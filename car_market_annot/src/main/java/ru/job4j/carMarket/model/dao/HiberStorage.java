package ru.job4j.carMarket.model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carMarket.model.entity.*;

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
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOGGER.error("error", e);
            throw e;
        } finally {
            if (tx.isActive()) {
                tx.commit();
            }
            session.close();
        }
    }

    @Override
    public List<Car> getCars() {
        return tx(session -> session.createQuery("SELECT c.mark, c.model, c.transmission, c.bodyType, c.yearIssue, c.price, " +
                "c.pathImage, u.login, c.isSold, c.id from Car c join fetch User u on c.user.id = u.id ORDER BY c.id").list());
    }

    @Override
    public List<Mark> getMarks() {
        return tx(session -> session.createQuery("FROM Mark").list());
    }

    @Override
    public List<Model> findModelsByMark(int id) {
        return tx(session -> {
            List rsl = session.createQuery("FROM Mark where id = " + id).list();
            return rsl;
        });
    }

    @Override
    public List<Transmission> getTransmission() {
        return tx(session -> session.createQuery("FROM Transmission").list());
    }

    @Override
    public List<BodyType> getBodyType() {
        return tx(session -> session.createQuery("FROM BodyType").list());
    }

    @Override
    public User addUser(User user) {
        return tx(session -> {
            session.save(user);
            return user;
        });
    }

    @Override
    public Car addCarToUser(User user, Car car) {
        return tx(session -> {
            car.setUser(user);
           session.save(car);
            return car;
        });
    }

    @Override
    public User findUserByLogin(String login) {
        return tx(session -> {
            Query query = session.createQuery("FROM User where login = :userName");
            query.setParameter("userName", login);
            return (User) query.uniqueResult();
        });
    }

    @Override
    public void soldCar(int id) {
        tx(session -> {
            Car car = session.load(Car.class, id);
            car.setIsSold(true);
            return null;
        });
    }

}
