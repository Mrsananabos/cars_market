package ru.job4j.carMarket.model.service.impl;

import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.dao.impl.BodyTypeDaoImpl;
import ru.job4j.carMarket.model.entity.BodyType;
import ru.job4j.carMarket.model.service.CarPartValidate;

import java.util.List;

public class BodyTypeValidateImpl implements CarPartValidate<BodyType>{
    private static final CarPartValidate INSTANCE = new BodyTypeValidateImpl();
    private static final Logger LOGGER = Logger.getLogger(BodyTypeValidateImpl.class);

    private BodyTypeValidateImpl() {
    }

    public static CarPartValidate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<BodyType> getCarPart() {
        List<BodyType> result = BodyTypeDaoImpl.getInstance().getAll();
        if (result == null) {
            LOGGER.info("Body types are not found");
        }
        return result;
    }
}
