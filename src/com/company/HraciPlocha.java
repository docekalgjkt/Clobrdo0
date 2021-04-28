package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;

public class HraciPlocha {

    public static HraciPlocha hp;

    private Map<Integer, Figurka> plocha = new HashMap<>();
    private int delkaPlochy;
    public int getDelkaPlochy() {return delkaPlochy;}

    private List<BarvaFigurky> hraci = new ArrayList<>();
    private int pocetHracu;
    public int getPocetHracu() { return pocetHracu; }

    private Map<BarvaFigurky, StartovniDomecek> domecky = new HashMap<>();
    private Map<BarvaFigurky, CilovyDomecek> cile = new HashMap<>();

    private int pocetFigurek;

    private Kostka kostka;

    private BarvaFigurky praveHraje;

    public HraciPlocha(int vel, int pocFig, int pocHracu, Kostka k) {

        if(hp == null) {
            hp = this;
        }
        else {
            return;
        }

        delkaPlochy = vel;
        pocetFigurek = pocFig;
        pocetHracu = pocHracu;
        kostka = k;

        Color[] barvy = {Color.red, new Color(0, 100, 255), Color.green, new Color(255, 220, 0)};

        for(int i = 0; i < pocetHracu; i++) {

            int start = i * 10;
            int cilVstup = spocitanaCesta((i * 10) + 39);

            BarvaFigurky novyHrac = new BarvaFigurky(i, start, cilVstup, barvy[i]);

            hraci.add(novyHrac);

            domecky.put(novyHrac, new StartovniDomecek(novyHrac, pocetFigurek));
            cile.put(novyHrac, new CilovyDomecek(novyHrac, pocetFigurek));
        }

        new GUI();

        startHry();
    }

    private void startHry() {
        praveHraje = hraci.get(0);
        zacatekTahu();
    }

    public int hodKostkou() {

        if(hozeno) return mujHod;

        mujHod = kostka.hod();
        hozeno = true;
        kolikratHozeno++;

        GUI.gui.zapnoutKostku(false);

        if(domecky.get(praveHraje).getFigurkyDoma().size() == 4) {
            if(mujHod != kostka.getPocetStran()) {
                if(kolikratHozeno < 3) {
                    hozeno = false;

                    GUI.gui.zapnoutKostku(true);
                }
                else {
                    konecTahu();
                }
            }
            else {
                GUI.gui.zapnoutNasazeni(praveHraje.getPoradi(), true);
            }
        }
        else if(mujHod == kostka.getPocetStran() && domecky.get(praveHraje).getFigurkyDoma().size() > 0) {
            if(plocha.get(praveHraje.getStartovniPole()) == null || plocha.get(praveHraje.getStartovniPole()).getBarva() != praveHraje) {
                GUI.gui.zapnoutNasazeni(praveHraje.getPoradi(), true);
            }
        }

        return mujHod;
    }

    private int mujHod;
    private int kolikratHozeno;
    private boolean hozeno;

    private void konecTahu() {

        int hraciCoDohrali = 0;
        for (BarvaFigurky b : hraci) {
            if(!b.getMuzuHrat()) hraciCoDohrali++;
        }

        if(hraciCoDohrali == 4) {
            konecHry();
            return;
        }

        int i = praveHraje.getPoradi() + 1;
        praveHraje = hraci.get((i < hraci.size()) ? i : 0);

        if(!praveHraje.getMuzuHrat()) {
            konecTahu();
            return;
        }
        zacatekTahu();
    }

    private void konecHry() {
        // KONEC
    }

    private void zacatekTahu() {
        vybranaFigurka = null;
        hozeno = false;
        kolikratHozeno = 0;

        GUI.gui.zapnoutNasazeni(praveHraje.getPoradi(), false);
        GUI.gui.zapnoutKostku(true);
        GUI.gui.ukazatKdoHraje(praveHraje.getPoradi());
    }

    private int spocitanaCesta(int kam) {

        if(kam < 0) {
            kam += 40;
        }
        else if(kam > delkaPlochy - 1) {
            kam -= delkaPlochy;
        }

        return kam;
    }
    public int getSpocitanaCesta(int kam) {return spocitanaCesta(kam);}

    private Figurka vybranaFigurka;

    private void posunFigurky(Figurka jaka, int kam) {
        int odkud = jaka.getPozice();
        plocha.put(kam, jaka);
        plocha.remove(odkud);
        jaka.setPozice(kam);
    }

    private void vyhodit(Figurka jaka, int kde) {

        domecky.get(jaka.getBarva()).vratitFigurku(jaka);

        GUI.gui.zmenitPocetFigurek(jaka.getBarva().getPoradi(), domecky.get(jaka.getBarva()).getFigurkyDoma().size());

        plocha.remove(kde);
    }


    //region GUI Metody

    public void vyberPole(int pole) {
        if(!hozeno) return;

        Figurka f = plocha.get(pole);

        if(f == null) {
            if(vybranaFigurka != null) {
                if(pole == spocitanaCesta(vybranaFigurka.getPozice() + mujHod)) {

                    GUI.gui.nastavPole(vybranaFigurka.getPozice(), praveHraje.getMojeBarva(), false);

                    posunFigurky(vybranaFigurka, pole);

                    GUI.gui.nastavPole(pole, praveHraje.getMojeBarva(), true);

                    if(mujHod == kostka.getPocetStran() && kolikratHozeno < 3) {
                        zacatekTahu();
                    }
                    else {
                        konecTahu();
                    }


                    GUI.gui.vypniVsechnaPole();
                }
            }
        }
        else if(f.getBarva().equals(praveHraje)) {
            vybranaFigurka = f;
            ukazMoznosti();
        }
        else if(vybranaFigurka != null) {
            if(pole == spocitanaCesta(vybranaFigurka.getPozice() + mujHod)) {
                vyhodit(f, pole);

                GUI.gui.nastavPole(vybranaFigurka.getPozice(), praveHraje.getMojeBarva(), false);

                posunFigurky(vybranaFigurka, pole);

                GUI.gui.nastavPole(pole, praveHraje.getMojeBarva(), true);

                if(mujHod == kostka.getPocetStran() && kolikratHozeno < 3) {
                    zacatekTahu();
                }
                else {
                    konecTahu();
                }

                GUI.gui.vypniVsechnaPole();
            }
        }
    }

