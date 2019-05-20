package ru.job4j.carMarket.model.dao.impl;

import ru.job4j.carMarket.model.dao.CommonCarPartDao;
import ru.job4j.carMarket.model.dao.utils.HiberUtil;
import ru.job4j.carMarket.model.entity.Mark;

import java.util.List;

public class MarkDaoImpl implements CommonCarPartDao<Mark> {
    private static final MarkDaoImpl INSTANCE = new MarkDaoImpl();
    private static final String GET_MARK = "FROM Mark";

    private MarkDaoImpl() {
    }

    public static MarkDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Mark> getAll() {
        return HiberUtil.getInstance().tx(session -> session.createQuery(GET_MARK).list());
    }

}
