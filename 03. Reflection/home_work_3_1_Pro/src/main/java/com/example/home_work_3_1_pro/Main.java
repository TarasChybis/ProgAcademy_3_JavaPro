//Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод, помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
//@Test(a=2, b=5)
//public void test(int a, int b) {…}

package com.example.home_work_3_1_pro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {

        Class<?> ls = TestClass.class;

        Method[] methods = ls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                try {
                    method.invoke(ls, test.a(), test.b());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
