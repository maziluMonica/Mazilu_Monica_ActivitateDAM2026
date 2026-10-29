package com.example.laborator3;

import java.io.Serializable;

public class Marmura implements Serializable {

    private static final long serialVersionUID = 1L;

    private String culoare;
    private String tipMarmura;
    private double pretPeMetruPatrat;
    private String finisaj;
    private boolean esteInStoc;

    public Marmura() {
    }

    public Marmura(String culoare, String tipMarmura, double pretPeMetruPatrat, String finisaj, boolean esteInStoc) {
        this.culoare = culoare;
        this.tipMarmura = tipMarmura;
        this.pretPeMetruPatrat = pretPeMetruPatrat;
        this.finisaj = finisaj;
        this.esteInStoc = esteInStoc;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getTipMarmura() {
        return tipMarmura;
    }

    public void setTipMarmura(String tipMarmura) {
        this.tipMarmura = tipMarmura;
    }

    public double getPretPeMetruPatrat() {
        return pretPeMetruPatrat;
    }

    public void setPretPeMetruPatrat(double pretPeMetruPatrat) {
        this.pretPeMetruPatrat = pretPeMetruPatrat;
    }

    public String getFinisaj() {
        return finisaj;
    }

    public void setFinisaj(String finisaj) {
        this.finisaj = finisaj;
    }

    public boolean isEsteInStoc() {
        return esteInStoc;
    }

    public void setEsteInStoc(boolean esteInStoc) {
        this.esteInStoc = esteInStoc;
    }

    @Override
    public String toString() {
        return "Culoare: " + culoare + ", Tip: " + tipMarmura + ", Pret: " + pretPeMetruPatrat + " LEI, Finisaj: " + finisaj + ", In stoc: " + (esteInStoc ? "Da" : "Nu");
    }
}