 
package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculateTest {
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculate calc = new Calculate();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }
    @Test
     public void whenSubtractOneMinusOneThenZero() {
        Calculate calc = new Calculate();
        calc.subtrack(1D, 1D);
        double result = calc.getResult();
        double expected = 0;
        assertThat(result, is(expected));
    }
    @Test
public void whenDivOneDivideOneThenOne() {
        Calculate calc = new Calculate();
        calc.div(1D, 1D);
        double result = calc.getResult();
        double expected = 1;
        assertThat(result, is(expected));
    }
    @Test
public void whenMultipleOneMultiplyOneThenOne() {
        Calculate calc = new Calculate();
        calc.multiple(1D, 1D);
        double result = calc.getResult();
        double expected = 1;
        assertThat(result, is(expected));
    }
}
