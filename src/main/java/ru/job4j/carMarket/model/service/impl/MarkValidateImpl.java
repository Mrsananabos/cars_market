package ru.job4j.carMarket.model.service.impl;

import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.dao.impl.MarkDaoImpl;
import ru.job4j.carMarket.model.entity.Mark;
import ru.job4j.carMarket.model.service.CarPartValidate;

import java.util.List;

public class MarkValidateImpl implements CarPartValidate<Mark> {
    private static final CarPartValidate INSTANCE = new MarkValidateImpl();
    private static final Logger LOGGER = Logger.getLogger(MarkValidateImpl.class);

    private MarkValidateImpl() {
    }

    public static CarPartValidate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Mark> getCarPart() {
        List<Mark> result = MarkDaoImpl.getInstance().getAll();
        if (result == null) {
            LOGGER.info("Marks are not found");
        }
        return result;
    }
}
