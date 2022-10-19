package com.example.home_work_3_3a_pro;

public class TextClass {
    @Save
    private String a;
    private String b;
    @Save
    private String c;
    private String d;
    @Save
    private String e;
    @Save
    private int f = 10;
    public TextClass(String a, String b, String c, String d, String e) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    public TextClass() {
        super();
    }

    @Override
    public String toString() {
        return "TextClass [a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", e=" + e + ", f=" + f + "]";
    }
}
