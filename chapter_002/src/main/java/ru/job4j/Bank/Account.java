package ru.job4j.Bank;

public class Account{

    private int value;
    private String requisies;

    public Account(int value, String requisies){
        this.value = value;
        this.requisies = requisies;
    }

    int getValue() {
        return this.value;
    }

    String getRequisies() {
        return  this.requisies;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

    @Override
    public String toString() {
        return "Account{" + "value: " + value + ", requisies: " + requisies + "}";
    }
}