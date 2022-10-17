package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "qwerty7890";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        int age = 0;

        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Age must be number");
            response.sendRedirect("index.jsp");
            return;
        }

        if(age < 18){
            session.setAttribute("error", "Age must be 18 or over");
            response.sendRedirect("index.jsp");
            return;
        }

        if(!password.matches("\\A(?=\\S*?[0-9@#$%^&+=])(?=\\S*?[a-z])(?=\\S*?[A-Z])\\S{10,}\\z")){
            session.setAttribute("error", "Password must be at least 10 characters long <br>" +
                    " contain at least one uppercase and lowercase letter <br>" +
                    " contain no spaces");
            response.sendRedirect("index.jsp");
            return;
        }


        if (LOGIN.equals(login) && PASS.equals(password)) {
            session = request.getSession(true);
            session.setAttribute("user_login", login);
            response.sendRedirect("index.jsp");
            return;
        }
        session.setAttribute("error", "Login or password entered incorrectly");
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null)){
            session.removeAttribute("user_login");
            session.removeAttribute("error");
        }

        response.sendRedirect("index.jsp");
    }
}
