package ru.job4j.carMarket.model.service.impl;

import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.dao.impl.CarDaoImpl;
import ru.job4j.carMarket.model.dao.impl.UserDaoImpl;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.FormCarSale;
import ru.job4j.carMarket.model.entity.User;
import ru.job4j.carMarket.model.service.CarValidate;

import java.util.List;

public class CarValidateImpl implements CarValidate {
    private static final CarValidate INSTANCE = new CarValidateImpl();
    private static final Logger LOGGER = Logger.getLogger(CarValidateImpl.class);

    public static CarValidate getInstance() {
        return INSTANCE;
    }

    private CarValidateImpl() {
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
    public Car addCarToUser(FormCarSale formCar) {
        if (formCar != null) {
            if (isValid(formCar.getAuthor())) {
                User user = UserDaoImpl.getInstance().findUserByLogin(formCar.getAuthor());
                CarDaoImpl.getInstance().addCarToUser(user, formCar.getCar());
            } else {
                LOGGER.info("Car does't have author");
            }
        } else {
            LOGGER.info("Car is empty");
        }
        return null;
    }

    @Override
    public void soldCarById(String id) {
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
