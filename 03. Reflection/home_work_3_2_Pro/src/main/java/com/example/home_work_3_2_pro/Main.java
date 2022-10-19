//Написать класс TextContainer, который содержит в себе строку.<br>
//С помощью механизма аннотаций указать:<br>
//1) в какой файл должен сохраниться текст;<br>
//2) метод, который выполнит сохранение.<br>
//Написать класс Saver, который сохранит поле класса TextContainer в указанный файл.<br>
//<br>
//@SaveTo(path=“c:\\file.txt”)
//class Container {
//&ensp;String text = “…”;
//&ensp;@Saver
//&ensp;public void save(..) {…}
//}

package com.example.home_work_3_2_pro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        @SuppressWarnings("InstantiationOfUtilityClass") TextContainer container = new TextContainer();
        Class<?> cls = container.getClass();

        SaveTo annSaveTo = cls.getAnnotation(SaveTo.class);

        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Saver.class)) method.invoke(container, annSaveTo.path());
        }
    }
}