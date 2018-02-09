package ru.job4j.Generalizations;

public class User{
   private int id;
   private String name;
   private String city;

    public User(int id, String name, String city){
        this.id = id;
        this.name = name;
        this.city = city;
    }

    int getId() {
        return this.id;
    }

    String getName() {
        return this.name;
    }

    String getCity() {
        return this.city;
    }

}