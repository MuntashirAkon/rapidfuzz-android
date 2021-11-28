// SPDX-License-Identifier: Apache-2.0

package io.github.muntashirakon.rapidfuzz;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_PARTIAL_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_PARTIAL_TOKEN_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_PARTIAL_TOKEN_SET_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_PARTIAL_TOKEN_SORT_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_QUICK_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_TOKEN_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_TOKEN_SET_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_TOKEN_SORT_RATIO;
import static io.github.muntashirakon.rapidfuzz.RapidFuzz.TYPE_WEIGHTED_RATIO;

/**
 * Cached implementation of {@link RapidFuzz}. If it is necessary to fetch multiple ratio/score using the same query
 * string, cached implementation is much faster.
 * <p>
 * In addition, a number of utility methods are provided for faster matching given a collection of string or objects.
 * <p>
 * This class is not thread-safe!
 */
public class RapidFuzzCached implements Closeable {
    /**
     * Generates choice string when a collection of objects is provided as an argument in the {@code #extract*} methods.
     *
     * @param <T> The type of object the collection holds.
     */
    public interface ChoiceGenerator<T> {
        /**
         * Get a choice string from the given object.
         *
         * @param object An object belonging to the collection.
         * @return The choice string generated in some way.
         */
        String getChoice(T object);
    }

    /**
     * Generates choice string when a collection of objects is provided as an argument in the {@code #extract*} methods.
     *
     * @param <T> The type of object the collection holds.
     */
    public interface ChoicesGenerator<T> {
        /**
         * Get choice strings from the given object. Maximum score is taken.
         *
         * @param object An object belonging to the collection.
         * @return The choice strings generated in some way.
         */
        List<String> getChoices(T object);
    }

    /**
     * Holds the selected choice and its score analysed from a collection of objects or strings.
     *
     * @param <T> The type of object the collection holds.
     */
    public static class Result<T> implements Comparable<Result<T>> {
        private final T mObject;
        private final double mScore;

        private Result(T object, double score) {
            this.mObject = object;
            this.mScore = score;
        }

        /**
         * Get the choice object.
         */
        public T getObject() {
            return mObject;
        }

        /**
         * Get score calculated using a given algorithm.
         */
        public double getScore() {
            return mScore;
        }

        /**
         * Get the object converted to string, or the string itself if the object is a string.
         */
        public String getString() {
            if (mObject instanceof String) return (String) mObject;
            return mObject.toString();
        }

        @Override
        public int compareTo(Result o) {
            if (o == null) return 1;
            return Double.compare(this.mScore, o.mScore);
        }

        @Override
        public String toString() {
            return "Result{" +
                    "mObject=" + mObject +
                    ", mScore=" + mScore +
                    '}';
        }
    }

    static {
        System.loadLibrary("rapidfuzz");
    }

    public static List<Result<String>> extractAll(String query, Collection<String> choices) {
        return extractAll(query, choices, 0.0);
    }

    public static List<Result<String>> extractAll(String query, Collection<String> choices, int ratioType) {
        return extractAll(query, choices, ratioType, 0.0);
    }

    public static List<Result<String>> extractAll(String query, Collection<String> choices, double scoreCutoff) {
        return extractAll(query, choices, TYPE_WEIGHTED_RATIO, scoreCutoff);
    }

    public static List<Result<String>> extractAll(String query, Collection<String> choices, int ratioType, double scoreCutoff) {
        if (choices == null) return null;
        if (choices.size() == 0) return Collections.emptyList();
        List<Result<String>> results = new ArrayList<>();
        RapidFuzzCached extractor = new RapidFuzzCached(query, ratioType);
        for (String choice : choices) {
            double score = extractor.ratio(choice);
            if (score >= scoreCutoff) {
                results.add(new Result<>(choice, score));
            }
        }
        return results;
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoiceGenerator<T> generator) {
        return extractAll(query, choices, generator, 0.0);
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoiceGenerator<T> generator, int ratioType) {
        return extractAll(query, choices, generator, ratioType, 0.0);
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoiceGenerator<T> generator, double scoreCutoff) {
        return extractAll(query, choices, generator, TYPE_WEIGHTED_RATIO, scoreCutoff);
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoiceGenerator<T> generator, int ratioType, double scoreCutoff) {
        if (choices == null) return null;
        if (choices.size() == 0) return Collections.emptyList();
        List<Result<T>> results = new ArrayList<>();
        RapidFuzzCached extractor = new RapidFuzzCached(query, ratioType);
        for (T choice : choices) {
            double score = extractor.ratio(generator.getChoice(choice));
            if (score >= scoreCutoff) {
                results.add(new Result<>(choice, score));
            }
        }
        return results;
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoicesGenerator<T> generator) {
        return extractAll(query, choices, generator, 0.0);
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoicesGenerator<T> generator, int ratioType) {
        return extractAll(query, choices, generator, ratioType, 0.0);
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoicesGenerator<T> generator, double scoreCutoff) {
        return extractAll(query, choices, generator, TYPE_WEIGHTED_RATIO, scoreCutoff);
    }

