package com.example.laborator8;

import android.graphics.Bitmap;

public class ImaginiMarmura {
    private String textAfisat;
    private Bitmap imagine;
    private String link;

    public ImaginiMarmura() {
    }

    public ImaginiMarmura(String textAfisat, String link, Bitmap imagine) {
        this.textAfisat = textAfisat;
        this.link = link;
        this.imagine = imagine;
    }

    public String getTextAfisat() {
        return textAfisat;
    }

    public void setTextAfisat(String textAfisat) {
        this.textAfisat = textAfisat;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
