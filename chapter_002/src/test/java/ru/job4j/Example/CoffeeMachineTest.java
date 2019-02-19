package ru.job4j.example;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    @Test
    public void whenEnterValueIsHundredAndPriceSeventysevenRemainIsTwentyThree() {
        CoffeeMachine coffee = new CoffeeMachine();
        int[] resultArray = coffee.changes(100, 77);
        int[] expectArray = {10, 10, 2, 1};
        assertThat(resultArray, is(expectArray));
    }
}

