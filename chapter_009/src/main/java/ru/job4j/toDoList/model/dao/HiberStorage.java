package ru.job4j.toDoList.model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.toDoList.model.entity.Item;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Override
    public Item add(Item item) {
        item.setId(NEXT_ID.getAndIncrement());
        item.setCreated(new Timestamp(new Date().getTime()));
        System.out.println(item);
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public void update(Item item) {
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Item item) {
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }
    }

    @Override
    public List findAll() {
        try (Session session = this.factory.openSession()) {
            List a = session.createQuery("FROM Item i ORDER BY i.id").list();
            return a;
        }
    }

    @Override
    public void doneItem(int id) {
        try (Session session = this.factory.openSession()) {
            session.beginTransaction();
            Item item = session.load(Item.class, id);
            item.setDone(true);
            session.getTransaction().commit();
        }
    }
}
