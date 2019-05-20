package ru.job4j.carMarket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carMarket.model.service.impl.ModelValidateImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static ru.job4j.carMarket.controller.AuthenticController.CONTENT_TYPE;

public class ModelController extends HttpServlet {

    private static final String MARK = "mark";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE);
        String idMark = req.getParameter(MARK);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, ModelValidateImpl.getInstance().findModelsByMark(idMark));
        writer.flush();
    }
}
