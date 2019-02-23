package ru.job4j.threads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NonBlocking {
    protected final ConcurrentMap<Integer, Base> cache = new ConcurrentHashMap<>();

    public void add(Base model) {
        this.cache.put(model.getId(), model);
    }

    public void delete(Base model) {
        this.cache.remove(model.getId());
    }

    public void update(Base newModel) throws OplimisticException {
        this.cache.computeIfPresent(newModel.getId(), (integer, oldModel) -> {
            if (oldModel.getVersion() == newModel.getVersion()) {
                newModel.update();
                return newModel;
            } else {
                throw new OplimisticException("data erased");
            }
        });
    }
}
