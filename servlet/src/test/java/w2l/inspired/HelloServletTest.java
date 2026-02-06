package w2l.inspired;


import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import jakarta.servlet.http.HttpServletRequest;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloServletTest {
    @Test
    void testGet()throws IOException {
       // HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
       // HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn (writer);
        HelloServlet servlet = new HelloServlet();
        servlet.doGet(request,response);
        Assertions.assertEquals("<h2> hello servlet</h2>",stringWriter.toString().trim());

    }
    @Test
    void testPut()throws IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        String myName = "user1";
        when(request.getParameter("name")).thenReturn(myName);
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn (writer);
        HelloServlet servlet = new HelloServlet();
        servlet.doPut(request,response);
        Assertions.assertTrue(stringWriter.toString().trim().contains("Hello, "+myName));
        Assertions.assertEquals("{\"greeting\": \"Hello, user1\"}",stringWriter.toString().trim());
    }
}
