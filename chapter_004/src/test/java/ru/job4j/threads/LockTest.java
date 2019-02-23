package ru.job4j.threads;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class LockTest {

    Lock lock = new Lock();
    int value = 0;

    public void increment() {
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value++;
        lock.unlock();
    }

    @Test
    public void whenThreeTimesValueIncrementedThenIsThree() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                increment();
            }
        };
        Thread thr1 = new Thread(r);
        Thread thr2 = new Thread(r);
        Thread thr3 = new Thread(r);
        thr1.start();
        thr2.start();
        thr3.start();
        try {
            thr1.join();
             thr2.join();
             thr3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(this.value, Matchers.is(3));

    }
}