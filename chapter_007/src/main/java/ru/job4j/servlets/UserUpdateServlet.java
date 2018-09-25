package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserUpdateServlet extends HttpServlet {

    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        User currentUser = service.findById(Integer.valueOf(id));
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "<body>" +
                "<form action='"+ req.getContextPath() + "/edit' method='post'>" +
                "New name: <input type='text' name='name' value='" + currentUser.getName() + "'/><br>" +
                "New login: <input type='text' name='login' value='" + currentUser.getLogin() + "'/><br>" +
                "New email: <input type='text' name='email' value='" + currentUser.getEmail() + "'/><br>" +
                " <input type='hidden' name='id' value='" + req.getParameter("id") + "'>" +
                "<input type='submit' value='Add'>" +
                "</form>" +
                "</body>" +
                "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        int id =  Integer.valueOf(req.getParameter("id"));
        User user = new User(id, name, login, email);
        service.update(user);
    }



}
