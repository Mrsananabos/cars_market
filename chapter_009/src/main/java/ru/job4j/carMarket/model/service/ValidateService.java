package ru.job4j.carMarket.model.service;

import org.apache.log4j.Logger;
import ru.job4j.toDoList.model.dao.HiberStorage;
import ru.job4j.toDoList.model.dao.Storage;
import ru.job4j.toDoList.model.entity.Item;

import java.util.List;

public class ValidateService implements Validate {
    private static final Validate INSTANCE = new ValidateService();
    private final Storage logic = HiberStorage.getInstance();
    private static final Logger LOGGER = Logger.getLogger(ValidateService.class);

    public static Validate getInstance() {
        return INSTANCE;
    }

    public List<Item> findAll() {
        List<Item> result = logic.findAll();
        if (result == null) {
            LOGGER.error("Items are not finded");
        }
        return result;
    }

    public Item add(String desc) {
        Item item = new Item();
        item.setDesc(desc);
        Item result = this.logic.add(item);
        if (!validItem(result)) {
            LOGGER.error("item is incorrect");
        }
        return item;
    }

    @Override
    public void doneItem(int id) {
        if (id < 0) {
            LOGGER.error("invalid id");
        } else {
            logic.doneItem(id);
        }
    }

    private boolean validItem(Item item) {
        return ((item.getId() >= 0) && (!item.getDesc().isEmpty()) && (item.getCreated() != null));
    }
}
