package ru.ashveytser.Profession;

public class Engineer extends Profession {
    public int CompletedProjects;
    public Engineer(String name, String career, int age, int CompletedProjects) {
        super(name, career, age);
        this.CompletedProjects=CompletedProjects;
    }
    public  String Diagnose(Building building) {
        return (super.career+" "+super.name+"проектирует "+ building.name);
    }
}