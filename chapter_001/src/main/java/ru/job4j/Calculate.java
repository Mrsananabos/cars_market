package ru.job4j;

public class Calculate {
    private double result;

    public void add(double first, double second) {

        this.result = first + second;
    }

    public void subtrack(double first, double second) {

        this.result = first - second;
    }

    public void div(double first, double second) {
        this.result = first / second;
    }

    public void multiple(double first, double second) {
        this.result = first * second;
    }

    public double getResult() {
        return this.result;
    }

}
