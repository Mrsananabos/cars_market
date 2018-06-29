package ru.job4j.threads;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {


    @Test
    public void showWorkingThreadPool() {
        ThreadPool pool = new ThreadPool();
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Working1 is done");
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Working2 is done");
            }
        };
        Runnable r3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Working3 is done");
            }
        };
        Runnable r4 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Working4 is done");
            }
        };

        pool.start();
        pool.addWork(r1);

        pool.addWork(r2);

        pool.addWork(r3);
        pool.addWork(r4);



    }

}