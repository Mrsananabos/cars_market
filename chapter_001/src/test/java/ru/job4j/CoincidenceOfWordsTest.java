package ru.job4j;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Arrays;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
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