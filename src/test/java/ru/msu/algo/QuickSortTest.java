package ru.msu.algo;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.msu.algo.utils.PivotStrategy;
import ru.msu.algo.utils.QuickSortStrategy;
import ru.msu.algo.utils.Sorts;

import java.util.Arrays;
import java.util.stream.Stream;

public class QuickSortTest extends BaseSortTest {

    SmartSort simpleQuickSort = Sorts.quickSortOf(QuickSortStrategy.PURE_QUICK_SORT);
    SmartSort comboQuickSort = Sorts.quickSortOf(QuickSortStrategy.QUICK_PLUS_INSERT_SORT);

    @ParameterizedTest()
    @MethodSource("provideSimpleTestCases")
    public void simpleQuickSortTest(Integer[] initial, Integer[] expected) {
        simpleQuickSort.sort(initial, Integer::compare);
        Assert.assertArrayEquals(expected, initial);
    }

    @ParameterizedTest()
    @MethodSource("provideSimpleTestCases")
    public void comboSortTest(Integer[] initial, Integer[] expected) {
        comboQuickSort.sort(initial, Integer::compare);
        Assert.assertArrayEquals(expected, initial);
    }

    @ParameterizedTest()
    @MethodSource("provideQuickSortAlgs")
    public void stressTestALotOfEqualValues(SmartSort sortAlg) {
        Integer[] actual = generateRandomIntArray(20, 5000);
        Integer[] expected = actual.clone();
        Arrays.sort(expected);
        sortAlg.sort(actual, Integer::compare);
        Assert.assertArrayEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("provideQuickSortAlgs")
    public void stressTestALotOfDiffValues(SmartSort sortAlg) {
        Integer[] actual = generateRandomIntArray(200000, 200000000);
        Integer[] expected = actual.clone();
        Arrays.sort(expected);
        sortAlg.sort(actual, Integer::compare);
        Assert.assertArrayEquals(expected, actual);
    }


    @ParameterizedTest()
    @MethodSource("provideQuickSortAlgs")
    public void timingTestWitLibVersion(SmartSort sortAlg) {
        Integer[] actual = generateRandomIntArray(100000, 1000000);
        Integer[] expected = actual.clone();
        long time = System.currentTimeMillis();
        Arrays.sort(expected);
        long stdLibTime = System.currentTimeMillis() - time;

        time = System.currentTimeMillis();
        sortAlg.sort(actual, Integer::compare);
        long ourLibTime = System.currentTimeMillis() - time;

        Assert.assertArrayEquals(expected, actual);
        System.out.printf("Algorithm: %s. StdTime: %s, OurTime: %s", sortAlg, stdLibTime, ourLibTime);
    }


    protected static Stream<Arguments> provideQuickSortAlgs() {
        return Stream.of(
                Arguments.of(Sorts.quickSortOf(QuickSortStrategy.PURE_QUICK_SORT, PivotStrategy.MEDIAN_OF_3)),
                Arguments.of(Sorts.quickSortOf(QuickSortStrategy.PURE_QUICK_SORT, PivotStrategy.RANDOM)),
                Arguments.of(Sorts.quickSortOf(QuickSortStrategy.QUICK_PLUS_INSERT_SORT, PivotStrategy.MEDIAN_OF_3)),
                Arguments.of(Sorts.quickSortOf(QuickSortStrategy.QUICK_PLUS_INSERT_SORT, PivotStrategy.RANDOM))
        );
    }
}
