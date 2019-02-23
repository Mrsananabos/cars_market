package ru.job4j.threads;

import org.junit.Test;

import javax.naming.InsufficientResourcesException;

public class UserStorageTest {
    @Test
    public void add() throws InterruptedException {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    storage.transfer(1, 2, 100);
                } catch (InsufficientResourcesException e) {
                    System.out.println(e);
                }
            }
        });
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    storage.transfer(2, 1, 500);
                } catch (InsufficientResourcesException e) {
                    System.out.println(e);
                }
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println("Amount of first User is " + user1.getAmount());
        System.out.println("Amount of second User is " + user2.getAmount());
    }
}