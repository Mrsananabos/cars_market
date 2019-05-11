package ru.job4j.carMarket.model.dao.impl;

import ru.job4j.carMarket.model.dao.CommonCarPartDao;
import ru.job4j.carMarket.model.dao.utils.HiberUtil;
import ru.job4j.carMarket.model.entity.BodyType;

import java.util.List;

public class BodyTypeDaoImpl implements CommonCarPartDao<BodyType> {
    private static final BodyTypeDaoImpl INSTANCE = new BodyTypeDaoImpl();

    private BodyTypeDaoImpl() {
    }

    public static BodyTypeDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<BodyType> getAll() {
        return HiberUtil.getInstance().tx(session -> session.createQuery("FROM BodyType").list());
    }

}
