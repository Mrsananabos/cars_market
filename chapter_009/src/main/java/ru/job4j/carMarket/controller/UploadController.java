package ru.job4j.carMarket.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import ru.job4j.carMarket.model.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class UploadController extends HttpServlet {
    private String pathURL = "image/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        Settings settings = new Settings(this.getServletContext().getRealPath("/WEB-INF/classes"));
        String pathToImg = settings.getValue("ImgPath");
        String fileName = "";
        String status = "0";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List fileItems = upload.parseRequest(req);
            Iterator iterator = fileItems.iterator();
            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!(fileItem).isFormField()) {
                    fileName = fileItem.getName();
                    String path = pathToImg + File.separator + fileName;
                    File file = new File(path);
                    fileItem.write(file);
                    status = "1";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String answer = new JSONObject()
                .put("status", status)
                .put("url", pathURL + fileName).toString();
        PrintWriter out = resp.getWriter();
        out.print(answer);
    }

}