    public void vyberCilovehoPole(int poradi, int pole) {
        GUI.gui.vypniVsechnaPole();

        if(!hozeno) return;

        if(poradi == praveHraje.getPoradi()) {
            if(vybranaFigurka != null && cile.get(praveHraje).jeVolno(pole)) {
                if(vybranaFigurka.getVCili()) {
                    GUI.gui.nastavPole(praveHraje.getPoradi(), vybranaFigurka.getPozice(), vybranaFigurka.getBarva().getMojeBarva(), false);

                    cile.get(praveHraje).posunVCili(vybranaFigurka, pole - mujHod, pole);

                    GUI.gui.nastavPole(praveHraje.getPoradi(), vybranaFigurka.getPozice(), vybranaFigurka.getBarva().getMojeBarva(), true);
                }
                else {
                    int odkud = vybranaFigurka.getPozice();

                    if(spocitanaCesta(odkud + mujHod) != pole || spocitanaCesta(praveHraje.getVstupDoCile() - vybranaFigurka.getPozice()) >= kostka.getPocetStran()) return;

                    GUI.gui.nastavPole(odkud, vybranaFigurka.getBarva().getMojeBarva(), false);

                    cile.get(praveHraje).jitDoCile(vybranaFigurka, pole);
                    vybranaFigurka.setvCili(true);

                    GUI.gui.nastavPole(praveHraje.getPoradi(), vybranaFigurka.getPozice(), vybranaFigurka.getBarva().getMojeBarva(), true);

                    plocha.remove(odkud);
                }
                konecTahu();
            }
            else if (vybranaFigurka == null && !cile.get(praveHraje).jeVolno(pole)) {
                vybranaFigurka = cile.get(praveHraje).getCil().get(pole);
                konecTahu();
            }
        }
    }

    public void nasaditFigurku0(int poradi) {

        GUI.gui.vypniVsechnaPole();

        if(praveHraje.getPoradi() != poradi) return;

        if(plocha.get(praveHraje.getStartovniPole()) != null) {
            if(plocha.get(praveHraje.getStartovniPole()).getBarva() != praveHraje) {
                vyhodit(plocha.get(praveHraje.getStartovniPole()), praveHraje.getStartovniPole());
                Figurka nasazenaFig = domecky.get(praveHraje).nasaditFigurku();
                if(nasazenaFig != null) {
                    plocha.put(praveHraje.getStartovniPole(), nasazenaFig);
                    nasazenaFig.setPozice(praveHraje.getStartovniPole());
                    GUI.gui.nastavPole(praveHraje.getStartovniPole(), praveHraje.getMojeBarva(), true);
                    zacatekTahu();
                }
            }
        }
        else {
            Figurka nasazenaFig = domecky.get(praveHraje).nasaditFigurku();
            if(nasazenaFig != null) {
                plocha.put(praveHraje.getStartovniPole(), nasazenaFig);
                nasazenaFig.setPozice(praveHraje.getStartovniPole());
                GUI.gui.nastavPole(praveHraje.getStartovniPole(), praveHraje.getMojeBarva(), true);
                zacatekTahu();
            }
        }

        GUI.gui.zmenitPocetFigurek(praveHraje.getPoradi(), domecky.get(praveHraje).getFigurkyDoma().size());
        GUI.gui.zapnoutNasazeni(praveHraje.getPoradi(), false);
    }

    private void ukazMoznosti() {
        GUI.gui.vypniVsechnaPole();

        if(!hozeno || vybranaFigurka.getBarva() != praveHraje) return;

        int cesta = vybranaFigurka.getPozice() + mujHod;

        boolean muzuHrat = false;

        if(!vybranaFigurka.getVCili()) {
            if(spocitanaCesta(praveHraje.getVstupDoCile() - vybranaFigurka.getPozice()) > kostka.getPocetStran()) {
                if(plocha.get(spocitanaCesta(cesta)) == null) {
                    GUI.gui.zapniPole(spocitanaCesta(cesta));
                    muzuHrat = true;
                }
                else if (plocha.get(spocitanaCesta(cesta)).getBarva() != praveHraje){
                    GUI.gui.zapniPole(spocitanaCesta(cesta));
                    muzuHrat = true;
                }
            }
            else {
                int cestaDoCile = cesta - praveHraje.getVstupDoCile() - 1;
                if(cestaDoCile < 4 && cile.get(praveHraje).jeVolno(cestaDoCile)) {
                    GUI.gui.zapniPole(praveHraje.getPoradi(), cestaDoCile);
                    muzuHrat = true;
                }
            }
        }
        else if(cesta < 4) {
            if(cile.get(praveHraje).jeVolno(cesta)) {
                GUI.gui.zapniPole(praveHraje.getPoradi(), cesta);
                muzuHrat = true;
            }
        }

        if(!muzuHrat) konecTahu();
    }

    //endregion
}