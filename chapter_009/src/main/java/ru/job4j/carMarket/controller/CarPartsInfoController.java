package ru.job4j.carMarket.controller;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.carMarket.model.dao.HiberStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class CarPartsInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        Gson gson = new Gson();
        List result;
        String json;
        String part = req.getParameter("part");
        switch (part) {
            case ("mark"): {
                result = HiberStorage.getInstance().getMarks();
                json = gson.toJson(result);
                break;
            }
            case ("model"): {
                String idMark = req.getParameter("mark");
                result = HiberStorage.getInstance().findModelsByMark(Integer.valueOf(idMark));
                json = gson.toJson(result);
                break;
            }
            case ("trans"): {
                result = HiberStorage.getInstance().getTransmission();
                json = gson.toJson(result);
                break;
            }
            case ("body"): {
                result = HiberStorage.getInstance().getBodyType();
                json = gson.toJson(result);
                break;
            }
            default:
                throw new UnsupportedOperationException(String.format("Part of car : %s not found", part));
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
