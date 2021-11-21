package ru.msu.algo.comparators;

import java.util.Comparator;

public class CountingComparator<T> implements Comparator<T> {
    private final Comparator<T> comparator;
    private int counter = 0;

    public CountingComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {
        counter += 1;
        return comparator.compare(o1, o2);
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public boolean equals(Object obj) {
        return comparator.equals(obj);
    }
}
