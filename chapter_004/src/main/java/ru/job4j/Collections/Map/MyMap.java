package ru.job4j.collections.map;

import ru.job4j.collections.map.User;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MyMap {

    private String name;
    private int children;
    //  private Calendar birthday = Calendar.getInstance();

    public MyMap(String name, int children, int dayBirthday, int mounthBirthday, int yearBirthday) {
        this.name = name;
        this.children = children;
        // this.birthday.set(yearBirthday, mounthBirthday, dayBirthday);
    }


    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    // public Calendar getBirthday() {
    //  return birthday;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyMap myMap = (MyMap) o;

        if (children != myMap.children) {
            return false;
        }

        return name != null ? name.equals(myMap.name) : myMap.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + children;
        return result;
    }

    public static void main(String[] args) {
        Map<MyMap, Object> map = new HashMap<>();
        MyMap u1 = new MyMap("Sasha", 2, 13, 7, 1980);
        MyMap u2 = new MyMap("Sasha", 2, 13, 7, 1980);
        map.put(u1, new Object());
        map.put(u2, new Object());
        System.out.println(u1.hashCode());
        System.out.println("  " + u2.hashCode());


    }
}