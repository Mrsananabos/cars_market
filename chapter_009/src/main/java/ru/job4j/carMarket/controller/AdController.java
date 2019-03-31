package ru.job4j.carMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carMarket.model.dao.HiberStorage;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.Mark;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AdController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/newAd.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car();
        try (BufferedReader reader = req.getReader()) {
            String line = reader.readLine();
            System.out.println(line);
            sb.append(line);
            car = mapper.readValue(sb.toString(), Car.class);
            System.out.println(car);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HiberStorage.getInstance().addCar(car);
    }
}
