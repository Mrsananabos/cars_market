package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.job4j.servlets.User;

public class UserServlet extends HttpServlet {

    private final ValidateService service = ValidateService.getInstance();


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter writer = new PrintWriter(res.getOutputStream());
        writer.append(service.findAll().toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                String name = req.getParameter("name");
                String login = req.getParameter("login");
                String email = req.getParameter("email");
                User user = new User(name, login, email);
                service.add(user);
                break;
            case "update":
                int id = Integer.valueOf(req.getParameter("id"));
                String name1 = req.getParameter("name");
                String login1 = req.getParameter("login");
                String email1 = req.getParameter("email");
                User updateUser = new User(id, name1, login1, email1);
                service.update(updateUser);
                break;
            case "delete":
                int id1 = Integer.valueOf(req.getParameter("id"));
                service.delete(id1);
                break;
            case "findById":
                int id2 = Integer.valueOf(req.getParameter("id"));
                User byId = null;
                byId = service.findById(id2);
                PrintWriter writer = new PrintWriter(resp.getOutputStream());
                writer.append("Found user is " + byId.toString());
                writer.flush();
                break;
            default:
                break;
        }
        doGet(req, resp);
    }
}
