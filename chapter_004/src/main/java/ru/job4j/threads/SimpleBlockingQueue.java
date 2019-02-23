package ru.job4j.threads;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> simpleBlockingQueue = new LinkedList<T>();
    private int size;
    private boolean isEmty = true;
    private boolean completed = false;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (completed) {
                this.wait();
            }
            this.simpleBlockingQueue.offer(value);
            if (this.simpleBlockingQueue.size() == this.size) {
                this.completed = true;
            }
            if (isEmty) {
                this.isEmty = false;
                this.notify();
            }
        }

    }


    public T poll() throws InterruptedException {
        synchronized (this) {
            while (isEmty) {
                this.wait();
            }
            if (this.simpleBlockingQueue.size() - 1 == 0) {
                this.isEmty = true;
            }
            if (this.simpleBlockingQueue.size() == this.size) {
                this.completed = false;
                this.notify();
            }

        }
        return this.simpleBlockingQueue.poll();
    }

}

