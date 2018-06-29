package ru.job4j.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < this.size; i++) {
            this.threads.add(i, new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Runnable work = null;
                        try {
                            work = tasks.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        work.run();
                        if (Thread.currentThread().isInterrupted()) {
                            break;
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





