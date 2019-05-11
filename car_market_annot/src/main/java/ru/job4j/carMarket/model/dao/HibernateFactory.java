package ru.job4j.carMarket.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {

    public static final SessionFactory FACTORY = new Configuration()
            .configure()
            .buildSessionFactory();
}
