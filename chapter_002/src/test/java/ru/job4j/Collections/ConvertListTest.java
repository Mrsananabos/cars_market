package ru.job4j.Collections;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertListTest{
    @Test
    public void WhenArrayEnterAfterListComes() {
        ConvertList convert = new ConvertList();
        List<Integer> resultList = convert.toList(new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}});
        List<Integer> expectList = new ArrayList<>();
        for (int i = 1; i<=resultList.size(); i++){
            expectList.add(i);
        }
        assertThat(resultList, is(expectList));


    }

    @Test
    public void WhenListEnterAfterArrayComes() {
        ConvertList convert = new ConvertList();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        int[][] resultArray = convert.toArray(list, 3);
        int[][] expectArray = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 0, 0}};
        assertThat(resultArray, is(expectArray));
    }
}