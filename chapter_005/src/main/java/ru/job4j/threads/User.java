package ru.job4j.threads;

import javax.naming.InsufficientResourcesException;
import java.util.Random;

public class User {
    protected int id;
    protected int amount;

    public User(int id, int amount) {
        this.amount = amount;
        this.id = id;
    }


    protected int getId() {
        return id;
    }

    protected int getAmount() {
        return amount;
    }

    protected void setAmount(int amount) {
        this.amount = amount;
    }

    protected synchronized void withdraw(int amount) throws InsufficientResourcesException{
         if (this.amount < amount) {
             throw new InsufficientResourcesException("Недостаток денег на счёте");
         } else {
             this.amount -= amount;
         }
    }

    protected synchronized void deposit(int amount) {
        this.amount += amount;
    }


}
