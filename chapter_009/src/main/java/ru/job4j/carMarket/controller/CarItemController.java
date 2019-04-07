package ru.job4j.carMarket.controller;

import com.google.gson.Gson;
import ru.job4j.carMarket.model.dao.HiberStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CarItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        System.out.println("start get cars");
        Gson gson = new Gson();
        List result = HiberStorage.getInstance().getCars();
        System.out.println(result);
        String json = gson.toJson(result);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        System.out.println(json);
        writer.append(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
