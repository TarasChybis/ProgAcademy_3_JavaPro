

package com.example.home_work_3_3b_pro;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class Main extends Application {
    public static void main(String[] args) {
        TextContainer text = new TextContainer("This my text.");
        SaverClass.save(text);
    }
}