package ru.job4j.carMarket.controller;

import com.google.gson.Gson;
import ru.job4j.carMarket.model.dao.HiberStorage;
import ru.job4j.carMarket.model.entity.Car;
import ru.job4j.carMarket.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CarsItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setContentType("text/html; charset=windows-1251");
            String key = req.getParameter("data");
            System.out.println("key: " + key);
            Gson gson = new Gson();
            List<Car> result = null;
            if ("all".equals(key)) {
                result = HiberStorage.getInstance().getCars();
            } else {
                result = HiberStorage.getInstance().findCarsByLogin(key);
            }
            System.out.println("alo");
            String json = gson.toJson(result);
            System.out.println("json: " + json);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(json);
            writer.flush();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
