package com.company.model;

import java.awt.*;

public class BarvaFigurky {

    private int poradi;
    public int getPoradi() {return poradi;}

    private int startovniPole;
    public int getStartovniPole() {return startovniPole;}

    private int vstupDoCile;
    public int getVstupDoCile() {return vstupDoCile;}

    private Color mojeBarva;
    public Color getMojeBarva() {return mojeBarva;}

    private boolean muzuHrat;
    public boolean getMuzuHrat() {return muzuHrat;}

    public BarvaFigurky(int p, int s, int v, Color c) {
        poradi = p;
        startovniPole = s;
        vstupDoCile = v;
        mojeBarva = c;
        muzuHrat = true;
    }

    public void konecProMe(BarvaFigurky b) {
        if(b == this) {
            muzuHrat = false;
        }
    }

}
