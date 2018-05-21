package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Count {
    private int value;

    @GuardedBy("this")
    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public int get() {
        return this.value;
    }
}
