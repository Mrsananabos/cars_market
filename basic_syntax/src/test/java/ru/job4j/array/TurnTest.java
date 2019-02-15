package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] resultArray = turn.back(new int[]{1, 2, 3, 4, 5});
        int[] expectArray = {5, 4, 3, 2, 1};
        assertThat(resultArray, is(expectArray));
    }

    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] resultArray = turn.back(new int[]{19, 23, 35, 48, 51, 100});
        int[] expectArray = {100, 51, 48, 35, 23, 19};
        assertThat(resultArray, is(expectArray));
    }
}