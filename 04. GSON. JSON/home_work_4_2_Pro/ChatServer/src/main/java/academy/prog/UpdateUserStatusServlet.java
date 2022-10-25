package academy.prog;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class UpdateUserStatusServlet {
    UsersList usersList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        byte[] buff = Utils.requestBodyToArray(request);
        String login = new String(buff, StandardCharsets.UTF_8);

        usersList.add(login, new User(login, "online"));

    }
}
