package ru.job4j.crudServlets.controller;

import com.google.gson.Gson;
import ru.job4j.crudServlets.model.GeoPoint;
import ru.job4j.crudServlets.model.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public class JSONController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        Gson gson = new Gson();
        Collection<GeoPoint> result = new ArrayList<>();
        String json = toString();
        String action = req.getParameter("action");
        switch (action) {
            case ("country"): {
                result = ValidateService.getInstance().getCountries();
                json = gson.toJson(result);
                break;
            }
            case ("city"): {
                String country = req.getParameter("country");
                result = ValidateService.getInstance().getCitiesByCountry(country);
                json = gson.toJson(result);
                break;
            }
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Person person = null;
//        StringBuilder sb = new StringBuilder();
//        ObjectMapper mapper = new ObjectMapper();
//        String line;
//        try (BufferedReader reader = req.getReader()) {
//            line = reader.readLine();
//            sb.append(line);
//            person = mapper.readValue(sb.toString(), Person.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        MapStore.getInstance().add(person);
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        writer.append(mapper.writeValueAsString("SERVLETa"));
//        writer.flush();
    }


}
