package ru.job4j.bomberman;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Cell {
    private int x;
    private int y;
    private ReentrantLock lock = new ReentrantLock();

    public Cell(int x, int y, ReentrantLock lock) {
        this.x = x;
        this.y = y;
        this.lock = lock;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ReentrantLock getLock() {
        return lock;
    }

}
