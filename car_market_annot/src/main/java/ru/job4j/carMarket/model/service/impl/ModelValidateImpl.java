package ru.job4j.carMarket.model.service.impl;

import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.dao.impl.ModelDaoImpl;
import ru.job4j.carMarket.model.entity.Model;
import ru.job4j.carMarket.model.service.ModelValidate;

import java.util.List;

public class ModelValidateImpl implements ModelValidate {
    private static final ModelValidate INSTANCE = new ModelValidateImpl();
    private static final Logger LOGGER = Logger.getLogger(ModelValidateImpl.class);

    public static ModelValidate getInstance() {
        return INSTANCE;
    }

    private ModelValidateImpl() {
    }

    @Override
    public List<Model> findModelsByMark(String idMark) {
        List<Model> result = null;
        if (isValid(idMark)) {
            result = ModelDaoImpl.getInstance().findModelsByMark(Integer.valueOf(idMark));
            if (result == null) {
                LOGGER.info(String.format("Models of idMark(%s) are not found", idMark));
            }
        } else {
            LOGGER.info("Id of mark is not valid");
        }
        return result;
    }

    private boolean isValid(String value) {
        return ((value != null) && (!value.isEmpty()));
    }

}
