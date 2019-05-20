package ru.job4j.carMarket.model.dao.impl;

import ru.job4j.carMarket.model.dao.CommonCarPartDao;
import ru.job4j.carMarket.model.dao.utils.HiberUtil;
import ru.job4j.carMarket.model.entity.Transmission;

import java.util.List;

public class TransmissionDaoImpl implements CommonCarPartDao<Transmission> {
    private static final TransmissionDaoImpl INSTANCE = new TransmissionDaoImpl();
    private static final String GET_TRANS = "FROM Transmission";

    private TransmissionDaoImpl() {
    }

    public static TransmissionDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Transmission> getAll() {
        return HiberUtil.getInstance().tx(session -> session.createQuery(GET_TRANS).list());
    }

}
