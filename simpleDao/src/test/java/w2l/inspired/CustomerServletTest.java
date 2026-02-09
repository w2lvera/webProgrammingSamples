package w2l.inspired;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import w2l.inspired.web.CustomerServlet;

import java.io.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerServletTest {
    @Test
    void testGet()throws IOException {
        // HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        // HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn (writer);
        CustomerServlet servlet = new CustomerServlet();
        servlet.init();
        servlet.doGet(request,response);
        writer.flush();
        Assertions.assertTrue(stringWriter.toString().trim().contains("<form method=\"post\" "));

    }
    @Test
    void testPost()throws IOException {
        // HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        // HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringReader stringReader = new StringReader("1=on&2=on&3=on&4=on");
        BufferedReader  reader = new BufferedReader(stringReader);
        when(request.getReader()).thenReturn (reader);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn (writer);
        CustomerServlet servlet = new CustomerServlet();
        servlet.init();
        servlet.doPost(request,response);
        writer.flush();
        Assertions.assertTrue(stringWriter.toString().trim().contains("<h2> Customers with prefix P = 2</h2>"));
        //   Assertions.assertEquals("<h2> hello servlet</h2>",stringWriter.toString().trim());

    }
}
