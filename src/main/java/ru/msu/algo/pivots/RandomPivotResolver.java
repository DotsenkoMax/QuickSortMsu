package ru.msu.algo.pivots;

import java.util.Comparator;
import java.util.Random;

final public class RandomPivotResolver implements PivotResolver {
    private final int seed = 48;
    private final Random generator = new Random(seed);

    @Override
    public <T> T resolve(T[] array, Comparator<T> comparator) {
        return resolve(array, 0, array.length, comparator);
    }

    @Override
    public <T> T resolve(T[] array, int leftBound, int rightBound, Comparator<T> comparator) {
        int element = generator.nextInt(rightBound - leftBound) + leftBound;
        return array[element];
    }
}
