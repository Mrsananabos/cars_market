package ru.job4j.jsp;

import ru.job4j.jsp.service.Validate;
import ru.job4j.jsp.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserUpdateController extends HttpServlet {
    private final Validate service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("user", service.findById(id));
        req.getRequestDispatcher("/WEB-INF/view/UsersEditView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id =  Integer.valueOf(req.getParameter("id"));
        String login = req.getParameter("login");
        String role = req.getParameter("role");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        service.update(id, login, role, email, password, address);
        req.setAttribute("users", service.findAll());
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
