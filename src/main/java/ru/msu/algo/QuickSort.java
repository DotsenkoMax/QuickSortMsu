package ru.msu.algo;

import ru.msu.algo.pivots.PivotResolver;
import ru.msu.algo.pivots.RandomPivotResolver;
import ru.msu.algo.utils.QuickSortStrategy;

import java.util.Comparator;

final public class QuickSort implements SmartSort {
    private final QuickSortStrategy strategy;
    private final PivotResolver pivotResolver;
    private final SmartSort batchSorting;

    public QuickSort(QuickSortStrategy strategy) {
        this(strategy, new RandomPivotResolver());
    }

    public QuickSort(QuickSortStrategy strategy, PivotResolver pivotResolver) {
        this(strategy, pivotResolver, new InsertSort());
    }

    public QuickSort(QuickSortStrategy strategy, PivotResolver pivotResolver, SmartSort batchSorting) {
        this.strategy = strategy;
        this.pivotResolver = pivotResolver;
        this.batchSorting = batchSorting;
    }

    @Override
    public <T> void sort(T[] elements, Comparator<T> comparator) {
        sort(elements, 0, elements.length, comparator);
    }

    @Override
    public <T> void sort(T[] elements, int leftBound, int rightBound, Comparator<T> comparator) {
        innerSort(elements, leftBound, rightBound, comparator);
    }

    private <T> void innerSort(T[] elements, int leftBound, int rightBound, Comparator<T> comparator) {
        while (rightBound - leftBound > 1) {
            if (rightBound - leftBound <= strategy.getBatchSize()) {
                batchSorting.sort(elements, leftBound, rightBound, comparator);
                return;
            }
            T pivot = pivotResolver.resolve(elements, leftBound, rightBound, comparator);
            int leftIter = leftBound, rightIter = rightBound - 1;
            while (leftIter <= rightIter) {
                while (leftIter < rightIter) {
                    if (comparator.compare(elements[leftIter], pivot) < 0) leftIter += 1;
                    else break;
                }
                while (leftIter < rightIter) {
                    if (comparator.compare(pivot, elements[rightIter]) < 0) rightIter -= 1;
                    else break;
                }
                swap(elements, leftIter, rightIter);
                if (leftIter == rightIter) {
                    int compareResult = comparator.compare(elements[leftIter], pivot);
                    if (compareResult == 0) ++rightIter;
                    else if (compareResult < 0) {
                        ++rightIter;
                        ++leftIter;
                    }
                    break;
                } else if (leftIter == rightIter - 1) {
                    ++leftIter;
                    break;
                }
                ++leftIter;
                --rightIter;
            }

            int leftRecSize = leftIter - leftBound, rightRecSize = rightBound - rightIter;
            if (leftRecSize > rightRecSize) {
                innerSort(elements, rightIter, rightBound, comparator);
                rightBound = leftIter;
            } else {
                innerSort(elements, leftBound, leftIter, comparator);
                leftBound = rightIter;
            }
        }
    }

    private <T> void swap(T[] elements, int leftIdx, int rightIdx) {
        T tmp = elements[leftIdx];
        elements[leftIdx] = elements[rightIdx];
        elements[rightIdx] = tmp;
    }

    public String toString() {
        return String.format("Quicksort{%s, %s}", strategy, pivotResolver);
    }
}
