package ru.job4j.carMarket.model.dao.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Function;

public class HiberUtil {
    private static final Logger LOGGER = LogManager.getLogger(HiberUtil.class);
    private static final HiberUtil INSTANCE = new HiberUtil();
    private static StandardServiceRegistry registry;
    private SessionFactory session;

    private HiberUtil() {
    }

    public static HiberUtil getInstance() {
        return INSTANCE;
    }

    private SessionFactory getSessionFactory() {
        if (session == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();
                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                // Create SessionFactory
                session = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return session;
    }

    public  <T> T tx(final Function<Session, T> command) {
        Session session = this.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
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

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
