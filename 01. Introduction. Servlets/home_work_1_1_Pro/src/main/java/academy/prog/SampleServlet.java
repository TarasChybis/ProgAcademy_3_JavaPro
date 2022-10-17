package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SampleServlet extends HttpServlet {
    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html"); // Content-Type: text/html

        // MIME types

        PrintWriter pw = resp.getWriter();
        pw.println("<html><head><title>Prog.kiev.ua Test</title></head>");
        pw.println("<body><h1>Hello, Java Junior :)</h1></body></html>");
    }
}
