package ru.job4j.carMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carMarket.model.dao.HiberStorage;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.FormCarSale;
import ru.job4j.carMarket.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/newAd.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        HttpSession session = req.getSession();
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        String line;
        Car car = null;
        try (BufferedReader reader = req.getReader()) {
            line = reader.readLine();
            sb.append(line);
            car = mapper.readValue(sb.toString(), Car.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Car from front: \n" + car);
        HiberStorage.getInstance().addCarToUser(car, car.getAuthor());
    }
}


