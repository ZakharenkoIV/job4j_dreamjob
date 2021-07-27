package ru.job4j.dream.servlet;

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
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class RegServletTest {

    @Test
    public void whenPasswordsNotEquals() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        PowerMockito.when(req.getParameter("password1")).thenReturn("0000");
        PowerMockito.when(req.getParameter("password2")).thenReturn("0001");
        PowerMockito.when(req.getParameter("email")).thenReturn("q@q.com");
        PowerMockito.when(req.getParameter("name")).thenReturn("user");
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher disp = PowerMockito.mock(RequestDispatcher.class);
        PowerMockito.when(req.getRequestDispatcher("reg.jsp")).thenReturn(disp);
        HttpSession sess = PowerMockito.mock(HttpSession.class);
        PowerMockito.when(req.getSession()).thenReturn(sess);
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        new RegServlet().doPost(req, resp);
        verify(req).setAttribute(eq("error"), eq("Введенные пароли не совпадают"));

        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        verify(req).getRequestDispatcher(url.capture());
        verify(disp).forward(any(), any());
        assertEquals("reg.jsp", url.getValue());

    }

    @Test
    public void whenEmailIsAlreadyUse() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        PowerMockito.when(req.getParameter("password1")).thenReturn("0000");
        PowerMockito.when(req.getParameter("password2")).thenReturn("0000");
        PowerMockito.when(req.getParameter("email")).thenReturn("q@q.com");
        PowerMockito.when(req.getParameter("name")).thenReturn("user");
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher disp = PowerMockito.mock(RequestDispatcher.class);
        PowerMockito.when(req.getRequestDispatcher("reg.jsp")).thenReturn(disp);
        HttpSession sess = PowerMockito.mock(HttpSession.class);
        PowerMockito.when(req.getSession()).thenReturn(sess);
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        User user = new User();
        user.setId(0);
        user.setEmail("q@q.com");
        user.setPassword("0000");
        user.setName("user");
        store.saveUser(user);
        new RegServlet().doPost(req, resp);
        verify(req).setAttribute(eq("error"), eq("Введенный email уже используется в системе"));

        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        verify(req).getRequestDispatcher(url.capture());
        verify(disp).forward(any(), any());
        assertEquals("reg.jsp", url.getValue());
    }

    @Test
    public void whenRegistrationSuccessful() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        PowerMockito.when(req.getParameter("password1")).thenReturn("0000");
        PowerMockito.when(req.getParameter("password2")).thenReturn("0000");
        PowerMockito.when(req.getParameter("email")).thenReturn("1@q.com");
        PowerMockito.when(req.getParameter("name")).thenReturn("user");
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher disp = PowerMockito.mock(RequestDispatcher.class);
        PowerMockito.when(req.getRequestDispatcher("reg.jsp")).thenReturn(disp);
        HttpSession sess = PowerMockito.mock(HttpSession.class);
        PowerMockito.when(req.getSession()).thenReturn(sess);
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        new RegServlet().doPost(req, resp);
        verify(sess).setAttribute(eq("user"), argument.capture());
        User actual = argument.getValue();
        User expected = new User();
        expected.setId(0);
        expected.setEmail("1@q.com");
        expected.setPassword("0000");
        expected.setName("user");
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPassword(), actual.getPassword());

        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        verify(resp).sendRedirect(url.capture());
        assertTrue(url.getValue().endsWith("/post/posts.do"));
    }

    @Test
    public void whenDoGetCallThenForwardToRegPage() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher disp = PowerMockito.mock(RequestDispatcher.class);
        PowerMockito.when(req.getRequestDispatcher(anyString())).thenReturn(disp);
        PowerMockito.doNothing().when(disp).forward(any(), any());

        new RegServlet().doGet(req, resp);

        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        verify(req).getRequestDispatcher(url.capture());
        verify(disp).forward(any(), any());
        assertEquals("reg.jsp", url.getValue());
    }
}