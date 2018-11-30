package ru.job4j.servlets.controller;

import com.google.gson.Gson;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.model.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class UsersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        int id =  Integer.valueOf(req.getParameter("id"));
        User user;
        user = ValidateService.getInstance().findById(id);
        Gson gson = new Gson();
        String json = toString();
        json = gson.toJson(user);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        Collection<User> users = new ArrayList<>();
        users = ValidateService.getInstance().findAll();
        Gson gson = new Gson();
        String json = toString();
        json = gson.toJson(users);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }


}
