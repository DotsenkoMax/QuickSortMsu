package ru.msu.algo.utils;

import ru.msu.algo.pivots.MedianOfThreePivotResolver;
import ru.msu.algo.pivots.PivotResolver;
import ru.msu.algo.pivots.RandomPivotResolver;

public enum PivotStrategy {
    MEDIAN_OF_3(new MedianOfThreePivotResolver()),
    RANDOM(new RandomPivotResolver());

    private final PivotResolver resolver;

    PivotStrategy(PivotResolver resolver) {
        this.resolver = resolver;
    }

    public PivotResolver get() {
        return resolver;
    }
}
