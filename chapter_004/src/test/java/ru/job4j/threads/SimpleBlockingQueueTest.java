package ru.job4j.threads;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenOfferTwiceAndPollTwice() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue(2);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.offer(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer a = queue.poll();
                    System.out.println("Покупка = " + a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread producer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.offer(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread customer1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer a = queue.poll();
                    System.out.println("Покупка = " + a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        customer.start();
        producer.start();
        customer1.start();
        producer1.start();

        customer.join();
        producer.join();
        customer1.join();
        producer1.join();
    }
}
