package com.example.home_work_3_3a_pro;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface Save {
    String path() default "saveText.txt";
}
