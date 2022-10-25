package academy.prog;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LogOutServlet {
    UsersList usersList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        byte[] buff = Utils.requestBodyToArray(request);
        String login = new String(buff, StandardCharsets.UTF_8);

        usersList.getUsers().put(login, new User(login,"offline"));
    }
}
