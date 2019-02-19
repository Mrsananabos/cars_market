package ru.job4j.bank;

public class Account {
    private int value;
    private String requisies;

    public Account(int value, String requisites) {
        this.value = value;
        this.requisies = requisites;
    }

    int getValue() {
        return this.value;
    }

    String getRequisies() {
        return this.requisies;
    }

    public void setValue(int newValue) {
        this.value = newValue;
    }

    @Override
    public String toString() {
        return "Account{" + "value: " + value + ", requisies: " + requisies + "}";
    }
}