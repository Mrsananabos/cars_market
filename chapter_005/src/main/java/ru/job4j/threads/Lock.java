package ru.job4j.threads;

import java.util.concurrent.atomic.AtomicBoolean;

public class Lock {
    private boolean locked = false;

    public synchronized void lock() throws InterruptedException {
        while (this.locked) {
            this.wait();
        }
        this.locked = true;
    }

    protected synchronized void unlock() {
        this.locked = false;
        this.notifyAll();
    }


}
