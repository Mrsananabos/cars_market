package ru.job4j.threads;

public class ThreadingProblem {
    private int count = 0;

    public void doCounter() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    count++;
                }
                System.out.println(count);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    count++;
                }
                System.out.println(count);
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        new ThreadingProblem().doCounter();
    }
}
