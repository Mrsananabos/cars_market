package ru.job4j.carMarket.controller;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

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
    final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = "";
        String path = req.getSession().getServletContext().getRealPath(File.separator + UPLOAD_DIRECTORY);
        System.out.println(path);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        System.out.println(UPLOAD_DIRECTORY);
        try {
            List fileItems = upload.parseRequest(req);
            Iterator iteraror = fileItems.iterator();
            while (iteraror.hasNext()) {
                FileItem fileItem = (FileItem) iteraror.next();
                if (!(fileItem).isFormField()) {
                    fileName = fileItem.getName();
                    File file = new File(path + File.separator + fileName);
                    System.out.println(file.getAbsolutePath());
                    fileItem.write(file);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String answer = new JSONObject()
                .put("status", "1").put("url", fileName).toString();
        PrintWriter out = resp.getWriter();
        out.print(answer);
    }

}





