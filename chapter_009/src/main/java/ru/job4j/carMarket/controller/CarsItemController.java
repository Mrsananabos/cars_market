package ru.job4j.carMarket.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CarsItemController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CarsItemController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=windows-1251");
        try {
            String key = req.getParameter("data");
            System.out.println("login: " + key);
            List result = ValidateService.getInstance().findCarsByKey(key);
            String json = new Gson().toJson(result);
            System.out.println("json: " + json);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(json);
            writer.flush();
        } catch (Exception e) {
           LOGGER.error(e.getMessage(), e);
        }
    }
}
