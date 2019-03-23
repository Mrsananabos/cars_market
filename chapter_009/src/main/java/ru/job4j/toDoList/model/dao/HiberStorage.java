package ru.job4j.toDoList.model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
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
    public Item add(Item item) {
        item.setId(NEXT_ID.getAndIncrement());
        item.setCreated(new Timestamp(new Date().getTime()));
        return tx(session -> {
            session.save(item);
            return item;
        });
    }

    @Override
    public Item update(Item item) {
       return tx(session -> {
            session.update(item);
            return item;
        });
    }

    @Override
    public void delete(Item item) {
        tx(session -> {
            session.delete(item);
            return null;
        });
    }

    @Override
    public List findAll() {
        return tx(session -> session.createQuery("FROM Item i ORDER BY i.id").list());
    }

    @Override
    public void doneItem(int id) {
        tx(session -> {
            Item item = session.get(Item.class, id);
            item.setDone(true);
            return null;
        });
    }
}
