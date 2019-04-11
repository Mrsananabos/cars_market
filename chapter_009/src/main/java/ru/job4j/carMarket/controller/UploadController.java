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
    final String UPLOAD_DIRECTORY = "0";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        getPath();
        System.out.println(req.getAttribute("OriginURL").toString());
        String fileName = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List fileItems = upload.parseRequest(req);
            Iterator iteraror = fileItems.iterator();
            while (iteraror.hasNext()) {
                FileItem fileItem = (FileItem) iteraror.next();
                if (!(fileItem).isFormField()) {
                    fileName = fileItem.getName();
                    String path = UPLOAD_DIRECTORY + File.separator + fileName;
                    File file = new File(path);
                    System.out.println(file.getAbsolutePath());
                    fileItem.write(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String answer = new JSONObject()
                .put("status", "1").put("url", fileName).toString();
        System.out.println(answer);
        PrintWriter out = resp.getWriter();
        out.print(answer);
    }

    private String getPath() {
        System.out.println("!!!");
        String tomcatBase = System.getProperty("catalina.base");
        String webApp = String.format("%s/webapps", tomcatBase);
        System.out.println(tomcatBase);
        System.out.println(webApp);
        return webApp;
    }

}





