package ru.job4j.Sorting;

import java.util.Comparator;
import java.util.List;

public class Departments {


    public List<String> sortAscending (List<String> list) {

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int rsl = 0;
                char[] s1 = o1.toCharArray();
                char[] s2 = o2.toCharArray();
                int length = s1.length <= s2.length ? s1.length : s2.length;
                for (int i =0; i<length; i++){
                    if (s1[i] != s2[i]){
                        rsl = s1[i] > s2[i] ? 1 : -1;
                        break;
                    } else {
                        if (length == i+1){
                            rsl = s1.length>s2.length ? 1 : -1;
                        }
                    }
                }
                return rsl;
            }
        });
        return list;
    }


    public List<String> sortInDecreasing (List<String> list) {

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int rsl = 0;
                char[] s1 = o1.toCharArray();
                char[] s2 = o2.toCharArray();
                int length = s1.length <= s2.length ? s1.length : s2.length;
                for (int i =0; i<length; i++){
                    if (s1[i] != s2[i]){
                        rsl = s1[i] < s2[i] ? 1 : -1;
                        break;
                    } else {
                        if (length == i+1){
                            rsl = s1.length>s2.length ? 1 : -1;
                        }
                    }
                }
                return rsl;
            }
        });
        return list;
    }
}
