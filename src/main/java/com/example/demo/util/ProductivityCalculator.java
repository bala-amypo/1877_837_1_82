// src/main/java/com/example/demo/util/ProductivityCalculator.java
package com.example.demo.util;

public class ProductivityCalculator {

    public static double clampScore(double score) {
        if (score < 0.0) return 0.0;
        if (score > 100.0) return 100.0;
        return score;
    }

    public static double normalize(double v) {
        return (v == null || v < 0) ? 0.0 : v;
    }

    public static int normalizeInt(Integer v) {
        return (v == null || v < 0) ? 0 : v;
    }

    public static double computeScore(double hours, int tasks, int meetings) {
        double score = (hours * 10.0) + (tasks * 5.0) + (meetings * 2.0);
        return clampScore(score);
    }
}
