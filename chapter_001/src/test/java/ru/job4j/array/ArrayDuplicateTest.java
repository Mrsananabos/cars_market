package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] result = duplicate.remove(new String[] {"1", "3", "7", "3", "1", "1", "3", "6", "6", "7"});
        String[] expect = {"3", "6", "1", "7"};
        assertThat(result, arrayContainingInAnyOrder(expect));
    }
}