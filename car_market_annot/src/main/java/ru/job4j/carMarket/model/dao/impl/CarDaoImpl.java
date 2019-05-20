package ru.job4j.carMarket.model.dao.impl;

import ru.job4j.carMarket.model.dao.CarDao;
import ru.job4j.carMarket.model.dao.utils.HiberUtil;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import java.util.List;

public class CarDaoImpl implements CarDao {
    private static final CarDaoImpl INSTANCE = new CarDaoImpl();
    private static final String GET_CAR_WITH_OWNERS = "SELECT c.mark, c.model, c.transmission, c.bodyType, c.yearIssue, c.price, c.pathImage, u.login, c.isSold, " +
            "c.id from Car c join fetch User u on c.owner.id = u.id ORDER BY c.id";

    private CarDaoImpl() {
    }

    public static CarDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Car> getCars() {
        return HiberUtil.getInstance().tx(session -> session.createQuery(GET_CAR_WITH_OWNERS).list());
    }

    @Override
    public Car addCarToUser(User user, Car car) {
        return HiberUtil.getInstance().tx(session -> {
            car.setOwner(user);
            session.save(car);
            return car;
        });
    }

    @Override
    public void soldCar(int id) {
        HiberUtil.getInstance().tx(session -> {
            Car car = session.load(Car.class, id);
            car.setIsSold(true);
            return null;
        });
    }
}
