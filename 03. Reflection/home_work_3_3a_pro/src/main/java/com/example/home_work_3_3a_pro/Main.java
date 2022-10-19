package com.example.home_work_3_3a_pro;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        TextClass tc = new TextClass("One", "Two", "Three", "Four", "Five");
        SerialisableClass<TextClass> sc = new SerialisableClass<TextClass>(tc,new File("text.txt"));
        sc.save();

        TextClass tcLoad = sc.load();
        System.out.println(tcLoad);
    }
}