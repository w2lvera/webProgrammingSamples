package w2l.inspired;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("<h2> hello servlet</h2>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        try {
            PrintWriter writer = response.getWriter();
            writer.println("{\"greeting\": \"Hello\"}");
        }catch(IOException ex) {
            throw ex;
        }
    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response)throws IOException{
        String name = request.getParameter("name");
        response.setContentType("application/json");
        try {
            PrintWriter writer = response.getWriter();
            writer.println("{\"greeting\": \"Hello, "+ name +"}");
        }catch(IOException ex) {
            throw ex;
        }
    }

}

