package com.example.home_work_3_2_pro;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Retention(value = RetentionPolicy.RUNTIME)
//@interface SaveTo {
//
//    String path();
//}

//@Retention(value = RetentionPolicy.RUNTIME)
//@interface Saver {
//}

@SaveTo(path = "d:\\file.txt")

public class TextContainer {
    private static final String TEXT = "This is some TEXT!";

    @Saver
    public static void save(String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(TEXT);
            System.out.println("Saving: DONE");
        } catch (IOException ex) {
            System.out.println("Saving: ERROR - " + ex.getMessage());
        }
    }
}
