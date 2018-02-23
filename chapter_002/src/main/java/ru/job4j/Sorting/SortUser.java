package ru.job4j.Sorting;

import javax.management.ObjectName;
import java.util.*;

public class SortUser {
    public Set<User> sort (List<User> list){
        Set<User> newSet = new TreeSet<User>();
        newSet.addAll(list);
        return newSet;
    }


    public List<User> sortNameLength (List<User> list) {
        list.sort( new Comparator<User>() {
                       @Override
                       public int compare(User o1, User o2) {
                           return Integer.compare(o1.name.length(), o2.name.length());
                       }
                   }
        );
        return list;
    }



    public List<User> sortByAllFields (List<User> list) {
        Collections.sort(list, new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int rsl = o1.name.compareTo(o2.name);
                        return rsl != 0 ? rsl : Integer.compare(o1.age, o2.age);

                }}
        );
        return list;
    }

}