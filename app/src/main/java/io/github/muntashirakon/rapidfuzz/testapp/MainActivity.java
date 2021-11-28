// SPDX-License-Identifier: Apache-2.0

package io.github.muntashirakon.rapidfuzz.testapp;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.github.muntashirakon.rapidfuzz.RapidFuzz;
import io.github.muntashirakon.rapidfuzz.RapidFuzzCached;

public class MainActivity extends AppCompatActivity {
    private static final String s1 = "new york mets\u0000";
    private static final String s2 = "new YORK mets\u0000";
    private static final String s3 = "the wonderful new york mets\u0000";
    private static final String s4 = "new york mets vs atlanta braves\u0000";
    private static final String s5 = "atlanta braves vs new york mets\u0000";
    private static final String s6 = "new york mets - atlanta braves\u0000";
    private static final String s7 = "new york city mets - atlanta braves\u0000";
    // test silly corner cases
    private static final String s8 = "{\u0000";
    private static final String s8a = "{\u0000";
    private static final String s9 = "{a\u0000";
    private static final String s9a = "{a\u0000";
    private static final String s10 = "a{\u0000";
    private static final String s10a = "{b\u0000";

    public static final List<String> YORK_LIST = new ArrayList<String>() {{
        add("New York");
        add("Yorkshire");
        add("New New York");
        add("New New New York");
        add("NY");
        add("New York City");
        add("York");
        add("New Yorker");
        add("New York Times");
        add("NYC");
        add("Cork");
        add("Vibgyor");
    }};

    private TextView textView;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
        textView2 = findViewById(R.id.tv2);
        new Thread(this::init).start();
    }

    @WorkerThread
    private void init() {
        int i = 1;
        StringBuilder builder = new StringBuilder()
                .append(getTestHeader(i++, "ratio", s1, s1))
                .append(getTestResult(100, RapidFuzz.ratio(s1, s1)))
                .append(getTestHeader(i++, "ratio", s8, s8a))
                .append(getTestResult(100, RapidFuzz.ratio(s8, s8a)))
                .append(getTestHeader(i++, "ratio", s9, s9a))
                .append(getTestResult(100, RapidFuzz.ratio(s9, s9a)))
                .append(getTestHeader(i++, "ratio", s1, s2))
                .append(getTestResult(73.33, RapidFuzz.ratio(s1, s2)))
                .append(getTestHeader(i++, "ratio", s1.toLowerCase(Locale.ROOT), s2.toLowerCase(Locale.ROOT)))
                .append(getTestResult(100, RapidFuzz.ratio(s1.toLowerCase(Locale.ROOT), s2.toLowerCase(Locale.ROOT))))
                .append(getTestHeader(i++, "partialRatio", s1, s1))
                .append(getTestResult(100, RapidFuzz.partialRatio(s1, s1)))
                .append(getTestHeader(i++, "ratio", s1, s3))
                .append(getTestResult(68.18, RapidFuzz.ratio(s1, s3)))
                .append(getTestHeader(i++, "partialRatio", s1, s3))
                .append(getTestResult(100, RapidFuzz.partialRatio(s1, s3)))
                .append(getTestHeader(i++, "tokenSortRatio", s1, s1))
                .append(getTestResult(100, RapidFuzz.tokenSortRatio(s1, s1)))
                .append(getTestHeader(i++, "tokenSetRatio", s4, s5))
                .append(getTestResult(100, RapidFuzz.tokenSetRatio(s4, s5)))
                .append(getTestHeader(i++, "tokenSetRatio", s10, s10a))
                .append(getTestResult(50, RapidFuzz.tokenSetRatio(s10, s10a)))
                .append(getTestHeader(i++, "partialTokenSetRatio", s10, s10a))
                .append(getTestResult(100, RapidFuzz.partialTokenSetRatio(s10, s10a)));
        runOnUiThread(() -> textView.setText(builder));
        // Extract
        StringBuilder builder2 = new StringBuilder();
        i = 1;
        testExtractAll(i++, "Weighted Ratio", RapidFuzz.TYPE_WEIGHTED_RATIO, builder2);
        testExtractAll(i++, "Quick Ratio", RapidFuzz.TYPE_QUICK_RATIO, builder2);
        testExtractAll(i++, "Ratio", RapidFuzz.TYPE_RATIO, builder2);
        testExtractAll(i++, "Partial Ratio", RapidFuzz.TYPE_PARTIAL_RATIO, builder2);
        testExtractAll(i++, "Token Sort Ratio", RapidFuzz.TYPE_TOKEN_SORT_RATIO, builder2);
        testExtractAll(i++, "Token Set Ratio", RapidFuzz.TYPE_TOKEN_SET_RATIO, builder2);
        runOnUiThread(() -> textView2.setText(builder2));
    }

    @NonNull
    private String getTestHeader(int testNo, String algo, String s1, String s2) {
        return "Test " + testNo + ": RapidFuzz." + algo + "(\"" + s1 + "\", \"" + s2 + "\");\n";
    }

    @NonNull
    private String getTestResult(double expected, double actual) {
        return "        Expected: " + expected + ", Actual: " + actual + "\n\n";
    }

    private void testExtractAll(int testNo, String algo, int algoType, StringBuilder builder) {
        List<RapidFuzzCached.Result<String>> results = RapidFuzzCached.extractAll("York", YORK_LIST, algoType);
        builder.append("Test ").append(testNo).append(": ").append(algo).append("\n")
                .append("       Query: York\n")
                .append("       List: ").append(YORK_LIST).append("\n");
        for (RapidFuzzCached.Result<String> result : results) {
            builder.append("       ").append(result.toString()).append("\n");
        }
    }
}
