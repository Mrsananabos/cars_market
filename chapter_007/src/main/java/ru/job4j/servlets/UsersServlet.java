package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsersServlet extends HttpServlet {

    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        for (User user : service.findAll()) {
            sb.append("<tr><td>" +
                    user.toString() +
                    "</td>" +
                    "<form action='" + req.getContextPath() + "/list' method='post'>" +
                    " <input type='hidden' name='id' value='" + user.getId() + "'>" +
                    " <input type='hidden' name='action' value='delete'>" +
                    " <td><input type='submit' value='Delete'></td>" +
                    "</form>" +
                    "<form action='" + req.getContextPath() + "/edit' method='get'>" +
                    " <input type='hidden' name='id' value='" + user.getId() + "'>" +
                    " <input type='hidden' name='action' value='edit'>" +
                    " <td><input type='submit' value='Edit'></td>" +
                    "</form>" +
                    "</tr>");
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "<body>" +

                "<table>\n" +

                sb +

                "</table>" +

                "</body>" +
                "</html>");
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        if (action.equals("delete")) {
            String id = req.getParameter("id");
            service.delete(Integer.valueOf(id));
        }
        doGet(req, resp);
    }


}
