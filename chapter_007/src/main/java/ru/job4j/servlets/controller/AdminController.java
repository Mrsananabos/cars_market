package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.Validate;
import ru.job4j.servlets.model.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("service", ValidateService.getInstance());
        req.getRequestDispatcher("/WEB-INF/view/UsersView.jsp").forward(req, resp);
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        ValidateService.getInstance().delete(Integer.valueOf(id));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));

    }


}
