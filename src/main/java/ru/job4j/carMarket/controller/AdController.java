package ru.job4j.carMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.entity.FormCarSale;
import ru.job4j.carMarket.model.service.impl.CarValidateImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import static ru.job4j.carMarket.controller.AuthenticController.CONTENT_TYPE;

public class AdController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdController.class);
    private static final String HTML_NEW_AD = "/newAd.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HTML_NEW_AD).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(CONTENT_TYPE);
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        String line;
        FormCarSale formCar = null;
        try (BufferedReader reader = req.getReader()) {
            line = reader.readLine();
            sb.append(line);
            formCar = mapper.readValue(sb.toString(), FormCarSale.class);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        CarValidateImpl.getInstance().addCarToUser(formCar);
    }
}


