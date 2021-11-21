package ru.msu.algo.utils;

public enum QuickSortStrategy {
    QUICK_PLUS_INSERT_SORT(10),
    PURE_QUICK_SORT(-1);

    private final Integer minBatchSize;

    QuickSortStrategy(Integer minBatch) {
        minBatchSize = minBatch;
    }

    public Integer getBatchSize() {
        return minBatchSize;
    }
}
