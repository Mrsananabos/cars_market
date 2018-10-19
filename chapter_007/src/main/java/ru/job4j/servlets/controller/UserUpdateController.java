package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.Role;
import ru.job4j.servlets.model.User;
import ru.job4j.servlets.model.Validate;
import ru.job4j.servlets.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("user", ValidateService.getInstance().findById(id));
        req.getRequestDispatcher("/WEB-INF/view/UsersEditView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        String login = req.getParameter("login");
        String role = req.getParameter("role");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        ValidateService.getInstance().update(id, login, role, email, password, address);
        HttpSession session = req.getSession();
        Role curRole = Role.valueOf(String.valueOf(session.getAttribute("role")));
        switch (curRole) {
            case admin: {
                req.setAttribute("users", ValidateService.getInstance().findAll());
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
                break;
            }
            case user: {
                resp.sendRedirect(String.format("%s/user", req.getContextPath()));
                break;
            }

        }

    }



}
