package ru.job4j.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    private int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        for (int i = 0; i < this.size; i++) {
            this.threads.add(i, new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable r;
                    while (true) {
                        synchronized (tasks) {
                            while (tasks.isEmpty()) {
                                try {
                                    System.out.println("Thread is waiting job");
                                    tasks.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            r = tasks.remove();
                        }
                        r.run();
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
        synchronized (tasks) {
            tasks.offer(job);
            System.out.println("Job is added");
            tasks.notify();
        }

    }


}


