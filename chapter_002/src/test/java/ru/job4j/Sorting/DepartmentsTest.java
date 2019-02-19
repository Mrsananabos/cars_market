package ru.job4j.sorting;

import org.junit.Test;
import ru.job4j.sorting.Departments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DepartmentsTest {

    @Test
    public void sortFromSmallestToLargest() {
        Departments d = new Departments();
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("K1\\SK1" , "K1\\SK2", "K2\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K2", "K2\\SK1\\SSK1", "K1", "K2\\SK1\\SSK2"));
        List<String> rsl = d.sortAscending(list);
        List<String> expect = new ArrayList<>();
        expect.addAll(Arrays.asList("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2",
                "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"));
        assertThat(rsl, is(expect));
    }


    @Test
    public void sortFromLargestToSmallest() {
        Departments d = new Departments();
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("K1\\SK1" , "K1\\SK2", "K2\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K2", "K2\\SK1\\SSK1", "K1", "K2\\SK1\\SSK2"));
        List<String> rsl = d.sortInDecreasing(list);
        List<String> expect = new ArrayList<>();
        expect.addAll(Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                        "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"));
        assertThat(rsl, is(expect));
    }

}