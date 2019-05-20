package ru.job4j.carMarket.controller;

import ru.job4j.carMarket.model.service.impl.CarValidateImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarIsSoldController extends HttpServlet {
    private static final String ID = "id";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CarValidateImpl.getInstance().soldCarById(req.getParameter(ID));
    }
}
