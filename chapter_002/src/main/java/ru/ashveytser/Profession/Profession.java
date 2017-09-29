package ru.ashveytser.Profession;

public class Profession{
    public String name;
    public String career;
    public int age;

    public Profession(String name, String career, int age){
            this.name = name;
            this.career = career;
            this.age = age;
        }

    public String getName() {
        return this.name;
    }

    public String getCareer() {
        return this.career;
    }

    public int getAge() {
        return this.age;
    }


}

