package ru.job4j.collections.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private int children;
    private int dayOfBirth;
    private int mounthOfBirth;
    private int yearOfBirth;
    private Calendar birthday = Calendar.getInstance();

    public User(String name, int children, int dayBirthday, int mounthBirthday, int yearBirthday) {
        this.name = name;
        this.children = children;
        this.birthday.set(yearBirthday, mounthBirthday, dayBirthday);
        this.dayOfBirth = dayBirthday;
        this.mounthOfBirth = mounthBirthday;
        this.yearOfBirth = yearBirthday;
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


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 37 * result + children;
        result = 37 * result + dayOfBirth;
        result = 37 * result + mounthOfBirth;
        result = 37 * result + yearOfBirth;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (children != user.children) {
            return false;
        }
        if (dayOfBirth != user.dayOfBirth) {
            return false;
        }
        if (mounthOfBirth != user.mounthOfBirth) {
            return false;
        }
        if (yearOfBirth != user.yearOfBirth) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    public static void main(String[] args) {
        // Map<User, Object> map = new HashMap<>();
        // User u1 = new User("Sasha", 2, 13, 7, 1980);
        // User u2 = new User("Sasha", 2, 13, 7, 1980);
        // map.put(u1, new Object());
        // map.put(u2, new Object());
        Object o = new Object();
        Integer i1 = 0b10000000000000000000000000000001;
        int i2 = 5;
        byte b1 = 5;
        float f1 = 5;
        boolean a1 = true;
        char c = 'a';
        String s1 = "dddddd";


        System.out.println();
    }

}
