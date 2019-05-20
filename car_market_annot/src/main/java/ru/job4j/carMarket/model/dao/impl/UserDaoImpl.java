package ru.job4j.carMarket.model.dao.impl;

import org.hibernate.query.Query;
import ru.job4j.carMarket.model.dao.UserDao;
import ru.job4j.carMarket.model.dao.utils.HiberUtil;
import ru.job4j.carMarket.model.entity.User;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    private static final String FIND_USERNAME_BY_LOGIN = "FROM User where login = :userName";
    private static final String USERNAME = "userName";

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
            Query query = session.createQuery(FIND_USERNAME_BY_LOGIN);
            query.setParameter(USERNAME, login);
            return (User) query.uniqueResult();
        });
    }
}
