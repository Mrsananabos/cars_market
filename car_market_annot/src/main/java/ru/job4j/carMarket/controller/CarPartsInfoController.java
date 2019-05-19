package ru.job4j.carMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carMarket.model.service.Validate;
import ru.job4j.carMarket.model.service.ValidateServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CarPartsInfoController extends HttpServlet {
    private final Validate service = ValidateServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        List result;
        String part = req.getParameter("part");
        if (part.equals("model")) {
            String idMark = req.getParameter("mark");
            result = service.findModelsByMark(idMark);
        } else {
            result = service.findPartsCarByKey(part);
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, result);
        writer.flush();
    }

}
