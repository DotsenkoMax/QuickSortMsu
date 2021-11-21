package ru.msu.algo;

import java.util.Comparator;

final public class InsertSort implements SmartSort {
    @Override
    public <T> void sort(T[] elements, Comparator<T> comparator) {
        sort(elements, 0, elements.length, comparator);
    }

    @Override
    public <T> void sort(T[] elements, int leftBound, int rightBound, Comparator<T> comparator) {
        for (int prefix_len = leftBound + 1; prefix_len < rightBound; prefix_len++) {
            for (int inserting = prefix_len; inserting >= leftBound + 1; inserting--) {
                if (comparator.compare(elements[inserting - 1], elements[inserting]) > 0) {
                    T tmp = elements[inserting];
                    elements[inserting] = elements[inserting - 1];
                    elements[inserting - 1] = tmp;
                } else break;
            }
        }
    }
}
