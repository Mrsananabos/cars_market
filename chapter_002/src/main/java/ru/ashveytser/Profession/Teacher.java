package ru.ashveytser.Profession;

public class Teacher extends Profession {
    public int rank;
    public Teacher(String name, String career, int age, int rank) {
        super(name, career, age);
        this.rank=rank;
    }
    public  String Diagnose(Student student) {
        return (super.career+" "+super.name+"обучает "+ student.name);

    }
}