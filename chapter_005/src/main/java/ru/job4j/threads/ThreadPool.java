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

    public void start() {
        for (int i = 0; i < this.size; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Thread me = Thread.currentThread();
                    while (true) {
                        Runnable work = null;
                        try {
                            work = tasks.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        work.run();
                        if (me.isInterrupted()) {
                            System.out.println("Поток прерван");
                            break;
                        }
                    }
                }
            });
            this.threads.add(thread);
            thread.start();

        }
    }


    public void addWork(Runnable work) {
        try {
            tasks.put(work);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        Runnable stopper = () -> Thread.currentThread().interrupt();
            this.addWork(stopper);
    }


}





