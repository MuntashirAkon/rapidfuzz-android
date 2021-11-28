// SPDX-License-Identifier: Apache-2.0

package io.github.muntashirakon.rapidfuzz;

public class RapidFuzz {
    static {
        System.loadLibrary("rapidfuzz");
    }

    /**
     * Same as {@link #ratio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double ratio(String s1, String s2) {
        return ratio(s1, s2, 0);
    }

    /**
     * Calculates a simple ratio between two strings.
     *
     * <pre>
     * // score is 96.55
     * double score = RapidFuzz.ratio("this is a test", "this is a test!");
     * </pre>
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double ratio(String s1, String s2, double scoreCutoff) {
        return nativeRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #partialRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double partialRatio(String s1, String s2) {
        return partialRatio(s1, s2, 0);
    }

    /**
     * Calculates the {@link #ratio(String, String, double)} of the optimal string alignment.
     *
     * <pre>
     * // score is 100
     * double score = RapidFuzz.partialRatio("this is a test", "this is a test!");
     * </pre>
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double partialRatio(String s1, String s2, double scoreCutoff) {
        return nativePartialRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #tokenSortRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double tokenSortRatio(String s1, String s2) {
        return tokenSortRatio(s1, s2, 0);
    }

    /**
     * Sorts the words in the strings and calculates the {@link #ratio(String, String, double)} between them.
     *
     * <pre>
     * // score is 100
     * double score = RapidFuzz.tokenSortRatio("fuzzy wuzzy was a bear", "wuzzy fuzzy was a bear");
     * </pre>
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double tokenSortRatio(String s1, String s2, double scoreCutoff) {
        return nativeTokenSortRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #partialTokenSortRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double partialTokenSortRatio(String s1, String s2) {
        return partialTokenSortRatio(s1, s2, 0);
    }

    /**
     * Sorts the words in the strings and calculates the {@link #partialRatio(String, String, double)} between them.
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double partialTokenSortRatio(String s1, String s2, double scoreCutoff) {
        return nativePartialTokenSortRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #tokenSetRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double tokenSetRatio(String s1, String s2) {
        return tokenSetRatio(s1, s2, 0);
    }

    /**
     * Compares the words in the strings based on unique and common words between them using
     * {@link #ratio(String, String, double)}.
     *
     * <pre>
     * // score1 is 83.87
     * double score1 = RapidFuzz.tokenSortRatio("fuzzy was a bear", "fuzzy fuzzy was a bear");
     * // score2 is 100
     * double score2 = RapidFuzz.tokenSetRatio("fuzzy was a bear", "fuzzy fuzzy was a bear");
     * </pre>
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double tokenSetRatio(String s1, String s2, double scoreCutoff) {
        return nativeTokenSetRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #partialTokenSetRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double partialTokenSetRatio(String s1, String s2) {
        return partialTokenSetRatio(s1, s2, 0);
    }

    /**
     * Compares the words in the strings based on unique and common words between them using
     * {@link #partialRatio(String, String, double)}.
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double partialTokenSetRatio(String s1, String s2, double scoreCutoff) {
        return nativePartialTokenSetRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #tokenRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double tokenRatio(String s1, String s2) {
        return tokenRatio(s1, s2, 0);
    }

    /**
     * Helper method that returns the maximum of {@link #tokenSetRatio(String, String, double)} and
     * {@link #tokenSortRatio(String, String, double)}. This is faster than manually executing the two functions.
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double tokenRatio(String s1, String s2, double scoreCutoff) {
        return nativeTokenRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #partialTokenRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double partialTokenRatio(String s1, String s2) {
        return partialTokenRatio(s1, s2, 0);
    }

    /**
     * Helper method that returns the maximum of {@link #partialTokenSetRatio(String, String, double)} and
     * {@link #partialTokenSortRatio(String, String, double)}. This is faster than manually executing the two functions.
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double partialTokenRatio(String s1, String s2, double scoreCutoff) {
        return nativePartialTokenRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #weightedRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double weightedRatio(String s1, String s2) {
        return weightedRatio(s1, s2, 0);
    }

    /**
     * Calculates a weighted ratio based on the other ratio algorithms
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double weightedRatio(String s1, String s2, double scoreCutoff) {
        return nativeWeightedRatio(s1, s2, scoreCutoff);
    }

    /**
     * Same as {@link #quickRatio(String, String, double)} except that the score threshold is set to 0.0.
     *
     * @param s1 String to compare with s2
     * @param s2 String to compare with s1
     * @return The ratio between s1 and s2 or 0 when ratio < 0.
     */
    public static double quickRatio(String s1, String s2) {
        return quickRatio(s1, s2, 0);
    }

    /**
     * Calculates a quick ratio between two strings using {@link #ratio(String, String, double)}.
     *
     * @param s1          String to compare with s2
     * @param s2          String to compare with s1
     * @param scoreCutoff A score threshold between 0% and 100%. Matches with a lower score than this number will not be
     *                    returned.
     * @return The ratio between s1 and s2 or 0 when ratio < score_cutoff.
     */
    public static double quickRatio(String s1, String s2, double scoreCutoff) {
        return nativeQuickRatio(s1, s2, scoreCutoff);
    }

    // Native calls
    private static native double nativeRatio(String s1, String s2, double scoreCutoff);

    private static native double nativePartialRatio(String s1, String s2, double scoreCutoff);

    private static native double nativeTokenSortRatio(String s1, String s2, double scoreCutoff);

    private static native double nativePartialTokenSortRatio(String s1, String s2, double scoreCutoff);

    private static native double nativeTokenSetRatio(String s1, String s2, double scoreCutoff);

    private static native double nativePartialTokenSetRatio(String s1, String s2, double scoreCutoff);

    private static native double nativeTokenRatio(String s1, String s2, double scoreCutoff);

    private static native double nativePartialTokenRatio(String s1, String s2, double scoreCutoff);

    private static native double nativeWeightedRatio(String s1, String s2, double scoreCutoff);

    private static native double nativeQuickRatio(String s1, String s2, double scoreCutoff);
}
