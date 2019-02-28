package ru.job4j.toDoList.controller;

import com.google.gson.Gson;
import ru.job4j.toDoList.model.entity.Item;
import ru.job4j.toDoList.model.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class ItemInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        Collection<Item> users = ValidateService.getInstance().findAll();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
