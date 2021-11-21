package ru.msu.algo.utils;

import ru.msu.algo.InsertSort;
import ru.msu.algo.QuickSort;
import ru.msu.algo.SmartSort;

public class Sorts {
    public static SmartSort quickSortOf(QuickSortStrategy sortStrategy) {
        return new QuickSort(sortStrategy);
    }

    public static SmartSort quickSortOf(QuickSortStrategy sortStrategy, PivotStrategy pivotStrategy) {
        return new QuickSort(sortStrategy, pivotStrategy.get());
    }

    public static SmartSort insertSortOf() {
        return new InsertSort();
    }
}
