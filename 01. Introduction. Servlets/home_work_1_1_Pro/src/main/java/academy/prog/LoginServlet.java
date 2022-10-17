package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	static final String TEMPLATE = "<html>" +
			"<head><title>Prog.kiev.ua</title></head>" +
			"<body><h1>%s</h1></body></html>";
	
	static final Map<String, String> cred = new HashMap<String, String>();
	
	static {
		// hardcode login credentials
		cred.put("user", "qwerty");
		cred.put("admin", "qazwsx");
	}

	/*

	POST /login HTTP/1.1
	....
	login=111&pass=1111

	 */

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String login = req.getParameter("login");
		String pass = req.getParameter("password");
		String msg;
		
		String temp = cred.get(login);
		if (pass.equals(temp))
			msg = "Success";
		else
			msg = "Denied";
		
        resp.getWriter().println(String.format(TEMPLATE, msg));
	}
}
