package ru.job4j.carMarket.controller;

import org.json.JSONObject;
import ru.job4j.carMarket.model.dao.HiberStorage;
import ru.job4j.carMarket.model.entity.User;

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
        HttpSession session = req.getSession();
        String login = "null";
        User user = (User) session.getAttribute("user");
        if (user != null) {
            login = user.getLogin();
        }
        System.out.println("К сессии привязан: " + login);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String answer = new JSONObject()
                .put("login", login).toString();
        writer.append(answer);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String rsl = "0";
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = HiberStorage.getInstance().findUserByLogin(login);
        if (user == null) {
            rsl = "1";
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);
            HiberStorage.getInstance().addUser(newUser);
            session.setAttribute("user", newUser);
        }
        String answer = new JSONObject()
                .put("status", rsl).toString();
        writer.append(answer);
        writer.flush();
    }

}
