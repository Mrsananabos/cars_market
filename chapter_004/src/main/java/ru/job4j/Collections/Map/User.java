package ru.job4j.Collections.Map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private int children;
    private Calendar birthday = Calendar.getInstance();

    public User(String name, int children, int dayBirthday, int mounthBirthday, int yearBirthday) {
        this.name = name;
        this.children = children;
        this.birthday.set(yearBirthday, mounthBirthday, dayBirthday);
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        User u1 = new User("Sasha", 2, 13, 7, 1980);
       User u2 = new User("Sasha", 2, 13, 7, 1980);
        map.put(u1, new Object());
        map.put(u2, new Object());
        System.out.println(map);

    }
}
