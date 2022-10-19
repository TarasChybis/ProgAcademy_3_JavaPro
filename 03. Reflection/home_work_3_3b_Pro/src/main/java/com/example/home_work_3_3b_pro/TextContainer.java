package com.example.home_work_3_3b_pro;

import java.io.Serializable;

public class TextContainer implements Serializable {
    private static final long serialVersionUID = 1L;
    @SaveFields
    private String text;


    public String publicText;


    public TextContainer(String text) {
        super();
        this.text = text;
    }

    public TextContainer() {
        super();
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TextContainer [text=" + text + ", publicText=" + publicText + "]";
    }
}
