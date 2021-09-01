package ru.asmotrov.vow.core.impl;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit tests for {@link DefaultWaterVolumeCalculatingService} class.
 *
 * Side note: tests for algorithms are a bit different from testing standard enterprise business logic,
 * as their results depend on the input a lot more than on underlying business logic, so that screams
 * of thousands of randomized tests to cover as much possible cases as possible.
 */
public class DefaultWaterVolumeCalculatingServiceTest {

    private DefaultWaterVolumeCalculatingService calculatingService = new DefaultWaterVolumeCalculatingService();
    private Random random = new Random();

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeIfInputIsNull() {
        calculatingService.calculate(null);

        fail("Should throw NPE at this point");
    }

    @Test
    public void shouldReturnZeroIfEmptyArray() {
        assertResultZeroForArray(new int[]{});
    }

    @Test
    public void shouldReturnZeroIfContainsOneElement() {
        assertResultZeroForArray(new int[]{12});
    }

    @Test
    public void shouldReturnZeroIfContainsTwoElements() {
        assertResultZeroForArray(new int[]{3, 7});
    }

    @Test
    public void shouldReturnZeroIfArrayIsOrdered() {
        assertResultZeroForArray(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    public void shouldReturnZeroForPeak() {
        assertResultZeroForArray(new int[]{3, 5, 10, 7, 2});
    }

    @Test
    public void shouldReturnSpecificValueForPredefinedArray() {
        assertResultForArray(3, new int[]{10, 3, 5, 0, 1});
    }

    @Test
    public void shouldReturnSpecificValueForPredefinedArrayBowl() {
        assertResultForArray(19, new int[]{10, 3, 5, 3, 10});
    }
    @Test
    public void shouldReturnZeroIfContainsOneElementRandom() {
        assertResultZeroForArray(new int[]{random.nextInt()});
    }

    @Test
    public void shouldReturnZeroIfContainsTwoElementsRandom() {
        assertResultZeroForArray(new int[]{random.nextInt(), random.nextInt()});
    }

    @Test
    public void shouldReturnZeroIfArrayIsOrderedRandom() {
        int size = 100;
        int[] array = new int[size];
        for (int i = 0; i < size; i++ ) {
            array[i] = random.nextInt();
        }
        Arrays.sort(array);
        assertResultZeroForArray(array);
    }

    private void assertResultForArray(long expected, int[] array) {
        long actual = calculatingService.calculate(array);

        assertEquals(expected, actual);
    }

    private void assertResultZeroForArray(int[] array) {
        assertResultForArray(0, array);
    }

}
