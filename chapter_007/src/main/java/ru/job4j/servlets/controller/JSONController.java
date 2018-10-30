package ru.job4j.servlets.controller;

import ru.job4j.servlets.model.MapStore;
import ru.job4j.servlets.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/chapter_007/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Person person = null;
        StringBuilder sb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        String line;
        try (BufferedReader reader = req.getReader()) {
            line = reader.readLine();
            sb.append(line);
            person = mapper.readValue(sb.toString(), Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MapStore.getInstance().add(person);
    }
}
