package ru.job4j.labmda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void whenLinearFunction() {
        Calculator calc = new Calculator();
        List<Double> results = new ArrayList<>();
        results = calc.function(
                0, 3,
                index -> (double) index + 10);
        ArrayList<Double> expected = new ArrayList<>(Arrays.asList(10D, 11D, 12D));
        assertThat(results, is(expected));
    }

    @Test
    public void whenQuadraticFunction() {
        Calculator calc = new Calculator();
        List<Double> results = new ArrayList<>();
        results = calc.function(
                1, 4,
                index -> (double) Math.pow(index, 2D) + 10);
        ArrayList<Double> expected = new ArrayList<>(Arrays.asList(11D, 14D, 19D));
        assertThat(results, is(expected));
    }

    @Test
    public void whenLogarithmicFunction() {
        Calculator calc = new Calculator();
        List<Double> results = new ArrayList<>();
        results = calc.function(
                100, 103,
                index -> (double) Math.log10(index));
        ArrayList<Double> expected = new ArrayList<>(Arrays.asList(2D, 2.0043213737826426D, 2.0086001717619175D));
        System.out.println(results);
        assertThat(results, is(expected));
    }
}