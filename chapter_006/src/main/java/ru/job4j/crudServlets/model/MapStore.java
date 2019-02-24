package ru.job4j.crudServlets.model;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapStore {
    private static final Logger LOGGER = Logger.getLogger(MapStore.class);
    private static final MapStore INSTANCE = new MapStore();
    private static final AtomicInteger INDEX = new AtomicInteger(1);
    private static final Map<Integer, Person> STORAGE = new ConcurrentHashMap<>();


    public static MapStore getInstance() {
        return INSTANCE;
    }

    public void add(Person person) {
        if (person != null) {
            STORAGE.put(INDEX.getAndIncrement(), person);
        }
    }
}
