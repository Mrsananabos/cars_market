package ru.job4j.threads;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadSafeArrayListTest {

    @Test
    public void when() {
        ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList(5);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add(10);
                list.add(20);
                list.add(30);
                System.out.println(list.getSize());
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                list.add(10);
                list.add(20);
                list.add(30);
                System.out.println(list.getSize());
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

    }

}
