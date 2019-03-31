package ru.job4j.carMarket.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class UploadServlet extends HttpServlet {
    File file;
    String path = this.getClass().getClassLoader().getResource("photo/1/").getPath();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/newAd.html").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("tut");
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        ServletFileUpload upload = new ServletFileUpload(factory);
//        try {
//            System.out.println("hh");
//            List fileItems = upload.parseRequest(req);
//            Iterator iteraror = fileItems.iterator();
//            while (iteraror.hasNext()) {
//                FileItem fileItem = (FileItem) iteraror.next();
//                if (!(fileItem).isFormField()) {
//                    String fileName = fileItem.getName();
//                    file = new File(path + fileName);
//                    fileItem.write(file);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        PrintWriter out = resp.getWriter();
        out.print("{\"status\":1}");
    }

}




