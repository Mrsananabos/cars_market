package ru.job4j.max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxTest {
    @Test
    public void MaximumOfTwo() {
        Max maxim = new Max();
        int result = maxim.max(81, 45);
        assertThat(result, is(81));
    }

    @Test
    public void MaximumOfThree() {
        Max maxim = new Max();
        int result = maxim.maxof3(81, 459, 38);
        assertThat(result, is(459));
    }


}
