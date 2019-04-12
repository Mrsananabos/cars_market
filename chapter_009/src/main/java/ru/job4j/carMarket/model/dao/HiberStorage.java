package ru.job4j.carMarket.model.dao;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import java.util.*;
import java.util.function.Function;

public class HiberStorage implements Storage {
    private static final Logger LOGGER = LogManager.getLogger(HiberStorage.class);
    private static final HiberStorage INSTANCE = new HiberStorage();
    private final SessionFactory factory;

    private HiberStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static HiberStorage getInstance() {
        return INSTANCE;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if (tx.isActive()) {
                tx.commit();
            }
            session.close();
        }
    }

//    @Override
//    public List getCars() {
//        return tx(session -> session.createQuery("SELECT c.mark, c.model, c.transmission, c.bodyType, c.yearIssue, c.price, " +
//                "c.pathImage, u.login, c.isSold, c.id from Car c join fetch User u on c.user.id = u.id").list());
//    }

    @Override
    public List getCars() {
        return tx(session -> session.createQuery("FROM Car").list());
    }

    @Override
    public List getMarks() {
        return tx(session -> session.createQuery("FROM Mark").list());
    }

    @Override
    public List findModelsByMark(int id) {
        return tx(session -> {
            List rsl = session.createQuery("FROM Mark where id = " + id).list();
            return rsl;
        });
    }

    @Override
    public List getTransmission() {
        return tx(session -> session.createQuery("FROM Transmission").list());
    }

    @Override
    public List getBodyType() {
        return tx(session -> session.createQuery("FROM BodyType").list());
    }

    @Override
    public List findCarsByLogin(String login) {
        return tx(session -> {
            List rsl = null;
            User user = this.findUserByLogin(login);
            if (user != null) {
                rsl = user.getCars();
            }
            System.out.println("Машины юзера " + rsl);
            return rsl;
        });
    }

    @Override
    public Car addCarToUser(Car car, String login) {
        return tx(session -> {
            User user = this.findUserByLogin(login);
            System.out.println("Машина добавляется к юзеру " + user.getLogin());
            user.getCars().add(car);
            System.out.println("Машины юзера " + user.getCars());
            session.save(user);
            session.save(car);
            return car;
        });
    }

    @Override
    public User addUser(User user) {
        return tx(session -> {
            session.save(user);
            return user;
        });
    }

    @Override
    public User findUserByLogin(String login) {
        return tx(session -> {
            Query query = session.createQuery("FROM User where login = \'" + login + "\'");
            return (User) query.uniqueResult();
        });
    }

//    @Override
//    public Car addCarToUser(User user, Car car) {
//        user.getCars().add(car);
//        return tx(session -> {
//            session.saveOrUpdate(user);
//            return car;
//        });
//    }
//
//    public static void main(String[] args) {
//        HiberStorage hiberStorage = HiberStorage.getInstance();
//        List<Car> cars = new ArrayList<Car>();
//        List<Car> cars1 = new ArrayList<Car>();
//        User user1 = new User("one", "one", cars);
//        Car car1 = new Car("mark", "model", "trans", "body", 2015, 1233, "/i", user1);
//        User user2 = new User("one", "one", cars1);
//        Car car2 = new Car("mark2", "model2", "trans2", "body2", 2012, 9003, "/i3", user2);
//        Car car3 = new Car("mark245", "model2", "trans2", "body2", 2012, 9003, "/i3", user2);
//        hiberStorage.addUser(user1);
//        hiberStorage.addUser(user2);
//        hiberStorage.addCar(car1);
//        hiberStorage.addCar(car2);
////        hiberStorage.addCarToUser(user1, car3);
////        hiberStorage.addCarToUser(user2, car2);
//        List<Car> car = hiberStorage.getCars();
//        String json;
//        Gson gson = new Gson();
//        json = gson.toJson(car);
//        System.out.println(json);
//
//    }

}
