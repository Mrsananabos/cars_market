package ru.job4j.carMarket.model.dao.impl;

import ru.job4j.carMarket.model.dao.ModelDao;
import ru.job4j.carMarket.model.dao.utils.HiberUtil;
import ru.job4j.carMarket.model.entity.Model;

import java.util.List;

public class ModelDaoImpl implements ModelDao {
    private static final ModelDaoImpl INSTANCE = new ModelDaoImpl();

    private ModelDaoImpl() {
    }

    public static ModelDaoImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Model> findModelsByMark(int id) {
        return HiberUtil.getInstance().tx(session -> {
            List rsl = session.createQuery("FROM Model m where  m.mark.id = " + id).list();
            return rsl;
        });
    }

}
