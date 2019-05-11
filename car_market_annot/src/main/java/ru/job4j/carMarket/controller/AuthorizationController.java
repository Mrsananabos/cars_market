package ru.job4j.carMarket.controller;

import org.json.JSONObject;
import ru.job4j.carMarket.model.entity.User;
import ru.job4j.carMarket.model.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthorizationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/enter.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String rsl = "0";
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = ValidateService.getInstance().findUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            rsl = "1";
            session.setAttribute("login", login);
        }
        String answer = new JSONObject()
                .put("status", rsl).toString();
        writer.append(answer);
        writer.flush();
    }


}
