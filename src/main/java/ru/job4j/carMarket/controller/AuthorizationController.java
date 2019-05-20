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

import static ru.job4j.carMarket.controller.AuthenticController.LOGIN;
import static ru.job4j.carMarket.controller.AuthenticController.PASSWORD;
import static ru.job4j.carMarket.controller.AuthenticController.STATUS;

public class AuthorizationController extends HttpServlet {
    private static final String HTML_ENTER = "/enter.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HTML_ENTER).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String rsl = "0";
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        User user = UserValidateImpl.getInstance().findUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            rsl = "1";
            session.setAttribute(LOGIN, login);
        }
        String answer = new JSONObject()
                .put(STATUS, rsl).toString();
        writer.append(answer);
        writer.flush();
    }


}