    public static <T> List<Result<T>> extractAll(String query, Collection<T> choices, ChoicesGenerator<T> generator, int ratioType, double scoreCutoff) {
        if (choices == null) return null;
        if (choices.size() == 0) return Collections.emptyList();
        List<Result<T>> results = new ArrayList<>();
        RapidFuzzCached extractor = new RapidFuzzCached(query, ratioType);
        for (T choice : choices) {
            List<String> strings = generator.getChoices(choice);
            double maxScore = 0;
            for (String s : strings) {
                double score = extractor.ratio(s);
                if (score >= maxScore) maxScore = score;
            }
            if (maxScore >= scoreCutoff) {
                results.add(new Result<>(choice, maxScore));
            }
        }
        return results;
    }

    public static Result<String> extractOne(String query, Collection<String> choices) {
        return extractOne(query, choices, 0.0);
    }

    public static Result<String> extractOne(String query, Collection<String> choices, int ratioType) {
        return extractOne(query, choices, ratioType, 0.0);
    }

    public static Result<String> extractOne(String query, Collection<String> choices, double scoreCutoff) {
        return extractOne(query, choices, TYPE_WEIGHTED_RATIO, scoreCutoff);
    }

    public static Result<String> extractOne(String query, Collection<String> choices, int ratioType, double scoreCutoff) {
        if (choices == null || choices.size() == 0) return null;
        RapidFuzzCached extractor = new RapidFuzzCached(query, ratioType);
        double bestScore = scoreCutoff;
        String bestChoice = null;
        for (String choice : choices) {
            double score = extractor.ratio(choice);
            if (score >= bestScore) {
                bestScore = score;
                bestChoice = choice;
            }
        }
        if (bestChoice == null) return null;
        return new Result<>(bestChoice, bestScore);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoiceGenerator<T> generator) {
        return extractOne(query, choices, generator, 0.0);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoiceGenerator<T> generator, int ratioType) {
        return extractOne(query, choices, generator, ratioType, 0.0);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoiceGenerator<T> generator, double scoreCutoff) {
        return extractOne(query, choices, generator, TYPE_WEIGHTED_RATIO, scoreCutoff);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoiceGenerator<T> generator, int ratioType, double scoreCutoff) {
        if (choices == null || choices.size() == 0) return null;
        RapidFuzzCached extractor = new RapidFuzzCached(query, ratioType);
        double bestScore = scoreCutoff;
        T bestChoice = null;
        for (T choice : choices) {
            double score = extractor.ratio(generator.getChoice(choice));
            if (score >= bestScore) {
                bestScore = score;
                bestChoice = choice;
            }
        }
        if (bestChoice == null) return null;
        return new Result<>(bestChoice, bestScore);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoicesGenerator<T> generator) {
        return extractOne(query, choices, generator, 0.0);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoicesGenerator<T> generator, int ratioType) {
        return extractOne(query, choices, generator, ratioType, 0.0);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoicesGenerator<T> generator, double scoreCutoff) {
        return extractOne(query, choices, generator, TYPE_WEIGHTED_RATIO, scoreCutoff);
    }

    public static <T> Result<T> extractOne(String query, Collection<T> choices, ChoicesGenerator<T> generator, int ratioType, double scoreCutoff) {
        if (choices == null || choices.size() == 0) return null;
        RapidFuzzCached extractor = new RapidFuzzCached(query, ratioType);
        double bestScore = scoreCutoff;
        T bestChoice = null;
        for (T choice : choices) {
            List<String> strings = generator.getChoices(choice);
            double maxScore = 0;
            for (String s : strings) {
                double score = extractor.ratio(s);
                if (score >= maxScore) maxScore = score;
            }
            if (maxScore >= bestScore) {
                bestScore = maxScore;
                bestChoice = choice;
            }
        }
        if (bestChoice == null) return null;
        return new Result<>(bestChoice, bestScore);
    }

    private final String mQuery;
    private final int mRatioType;
    private final long mPtr;

    public RapidFuzzCached(String query) {
        this(query, TYPE_WEIGHTED_RATIO);
    }

    public RapidFuzzCached(String query, int ratioType) {
        this.mQuery = query;
        this.mRatioType = ratioType;
        this.mPtr = initRatio();
    }

    public double ratio(String choice) {
        return ratio(choice, 0);
    }

    public double ratio(String choice, double scoreCutoff) {
        switch (mRatioType) {
            case TYPE_RATIO:
                return nativeGetRatio(mPtr, choice, scoreCutoff);
            case TYPE_PARTIAL_RATIO:
                return nativeGetPartialRatio(mPtr, choice, scoreCutoff);
            case TYPE_TOKEN_SORT_RATIO:
                return nativeGetTokenSortRatio(mPtr, choice, scoreCutoff);
            case TYPE_PARTIAL_TOKEN_SORT_RATIO:
                return nativeGetPartialTokenSortRatio(mPtr, choice, scoreCutoff);
            case TYPE_TOKEN_SET_RATIO:
                return nativeGetTokenSetRatio(mPtr, choice, scoreCutoff);
            case TYPE_PARTIAL_TOKEN_SET_RATIO:
                return nativeGetPartialTokenSetRatio(mPtr, choice, scoreCutoff);
            case TYPE_TOKEN_RATIO:
                return nativeGetTokenRatio(mPtr, choice, scoreCutoff);
            case TYPE_PARTIAL_TOKEN_RATIO:
                return nativeGetPartialTokenRatio(mPtr, choice, scoreCutoff);
            case TYPE_WEIGHTED_RATIO:
                return nativeGetWeightedRatio(mPtr, choice, scoreCutoff);
            case TYPE_QUICK_RATIO:
                return nativeGetQuickRatio(mPtr, choice, scoreCutoff);
            default:
                throw new UnsupportedOperationException("Unknown ratio type " + mRatioType);
        }
    }

