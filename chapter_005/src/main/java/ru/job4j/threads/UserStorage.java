package ru.job4j.threads;

import net.jcip.annotations.ThreadSafe;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage extends Thread {

    public List<User> store = new ArrayList<>();


    public boolean add(User user) {
        store.add(user);
        return true;
    }

    public boolean update(User user) {
        for (User cuttentUser : store) {
            if (cuttentUser.getId() == user.id) {
                store.remove(cuttentUser);
                return true;
            }
        }
        return false;
    }

    public boolean delete(User user) {
        return store.remove(user);
    }
}



