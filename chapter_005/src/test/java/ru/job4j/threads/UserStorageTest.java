package ru.job4j.threads;

import org.junit.Test;

import javax.naming.InsufficientResourcesException;

public class UserStorageTest {

    private class ThreadTransfer extends Thread {
        private final User userFrom;
        private final User userTo;
        private final int amount;

        private ThreadTransfer(User userFrom, User userTo, int amount) {
            this.userFrom = userFrom;
            this.userTo = userTo;
            this.amount = amount;
        }

        @Override
        public void run() {
            try {
                this.userFrom.withdraw(this.amount);
            } catch (InsufficientResourcesException e) {
                e.printStackTrace();
            }
            this.userTo.deposit(this.amount);
        }
    }


    @Test
    public void add() throws InterruptedException {
        UserStorage storage = new UserStorage();
        User user1 =  new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);

        Thread first = new ThreadTransfer(user1, user2, 100);
        Thread second = new ThreadTransfer(user2, user1, 500);

        first.start();
        second.start();

     first.join();
        second.join();

        System.out.println("Amount of first User is " + user1.getAmount());
        System.out.println("Amount of second User is " + user2.getAmount());


    }

}