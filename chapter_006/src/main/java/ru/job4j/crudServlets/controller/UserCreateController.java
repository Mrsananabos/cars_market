package ru.job4j.crudServlets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import ru.job4j.crudServlets.model.User;
import ru.job4j.crudServlets.model.service.ValidateService;

public class UserCreateController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


    @Override
     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        User user = null;
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        String line;
        try (BufferedReader reader = req.getReader()) {
            line = reader.readLine();
            sb.append(line);
           user = mapper.readValue(sb.toString(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ValidateService.getInstance().add(user.getLogin(), user.getPassword(), user.getRole().name(), user.getEmail(), user.getCountry(), user.getCity());
    }

}
