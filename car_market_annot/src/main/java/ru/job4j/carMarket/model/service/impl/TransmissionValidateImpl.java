package ru.job4j.carMarket.model.service.impl;

import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.dao.impl.TransmissionDaoImpl;
import ru.job4j.carMarket.model.entity.Transmission;
import ru.job4j.carMarket.model.service.CarPartValidate;

import java.util.List;

public class TransmissionValidateImpl implements CarPartValidate<Transmission> {
    private static final CarPartValidate INSTANCE = new TransmissionValidateImpl();
    private static final Logger LOGGER = Logger.getLogger(TransmissionValidateImpl.class);

    private TransmissionValidateImpl() {
    }

    public static CarPartValidate getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Transmission> getCarPart() {
        List<Transmission> result = TransmissionDaoImpl.getInstance().getAll();
        if (result == null) {
            LOGGER.info("Transmissions are not found");
        }
        return result;
    }
}
