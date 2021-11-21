package ru.msu.algo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msu.algo.comparators.CountingComparator;
import ru.msu.algo.utils.Sorts;

import java.util.Arrays;
import java.util.stream.Stream;

public class InsertSortTest extends BaseSortTest {

    SmartSort sortAlgo = Sorts.insertSortOf();

    @ParameterizedTest()
    @MethodSource("provideSimpleTestCases")
    public void simpleTest(Integer[] initial, Integer[] expected) {
        sortAlgo.sort(initial, Integer::compare);
        Assert.assertArrayEquals(expected, initial);
    }

    @Test
    public void stressTest() {
        Integer[] actual = generateRandomIntArray(20, 50);
        Integer[] expected = actual.clone();
        Arrays.sort(expected);
        sortAlgo.sort(actual, Integer::compare);
        Assert.assertArrayEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("provideCountingTestCases")
    public void simpleCountingTest(Integer[] initial, Integer[] expected, int count) {
        CountingComparator<Integer> myComp = new CountingComparator<>(Integer::compare);
        sortAlgo.sort(initial, myComp);
        Assert.assertArrayEquals(expected, initial);
        Assert.assertEquals(count, myComp.getCounter());
    }

    protected static Stream<Arguments> provideCountingTestCases() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3}, 2),
                Arguments.of(new Integer[]{2, 2, 2, 2}, new Integer[]{2, 2, 2, 2}, 3),
                Arguments.of(new Integer[]{6, 4, 5}, new Integer[]{4, 5, 6}, 3),
                Arguments.of(new Integer[]{10, 200, 30}, new Integer[]{10, 30, 200}, 3),
                Arguments.of(new Integer[]{1, 1, 1, 1, 6, 1, 1, 1, 1}, new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 6}, 12)
        );
    }


}