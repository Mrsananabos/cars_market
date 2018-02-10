package ru.job4j.Sorting;

import javax.management.ObjectName;
import java.util.*;

public class SortUser {
    public Set<User> sort (List<User> list){
        Set<User> newSet = new TreeSet<User>();
        newSet.addAll(list);
        return newSet;
    }






    public static void main(String[] args) {
        List<User> list = new ArrayList<User>();
        list.addAll(Arrays.asList(new User(230, "Ily"), new User(45,"Anna"), new User(32, "Zena"), new User(18, "Bob"), (new User(20, "Anna"))));
        SortUser s = new SortUser();
        System.out.println( s.sort(list));

    }


}