package ru.job4j.carMarket.controller;

import org.json.JSONObject;
import ru.job4j.carMarket.model.entity.User;
import ru.job4j.carMarket.model.service.impl.UserValidateImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticController extends HttpServlet {
    static final String CONTENT_TYPE = "text/html; charset=windows-1251";
    static final String LOGIN = "login";
    static final String PASSWORD = "password";
    static final String STATUS = "status";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE);
        String login = "null";
        HttpSession session = req.getSession();
        login = (String) session.getAttribute(LOGIN);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String answer = new JSONObject()
                .put(LOGIN, login).toString();
        writer.append(answer);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String answer = "0";
        User result = UserValidateImpl.getInstance().addUser(login, password);
        if (result != null) {
            session.setAttribute(LOGIN, login);
            answer = "1";
        }
        String response = new JSONObject()
                .put(STATUS, answer).toString();
        writer.append(response);
        writer.flush();
    }

}
