package ru.job4j.Sorting;

import java.util.Comparator;
import java.util.List;

public class ListCompare implements Comparator<List<Integer>> {
    @Override
    public int compare(List<Integer> left, List<Integer> right) {
        int rsl = left.size()-right.size();
        if (rsl == 0){
            for (int i = 0; i<left.size(); i++){
                if (!left.get(i).equals(right.get(i))) {
                    rsl = left.get(i)>right.get(i) ? 1 : -1;
                    break;
                }
            }
        }
        return rsl == 0 ? 0 : rsl>0 ? 1 : -1;
    }
}






