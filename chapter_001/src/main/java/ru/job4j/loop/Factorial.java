package ru.job4j.loop;

public class Factorial {

    public int calc(int n) {
        int fact = 1;
        if (n != 0) {
            for (; n > 0; n--) {
                fact = fact * n;
            }
        }
        return fact;
    }
}