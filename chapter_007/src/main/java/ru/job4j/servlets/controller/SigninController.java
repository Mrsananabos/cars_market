package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.Role;
import ru.job4j.servlets.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.job4j.servlets.model.Role.admin;
import static ru.job4j.servlets.model.Role.user;

public class SigninController extends HttpServlet {
    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (service.isCredential(login, password)) {
            moveRoleTo(login, req, resp);
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }

    }

    private void moveRoleTo(String login, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String role = service.findRoleByLogin(login);
        session.setAttribute("login", login);
        session.setAttribute("role", role);
        Role curRole = Role.valueOf(role);
        switch (curRole) {
            case admin: response.sendRedirect(String.format("%s/", request.getContextPath()));
            break;

            case user: response.sendRedirect(String.format("%s/user", request.getContextPath()));
            break;
        }
    }

}
