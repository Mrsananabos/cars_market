package ru.job4j.sorting;

import java.util.*;

public class SortUser {
    public Set<User> sort(List<User> list) {
        Set<User> newSet = new TreeSet<User>();
        newSet.addAll(list);
        return newSet;
    }

    public List<User> sortNameLength(List<User> list) {
        list.sort(Comparator.comparingInt(o -> o.name.length())
        );
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        Collections.sort(list, (o1, o2) -> {
            int rsl = o1.name.compareTo(o2.name);
            return rsl != 0 ? rsl : Integer.compare(o1.age, o2.age);
        }
        );
        return list;
    }
}