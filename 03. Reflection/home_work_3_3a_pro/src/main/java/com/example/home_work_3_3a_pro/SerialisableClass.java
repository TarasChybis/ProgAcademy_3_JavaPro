package com.example.home_work_3_3a_pro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class SerialisableClass<T> {
    private T cl;
    private File file = new File("default");

    public SerialisableClass(T cl) {
        super();
        this.cl = cl;
    }

    public SerialisableClass(T cl, File file) {
        super();
        this.cl = cl;
        this.file = file;
    }

    public void save() {
        Class<?> cls = this.cl.getClass();
        Field[] fields = cls.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                field.setAccessible(true);
                try {
                    sb.append(field.getName() + ":" + String.valueOf(field.get(cl)) + ",");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        serialisable(sb);
    }

    private void serialisable(StringBuilder k) {

        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(k);
        } catch (IOException e) {
            e.printStackTrace();
        }

        readSerialisable(file);
    }

    private String[] readSerialisable(File file) {
        String[] ss = null;
        if (!file.isFile()) {
            System.out.println("File not found.");
            return null;
        }
        try (FileInputStream fos = new FileInputStream(file); ObjectInputStream oos = new ObjectInputStream(fos)) {
            try {
                ss = oos.readObject().toString().split(",");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ss;
    }

    public T load() {
        return deSerialisable(readSerialisable(this.file));
    }

    private T deSerialisable(String[] str) {

        Map<String, String> map = new HashMap<>();
        Class<?> c = this.cl.getClass();
        Field[] fields = c.getDeclaredFields();
        for (String s : str) {
            map.put((s.substring(0, s.lastIndexOf(":"))), (s.substring(s.lastIndexOf(":") + 1, s.length())));
        }

        return setFields(fields, map, getConstructor(c));

    }

    private T getConstructor(Class<?> cl) {
        T constructor = null;
        try {
            constructor = (T) cl.newInstance() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return constructor;
    }

    private T setFields(Field[] fields, Map<String, String> map, T constructor) {

        for (Field field : fields) {
            for (String keys : map.keySet()) {
                if (keys.equals(field.getName())) {
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                        try {
                            if (field.getType() == int.class) {
                                field.set(constructor, Integer.parseInt(map.get(keys))  );
                                continue;
                            }
                            field.set(constructor, map.get(keys));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return constructor;
    }
}