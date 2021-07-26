package ru.job4j.dream.servlet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class RegServletTest {
    Store store = MemStore.instOf();
    HttpServletRequest req = mock(HttpServletRequest.class);
    HttpServletResponse resp = mock(HttpServletResponse.class);
    RequestDispatcher disp = PowerMockito.mock(RequestDispatcher.class);
    HttpSession sess = PowerMockito.mock(HttpSession.class);
    User user;

    @Before
    public void before() {
        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);
        PowerMockito.when(req.getParameter("password1")).thenReturn("0000");
        PowerMockito.when(req.getParameter("password2")).thenReturn("0000");
        PowerMockito.when(req.getParameter("email")).thenReturn("q@q.com");
        PowerMockito.when(req.getParameter("name")).thenReturn("user");
        PowerMockito.when(req.getSession()).thenReturn(sess);
        PowerMockito.when(req.getRequestDispatcher("reg.jsp")).thenReturn(disp);
        user = new User();
        user.setId(0);
        user.setEmail("q@q.com");
        user.setPassword("0000");
        user.setName("user");
    }

    @Test
    public void whenPasswordsNotEquals() throws IOException, ServletException {
        PowerMockito.when(req.getParameter("password2")).thenReturn("0001");
        new RegServlet().doPost(req, resp);
        verify(req).setAttribute(eq("error"), eq("Введенные пароли не совпадают"));
    }

    @Test
    public void whenEmailIsAlreadyUse() throws IOException, ServletException {
        user.setEmail(" ");
        PowerMockito.when(req.getParameter("email")).thenReturn(" ");
        store.saveUser(user);
        new RegServlet().doPost(req, resp);
        verify(req).setAttribute(eq("error"), eq("Введенный email уже используется в системе"));
    }

    @Test
    public void whenRegistrationSuccessful() throws IOException, ServletException {
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        new RegServlet().doPost(req, resp);
        verify(sess).setAttribute(eq("user"), argument.capture());
        User actual = argument.getValue();
        assertEquals(user.getName(), actual.getName());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals(user.getPassword(), actual.getPassword());

    }
}