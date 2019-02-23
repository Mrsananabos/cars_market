package ru.job4j.threads;

import com.sun.corba.se.spi.monitoring.MonitoredObject;

import java.util.concurrent.atomic.AtomicBoolean;

public class Lock {
    private boolean locked = false;
    Thread currenntThread = null;

    public synchronized void lock() throws InterruptedException {
        while (this.locked) {
            this.wait();
        }
        currenntThread = Thread.currentThread();
        this.locked = true;
    }

    protected synchronized void unlock() {
        synchronized (this) {
            if (this.locked & Thread.currentThread().equals(this.currenntThread)) {
                this.locked = false;
                notify();
            }
        }
    }


}
