package ru.job4j.threads;

import org.junit.Test;

import static org.junit.Assert.*;

public class NonBlockingTest {

    @Test
    public void when() throws OplimisticException {
        NonBlocking cash = new NonBlocking();
        cash.add(new Base(1, "task1"));
        cash.add(new Base(2, "task2"));
        cash.add(new Base(3, "task3"));

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                cash.update(new Base(3, "task30"));

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                cash.update(new Base(3, "task300"));
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cash.cache.get(3).getName());


    }


}