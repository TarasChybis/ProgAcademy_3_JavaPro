package com.example.home_work_3_3b_pro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Arrays;

public class SaverClass {
    public SaverClass() {
        super();

    }

    public static void save(Object obj) {
        Class<?> cl = obj.getClass();
        Arrays.stream(cl.getDeclaredFields()).filter(a -> a.isAnnotationPresent(SaveFields.class)).peek(a -> {
            if (Modifier.isPrivate(a.getModifiers())) {
                a.setAccessible(true);
            }
        }).forEach(a -> {
            try {
                saveTofile(a.get(obj), a.getAnnotation(SaveFields.class).path());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void saveTofile(Object str, String fileName) {
        File file = new File(fileName);
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file, true))) {
            br.write(String.valueOf(str) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}