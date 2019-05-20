package ru.job4j.carMarket.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
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

import static ru.job4j.carMarket.controller.AuthenticController.CONTENT_TYPE;
import static ru.job4j.carMarket.controller.AuthenticController.STATUS;

public class UploadController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UploadController.class);

    private static final String HTML_INDEX = "/index.html";
    private static final String PATH_URL = "image/";
    private static final String REAL_PATH = "/WEB-INF/classes";
    private static final String KEY = "ImgPath";
    private static final String URL = "url";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(HTML_INDEX).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(CONTENT_TYPE);
        Settings settings = new Settings(this.getServletContext().getRealPath(REAL_PATH));
        String pathToImg = settings.getValue(KEY);
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
            LOGGER.error(e.getMessage(), e);
        }
        String answer = new JSONObject()
                .put(STATUS, status)
                .put(URL, PATH_URL + fileName).toString();
        PrintWriter out = resp.getWriter();
        out.print(answer);
    }

}





