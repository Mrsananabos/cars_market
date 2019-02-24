package ru.job4j.crudServlets.controller;

import ru.job4j.crudServlets.model.User;
import ru.job4j.crudServlets.model.service.Validate;
import ru.job4j.crudServlets.model.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserController extends HttpServlet {
    private final Validate service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = service.findByLogin(String.valueOf(session.getAttribute("login")));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/view/UserView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        service.delete(Integer.valueOf(id));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));

    }

}
