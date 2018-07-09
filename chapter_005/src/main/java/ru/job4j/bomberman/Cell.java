package ru.job4j.bomberman;

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

    public static void main(String[] args) {
        ReentrantLock lockk = new ReentrantLock();
        lockk.lock();
        lockk.lock();
        lockk.lock();
        lockk.unlock();
        lockk.unlock();
        lockk.unlock();
    }

}
