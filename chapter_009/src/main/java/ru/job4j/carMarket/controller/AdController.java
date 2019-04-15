package ru.job4j.carMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carMarket.model.entity.FormCarSale;
import ru.job4j.carMarket.model.service.ValidateService;

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
        String line;
        FormCarSale formCar = null;
        try (BufferedReader reader = req.getReader()) {
            line = reader.readLine();
            sb.append(line);
            formCar = mapper.readValue(sb.toString(), FormCarSale.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Car from front: \n" + formCar);
        ValidateService.getInstance().addCarToUser(formCar);
    }
}


