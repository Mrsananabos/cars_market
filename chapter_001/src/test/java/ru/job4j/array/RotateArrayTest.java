package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RotateArrayTest {
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
        RotateArray rotate = new RotateArray();
        int[][] resultArray = rotate.rotate(new int[][]{{1, 2}, {3, 4}});
        int[][] expectArray = {{3, 1}, {4, 2}};
        assertThat(resultArray, is(expectArray));
    }

    @Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
        RotateArray rotate = new RotateArray();
        int[][] resultArray = rotate.rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        int[][] expectArray = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertThat(resultArray, is(expectArray));

    }
}