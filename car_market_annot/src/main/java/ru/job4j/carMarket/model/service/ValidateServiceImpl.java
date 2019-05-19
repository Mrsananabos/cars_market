package ru.job4j.carMarket.model.service;

import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.dao.impl.*;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.FormCarSale;
import ru.job4j.carMarket.model.entity.User;

import java.util.List;

public class ValidateServiceImpl implements Validate {
    private static final Validate INSTANCE = new ValidateServiceImpl();
    private static final Logger LOGGER = Logger.getLogger(ValidateServiceImpl.class);

    private ValidateServiceImpl() {
    }

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Car> findCarsByKey(String key) {
        List<Car> result = null;
        if (isValid(key)) {
            if ("all".equals(key)) {
                result = CarDaoImpl.getInstance().getCars();
            } else {
                User user = UserDaoImpl.getInstance().findUserByLogin(key);
                result = user.getCars();
            }
            if (result == null) {
                LOGGER.info("Cars with key(" + key + ") are not found");
            }
        } else {
            LOGGER.info("Key is not valid");
        }
        return result;
    }

    @Override
    public List findPartsCarByKey(String key) {
        List result = null;
        if (isValid(key)) {
            switch (key) {
                case ("mark"): {
                    result = MarkDaoImpl.getInstance().getAll();
                    break;
                }
                case ("trans"): {
                    result = TransmissionDaoImpl.getInstance().getAll();
                    break;
                }
                case ("body"): {
                    result = BodyTypeDaoImpl.getInstance().getAll();
                    break;
                }
                default:
                    LOGGER.info(String.format("key %s is not exist", key));
            }
            if (result == null) {
                LOGGER.info(String.format("Parts of %s are not found", key));
            }
        } else {
            LOGGER.info("Key is not valid");
        }
        return result;
    }

    @Override
    public List findModelsByMark(String idMark) {
        List result = null;
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



    @Override
    public void soldCar(String id) {
        if (isValid(id)) {
            int idCar = Integer.valueOf(id);
            CarDaoImpl.getInstance().soldCar(idCar);
        } else {
            LOGGER.info("Car's id is not valid");
        }
    }

    private boolean isValid(String value) {
        return ((value != null) && (!value.isEmpty()));
    }


}
