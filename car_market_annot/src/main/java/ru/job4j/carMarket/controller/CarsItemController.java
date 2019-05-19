package ru.job4j.carMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.service.impl.CarValidateImpl;

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
            List<Car> result = CarValidateImpl.getInstance().findCarsByKey(key);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer, result);
            writer.flush();
        } catch (Exception e) {
           LOGGER.error(e.getMessage(), e);
        }
    }
}
