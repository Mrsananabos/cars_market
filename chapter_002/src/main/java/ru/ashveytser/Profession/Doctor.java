package ru.ashveytser.Profession;

public class Doctor extends Profession {
    public int experience;
    public Doctor(String name, String career, int age, int experiance) {
        super(name, career, age);
        this.experience=experiance;
    }
    public  String Diagnose(Pacient pacient) {
        return (super.career+" "+super.name+"лечит "+ pacient.name);

    }
}