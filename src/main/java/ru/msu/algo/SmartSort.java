package ru.msu.algo;

import java.util.Comparator;

public interface SmartSort {
    <T> void sort(T[] elements, Comparator<T> comparator);

    <T> void sort(T[] elements, int leftBound, int rightBound, Comparator<T> comparator);

    String toString();
}
