package ru.job4j.dream.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PhotoDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (File file : Objects.requireNonNull(new File("c:\\images\\").listFiles())) {
            if (req.getParameter("name").equals(file.getName().split("\\.")[0])) {
                file.delete();
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/candidate/candidates.do");
    }
}
