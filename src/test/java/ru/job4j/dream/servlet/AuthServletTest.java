package ru.job4j.dream.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class AuthServletTest {
    static int id = 0;
    static Store store = MemStore.instOf();
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpServletResponse resp = mock(HttpServletResponse.class);
    HttpSession sess = PowerMockito.mock(HttpSession.class);
    RequestDispatcher disp = PowerMockito.mock(RequestDispatcher.class);
    User user;

    @Before
    public void before() throws ServletException, IOException {
        user = new User();
        user.setEmail("q@q.com");
        user.setPassword("0000");
        if (id == 0) {
            store.saveUser(user);
            id = store.findByUserEmail("q@q.com").getId();
            user.setId(id);
        } else {
            user.setId(id);
            store.saveUser(user);
        }

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);
        PowerMockito.when(req.getSession()).thenReturn(sess);
        PowerMockito.doNothing().when(sess).setAttribute(eq("user"), eq(user));
        PowerMockito.when(req.getParameter("email")).thenReturn("q@q.com");
        PowerMockito.when(req.getParameter("password")).thenReturn("0000");
        PowerMockito.when(req.getRequestDispatcher(any())).thenReturn(disp);
        PowerMockito.doNothing().when(disp).forward(any(), any());
    }

    @Test
    public void whenAuthWithValidUser() throws ServletException, IOException {
        new AuthServlet().doPost(req, resp);
        verify(sess).setAttribute(eq("user"), eq(user));
    }

    @Test
    public void whenAuthWithInvalidUserEmail() throws ServletException, IOException {
        user.setEmail("f");
        store.saveUser(user);
        new AuthServlet().doPost(req, resp);
        verify(req).setAttribute(eq("error"), eq("Не верный email или пароль"));
    }

    @Test
    public void whenAuthWithInvalidUserPassword() throws ServletException, IOException {
        user.setPassword("f");
        store.saveUser(user);
        new AuthServlet().doPost(req, resp);
        verify(req).setAttribute(eq("error"), eq("Не верный email или пароль"));
    }
}