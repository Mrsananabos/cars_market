package ru.job4j.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < this.size; i++) {
            this.threads.add(i, new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable r;
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            r = tasks.take();
                            r.run();
                        } catch (InterruptedException e) {
                            System.out.println("Thread is interrupted!");
                        }
                    }
                }

            }));

        }

    }


    public void start() {
        for (int i = 0; i < size; i++) {
            System.out.println("Thread is starting");
            threads.get(i).start();
        }
    }

    public void addWork(Runnable job) {
        try {
            tasks.put(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Job is added");
    }

}





