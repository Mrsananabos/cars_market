package ru.job4j.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ConvertList {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<Integer>();
        for (int[] i : array) {
            for (int j : i) {
                list.add(j);
            }
        }
        return list;
    }

    public int[][] toArray(List<Integer> list, int rows) {
        List<Integer> finalList = new ArrayList<Integer>();
        int columns = 0;
        int i = 0;
        if (list.size() % rows != 0) {
            columns = (list.size() / rows) + 1;
            int newCells = columns * rows - list.size();
            Integer[] newList = new Integer[newCells];
            for (int q = 0; q < newCells; q++) {
                newList[q] = 0;
            }
            finalList.addAll(list);
            finalList.addAll(Arrays.asList(newList));
        } else {
            columns = list.size() / rows;
            finalList.addAll(list);
        }

        int[][] newArray = new int[rows][columns];
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < columns; b++, i++) {
                newArray[a][b] = finalList.get(i);
            }
        }
        return newArray;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> newList = new ArrayList<Integer>();
        int lengthList = list.size();
        for (int i = 0; i < lengthList; i++) {
            for (int a : list.get(i)) {
                newList.add(a);
            }
        }
        return newList;
    }

}