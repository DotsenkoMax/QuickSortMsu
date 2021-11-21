package ru.msu.algo;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Random;
import java.util.stream.Stream;

public class BaseSortTest {

    protected static Stream<Arguments> provideSimpleTestCases() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3}),
                Arguments.of(new Integer[]{2, 2, 2, 2}, new Integer[]{2, 2, 2, 2}),
                Arguments.of(new Integer[]{6, 4, 5}, new Integer[]{4, 5, 6}),
                Arguments.of(new Integer[]{10, 200, 30}, new Integer[]{10, 30, 200}),
                Arguments.of(new Integer[]{1, 1, 1, 1, 6, 1, 1, 1, 1}, new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 6})
        );
    }

    protected Integer[] generateRandomIntArray(int size, int upperBound) {
        Integer[] arr = new Integer[size];
        Random gen = new Random(245);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = gen.nextInt(upperBound);
        }
        return arr;
    }
}
