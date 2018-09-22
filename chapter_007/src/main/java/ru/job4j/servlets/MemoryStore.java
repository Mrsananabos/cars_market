package ru.job4j.servlets;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private static final Map<Integer, User> STORE = new ConcurrentHashMap();
    private static Logger logger = Logger.getLogger(MemoryStore.class);
    private static int staticID = 0;

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        user.setId(staticID++);
        STORE.put(user.getId(), user);
        logger.info(user.getName() + " is added, ID = " + user.getId());
    }

    @Override
    public void update(User newUser) {
        STORE.put(newUser.getId(), newUser);
        newUser.updateCreateDate();
        logger.info("User " + newUser.getName() + " updated");
    }


    @Override
    public void delete(int id) {
        STORE.remove(id);
        logger.info("User with id = " + id + " successfuly deleted");
    }

    @Override
    public Collection<User> findAll() {
        return STORE.values();
    }

    @Override
    public User findByid(int id) {
        User rsl = null;
        try {
            if (STORE.containsKey(id)) {
                rsl = STORE.get(id);
            }
        } catch (Exception e) {
            logger.error("User is not found", e);
        }
        return rsl;
    }

    @Override
    public boolean storeContainsKey(int key) {
        return STORE.containsKey(key);
    }

    @Override
    public boolean storeIsEmpty() {
        return STORE.isEmpty();
    }


}
