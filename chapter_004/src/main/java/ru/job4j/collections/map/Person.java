package ru.job4j.collections.map;

import java.util.Arrays;

public class Person {
    String name;
    int age;
    boolean married;
    double growthInMeters;
    String[] previousEmployment;

    public Person(String name, int age, boolean married, double growthInMeters, String[] previousEmployment) {
        this.name = name;
        this.age = age;
        this.married = married;
        this.growthInMeters = growthInMeters;
        this.previousEmployment = previousEmployment;
    }


    @Override
    public int hashCode() {
        int result = 17;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (married ? 1 : 0);
        result = 31 * result + ((int) growthInMeters);
        for (String a : previousEmployment) {
            result = 31 * result + a.hashCode();
        }
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

        Person person = (Person) o;

        if (age != person.age) {
            return false;
        }
        if (married != person.married) {
            return false;
        }
        if (Double.compare(person.growthInMeters, growthInMeters) != 0) {
            return false;
        }
        if (name != null ? !name.equals(person.name) : person.name != null) {
            return false;
        }

            return Arrays.equals(previousEmployment, person.previousEmployment);
    }


    public static void main(String[] args) {
        String[] previousEmployment1 = new String[]{"Rusal", "Tatneft", "Lukoyl"};
        Person person1 = new Person("Aleksey", 54, true, 1.82, previousEmployment1);
        String[] previousEmployment2 = new String[]{"KrasniyOktabr", "Nestle"};
        Person person2 = new Person("Rodion", 29, false, 1.79, previousEmployment2);
        System.out.println("Hashcode person1 = " + person1.hashCode());
        System.out.println("Hashcode person2 = " + person2.hashCode());

    }
}
