package ru.job4j.carMarket.model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.job4j.carMarket.model.entity.Mark;
import ru.job4j.toDoList.model.entity.Item;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class HiberStorage implements Storage {
    private static final Logger LOGGER = LogManager.getLogger(HiberStorage.class);
    private static AtomicInteger NEXT_ID = new AtomicInteger(-1);
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
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Mark> getMarks() {
            return tx(session -> session.createQuery("FROM car_marks").list());
    }

    @Override
    public List<String> getTransmission() {
        return tx(session -> session.createQuery("FROM transmission").list());
    }

    @Override
    public List<String> getBodyType() {
        return tx(session -> session.createQuery("FROM body_type").list());
    }
}
