package ru.msu.algo.pivots;

import org.assertj.core.util.VisibleForTesting;

import java.util.Comparator;

public class MedianOfThreePivotResolver implements PivotResolver {

    @Override
    public <T> T resolve(T[] array, Comparator<T> comparator) {
        return resolve(array, 0, array.length, comparator);
    }

    @Override
    public <T> T resolve(T[] array, int leftBound, int rightBound, Comparator<T> comparator) {
        return medianOf3(array[leftBound], array[rightBound - 1], array[(leftBound + rightBound) / 2], comparator);
    }

    @VisibleForTesting
    private <T> T medianOf3(T a, T b, T c, Comparator<T> comparator) {
        if (comparator.compare(a, b) > 0) {
            if (comparator.compare(c, a) > 0) return a;
            return comparator.compare(b, c) > 0 ? b : c;
        } else if (comparator.compare(c, b) > 0) return b;
        return comparator.compare(a, c) > 0 ? a : c;
    }
}
