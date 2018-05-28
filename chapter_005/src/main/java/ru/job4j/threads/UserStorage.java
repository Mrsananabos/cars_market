package ru.job4j.threads;

import net.jcip.annotations.ThreadSafe;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage extends Thread {

    private List<User> store = new ArrayList<>();

    private User find(int id) {
        for (User currentUser : this.store) {
            if (currentUser.getId() == id) {
                return currentUser;
            }
        }
        return null;
    }


    public synchronized boolean add(User user) {
        this.store.add(user);
        return true;
    }

    public synchronized boolean update(User user) {
        for (User cuttentUser : store) {
            if (cuttentUser.getId() == user.getId()) {
                store.remove(cuttentUser);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        User currentUser = find(user.getId());
        return currentUser != null && store.remove(user);
    }

    public boolean transfer(int fromId, int toId, int amount) throws InsufficientResourcesException {
        User fromUser = find(fromId);
        User toUser = find(toId);
        try {
            fromUser.withdraw(amount);
            toUser.deposit(amount);
        } catch (InsufficientResourcesException e) {
            System.out.println("InsufficientResourcesException");
            return false;
        }
        return true;
    }
}



