package ru.job4j.threads;

import javax.naming.InsufficientResourcesException;
import java.util.Random;

public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.amount = amount;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public synchronized void withdraw(int amount) throws InsufficientResourcesException{
         if (this.amount < amount) {
             throw new InsufficientResourcesException("Недостаток денег на счёте");
         } else {
             this.amount -= amount;
         }
    }

    public synchronized void deposit(int amount) {
        this.amount += amount;
    }
}
