package ru.job4j.carMarket.controller;

import org.json.JSONObject;
import ru.job4j.carMarket.model.entity.User;
import ru.job4j.carMarket.model.service.impl.UserValidateImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        String login = "null";
        HttpSession session = req.getSession();
        login = (String) session.getAttribute("login");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String answer = new JSONObject()
                .put("login", login).toString();
        writer.append(answer);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String answer = "0";
        User result = UserValidateImpl.getInstance().addUser(login, password);
        if (result != null) {
            session.setAttribute("login", login);
            answer = "1";
        }
        String response = new JSONObject()
                .put("status", answer).toString();
        writer.append(response);
        writer.flush();
    }

}
