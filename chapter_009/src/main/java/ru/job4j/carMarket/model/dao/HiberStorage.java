package ru.job4j.carMarket.model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import java.util.List;
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
            T rsl = command.apply(session);
            System.out.println(rsl);
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public List getMarks() {
        return tx(session -> {
            return session.createQuery("FROM Mark").list();
        });
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
    public Car addCar(Car car) {
        return tx(session -> {
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

    @Override
    public Car addCarToUser(User user, Car car) {
        user.getCars().add(car);
        return tx(session -> {
            session.saveOrUpdate(user);
            return car;
        });
    }

}
