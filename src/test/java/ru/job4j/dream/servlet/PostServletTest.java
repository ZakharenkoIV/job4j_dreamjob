package ru.job4j.dream.servlet;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Post;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {

    @Test
    public void whenCreatePost() throws IOException {
        Store store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.when(req.getParameter("id")).thenReturn("0");
        PowerMockito.when(req.getParameter("name")).thenReturn("n");

        new PostServlet().doPost(req, resp);

        Post result = null;
        for (Post post : store.findAllPosts()) {
            result = post;
        }
        Assert.assertNotNull(result);
        Assert.assertThat(result.getName(), Is.is("n"));

        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        verify(resp).sendRedirect(url.capture());
        assertTrue(url.getValue().endsWith("/post/posts.do"));
    }

    @Test
    public void whenDoGetCallThenForwardToPostsPage() throws ServletException, IOException {
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        RequestDispatcher disp = PowerMockito.mock(RequestDispatcher.class);
        PowerMockito.when(req.getRequestDispatcher(anyString())).thenReturn(disp);
        PowerMockito.doNothing().when(disp).forward(any(), any());
        HttpSession sess = PowerMockito.mock(HttpSession.class);
        PowerMockito.when(req.getSession()).thenReturn(sess);

        new PostServlet().doGet(req, resp);

        ArgumentCaptor<String> url = ArgumentCaptor.forClass(String.class);
        verify(req).getRequestDispatcher(url.capture());
        verify(disp).forward(any(), any());
        assertEquals("posts.jsp", url.getValue());
    }
}
