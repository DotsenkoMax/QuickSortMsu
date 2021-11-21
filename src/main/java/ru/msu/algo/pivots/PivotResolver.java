package ru.msu.algo.pivots;

import java.util.Comparator;

public interface PivotResolver {
    <T> T resolve(T[] array, Comparator<T> comparator);
    <T> T resolve(T[] array, int leftBound, int rightBound, Comparator<T> comparator);
}
