package ru.job4j.bomberman;

public class A implements Runnable {

    @Override
    public void run() {
            Thread.currentThread().interrupt();
    }
}
