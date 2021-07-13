package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String password = req.getParameter("password1");
        if (!password.equals(req.getParameter("password2"))) {
            req.setAttribute("error", "Введенные пароли не совпадают");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }
        String email = req.getParameter("email");
        if (PsqlStore.instOf().findByUserEmail(email) != null) {
            req.setAttribute("error", "Введенный email уже используется в системе");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }
        User newUser = new User();
        newUser.setId(0);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setName(req.getParameter("name"));
        PsqlStore.instOf().saveUser(newUser);
        req.getSession().setAttribute("user", newUser);
        resp.sendRedirect(req.getContextPath() + "/post/posts.do");
    }
}
