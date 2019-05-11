package ru.job4j.carMarket.model.dao.impl;

import org.hibernate.query.Query;
import ru.job4j.carMarket.model.dao.UserDao;
import ru.job4j.carMarket.model.dao.utils.HiberUtil;
import ru.job4j.carMarket.model.entity.User;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public User addUser(User user) {
        return HiberUtil.getInstance().tx(session -> {
            session.save(user);
            return user;
        });
    }

    @Override
    public User findUserByLogin(String login) {
        return HiberUtil.getInstance().tx(session -> {
            Query query = session.createQuery("FROM User where login = :userName");
            query.setParameter("userName", login);
            return (User) query.uniqueResult();
        });
    }
}