    @Override
    public void close() {
        freeRatio();
    }

    private long initRatio() {
        switch (mRatioType) {
            case TYPE_RATIO:
                return nativeNewCachedRatio(mQuery);
            case TYPE_PARTIAL_RATIO:
                return nativeNewCachedPartialRatio(mQuery);
            case TYPE_TOKEN_SORT_RATIO:
                return nativeNewCachedTokenSortRatio(mQuery);
            case TYPE_PARTIAL_TOKEN_SORT_RATIO:
                return nativeNewCachedPartialTokenSortRatio(mQuery);
            case TYPE_TOKEN_SET_RATIO:
                return nativeNewCachedTokenSetRatio(mQuery);
            case TYPE_PARTIAL_TOKEN_SET_RATIO:
                return nativeNewCachedPartialTokenSetRatio(mQuery);
            case TYPE_TOKEN_RATIO:
                return nativeNewCachedTokenRatio(mQuery);
            case TYPE_PARTIAL_TOKEN_RATIO:
                return nativeNewCachedPartialTokenRatio(mQuery);
            case TYPE_WEIGHTED_RATIO:
                return nativeNewCachedWeightedRatio(mQuery);
            case TYPE_QUICK_RATIO:
                return nativeNewCachedQuickRatio(mQuery);
            default:
                throw new UnsupportedOperationException("Unknown ratio type " + mRatioType);
        }
    }

    private void freeRatio() {
        switch (mRatioType) {
            case TYPE_RATIO:
                nativeFreeCachedRatio(mPtr);
                break;
            case TYPE_PARTIAL_RATIO:
                nativeFreeCachedPartialRatio(mPtr);
                break;
            case TYPE_TOKEN_SORT_RATIO:
                nativeFreeCachedTokenSortRatio(mPtr);
                break;
            case TYPE_PARTIAL_TOKEN_SORT_RATIO:
                nativeFreeCachedPartialTokenSortRatio(mPtr);
                break;
            case TYPE_TOKEN_SET_RATIO:
                nativeFreeCachedTokenSetRatio(mPtr);
                break;
            case TYPE_PARTIAL_TOKEN_SET_RATIO:
                nativeFreeCachedPartialTokenSetRatio(mPtr);
                break;
            case TYPE_TOKEN_RATIO:
                nativeFreeCachedTokenRatio(mPtr);
                break;
            case TYPE_PARTIAL_TOKEN_RATIO:
                nativeFreeCachedPartialTokenRatio(mPtr);
                break;
            case TYPE_WEIGHTED_RATIO:
                nativeFreeCachedWeightedRatio(mPtr);
                break;
            case TYPE_QUICK_RATIO:
                nativeFreeCachedQuickRatio(mPtr);
                break;
            default:
                throw new UnsupportedOperationException("Unknown ratio type " + mRatioType);
        }
    }

    private static native long nativeNewCachedRatio(String query);

    private static native double nativeGetRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedRatio(long ptr);

    private static native long nativeNewCachedPartialRatio(String query);

    private static native double nativeGetPartialRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedPartialRatio(long ptr);

    private static native long nativeNewCachedTokenSortRatio(String query);

    private static native double nativeGetTokenSortRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedTokenSortRatio(long ptr);

    private static native long nativeNewCachedPartialTokenSortRatio(String query);

    private static native double nativeGetPartialTokenSortRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedPartialTokenSortRatio(long ptr);

    private static native long nativeNewCachedTokenSetRatio(String query);

    private static native double nativeGetTokenSetRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedTokenSetRatio(long ptr);

    private static native long nativeNewCachedPartialTokenSetRatio(String query);

    private static native double nativeGetPartialTokenSetRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedPartialTokenSetRatio(long ptr);

    private static native long nativeNewCachedTokenRatio(String query);

    private static native double nativeGetTokenRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedTokenRatio(long ptr);

    private static native long nativeNewCachedPartialTokenRatio(String query);

    private static native double nativeGetPartialTokenRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedPartialTokenRatio(long ptr);

    private static native long nativeNewCachedWeightedRatio(String query);

    private static native double nativeGetWeightedRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedWeightedRatio(long ptr);

    private static native long nativeNewCachedQuickRatio(String query);

    private static native double nativeGetQuickRatio(long ptr, String choice, double scoreCutoff);

    private static native void nativeFreeCachedQuickRatio(long ptr);
}
