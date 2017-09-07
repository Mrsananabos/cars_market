package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
            BubbleSort sort = new BubbleSort();
            int[] resultArray = sort.sort(new int[]{100, 23, 63, 41, 5, 17, 37, 76, 23, 99});
            int[] expectArray = {5, 17, 23, 23, 37, 41, 63, 76, 99, 100};
            assertThat(resultArray, is(expectArray));

        }
}
