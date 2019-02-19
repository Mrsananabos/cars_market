package ru.job4j.control;

import org.hamcrest.core.Is;
import org.junit.Test;
import ru.job4j.control.CoincidenceOfWords;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CoincidenceOfWordsTest {
    @Test
    public void whenArrayContainsDifferentArray() {
        CoincidenceOfWords contains = new CoincidenceOfWords();
        boolean result = contains.contains("Привет", "иве");
        assertThat(result, Is.is(true));

    }
}