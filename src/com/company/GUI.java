package com.company;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class GUI extends JFrame {

    public static GUI gui;

    private JButton[] policka = new JButton[40];

    private JButton[][] cile = new JButton[4][4];
    public JButton[][] getCile() {return cile;}

    private JButton kostka = new JButton();
    public JButton getKostka() {return kostka;}
    public void zapnoutKostku(boolean b) { kostka.setEnabled(b); }

    private JButton[] nasazovaciTlacitka = new JButton[4];
    public JButton[] getNasazovaciTlacitka() {return nasazovaciTlacitka;}
    public void zapnoutNasazeni(int i, boolean b) {
        nasazovaciTlacitka[i].setEnabled(b);
        nasazovaciTlacitka[i].setBackground((b) ? Color.lightGray : Color.white);
    }

    private JTextArea[] poctyFigurek = new JTextArea[4];
    public JTextArea[] getPoctyFigurek() {return poctyFigurek;}
    public void zmenitPocetFigurek(int a, int b) { poctyFigurek[a].setText(String.format("%s F", b)); }

    public GUI() {

        if(gui == null) gui = this;
        else return;

        this.setTitle("Clobrdo");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new GridLayout(11, 11, 5, 5));

        Color[] barvy = {Color.red, new Color(0, 100, 255), Color.green, new Color(255, 220, 0)};

        for(int i = 0; i < HraciPlocha.hp.getPocetHracu(); i++) {
            poctyFigurek[i] = new JTextArea("4 F");
            poctyFigurek[i].setBackground(Color.LIGHT_GRAY);
            poctyFigurek[i].setForeground(barvy[i]);
            poctyFigurek[i].setEditable(false);
            poctyFigurek[i].setMargin(new Insets(10, 8, 0, 0));
            poctyFigurek[i].setFont(new Font(poctyFigurek[0].getName(), Font.BOLD, 32)); // 27

            nasazovaciTlacitka[i] = new JButton("NASADIT");
            nasazovaciTlacitka[i].setMargin(new Insets(0, 0, 0, 0));
            nasazovaciTlacitka[i].setBackground(Color.white);
            nasazovaciTlacitka[i].setEnabled(false);
            nasazovaciTlacitka[i].setFocusable(false);
            nasazovaciTlacitka[i].setFont(new Font(poctyFigurek[0].getName(), Font.BOLD, 12)); // 10
            int i0 = i;
            nasazovaciTlacitka[i].addActionListener(e -> {
                HraciPlocha.hp.nasaditFigurku0(i0);
            });

            for(int ii = 0; ii < 4; ii++) {
                cile[i][ii] = new JButton();
                cile[i][ii].setBackground(Color.white);
                cile[i][ii].setFocusable(false);
                cile[i][ii].setBorder(new MatteBorder(2, 2, 2, 2, barvy[i]));
                cile[i][ii].setFont(new Font(cile[i][ii].getName(), Font.BOLD, 50));
                int ii1 = ii;
                int i1 = i;
                cile[i][ii].addActionListener(e -> {
                    HraciPlocha.hp.vyberCilovehoPole(i1, ii1);
                });
            }
        }

        kostka.setText("0");
        kostka.setForeground(Color.black);
        kostka.setBackground(Color.white);
        kostka.setFont(new Font(kostka.getName(), Font.BOLD, 40));
        kostka.setFocusable(false);
        kostka.setMargin(new Insets(0, 0, 0, 0));
        kostka.addActionListener(e -> {
            int hod = HraciPlocha.hp.hodKostkou();
            kostka.setText(String.valueOf(hod));
        });

        for (int y = 0; y < 11; y++) {
            for (int x = 0; x < 11; x++) {
                int pole = Design.getDeska()[y][x];
                if(pole > 0) {
                    policka[pole - 1] = new JButton();
                    policka[pole - 1].setFont(new Font(policka[pole - 1].getName(), Font.BOLD, 50));
                    policka[pole - 1].setBackground(Color.white);
                    policka[pole - 1].setMargin(new Insets(0, 0, 0, 0));
                    policka[pole - 1].setFocusable(false);

                    int xx = x, yy = y;
                    policka[pole - 1].addActionListener(e -> {
                        HraciPlocha.hp.vyberPole(pole - 1);
                    });

                    if((pole - 1) % 10 == 0 && (pole - 1) / 10 < HraciPlocha.hp.getPocetHracu()) {
                        policka[pole - 1].setBorder(new MatteBorder(4, 4, 4, 4, barvy[(pole - 1) / 10]));
                    }

                    if(pole % 10 == 0 && pole / 10 <= HraciPlocha.hp.getPocetHracu()) {
                        policka[pole - 1].setBorder(new MatteBorder(1, 1, 1, 1, barvy[HraciPlocha.hp.getSpocitanaCesta(pole) / 10]));
                    }

                    this.add(policka[pole - 1]);
                }
                else {
                    if(Design.getDeskaButtons()[y][x] != null) {
                        this.add(Design.getDeskaButtons()[y][x]);
                    }
                    else {
                        this.add(new JPanel());
                    }
                }
            }
        }

        this.setVisible(true);
    }

    public void nastavPole(int kde, Color barva, boolean b) {
        if(b) {
            policka[kde].setText("i");
            policka[kde].setForeground(barva);
        }
        else {
            policka[kde].setText("");
        }
    }

    public void ukazatKdoHraje(int kdo) {
        for (JTextArea t : poctyFigurek) {
            if(t.getBackground() != Color.lightGray) t.setBackground(Color.lightGray);
        }
        poctyFigurek[kdo].setBackground(Color.darkGray);
    }

    public void nastavPole(int koho, int kde, Color barva, boolean b) {
        if(b) {
            cile[koho][kde].setText("i");
            cile[koho][kde].setForeground(barva);
        }
        else {
            cile[koho][kde].setText("");
        }
    }

    public void zapniPole(int i) {
        policka[i].setBackground(Color.lightGray);
    }

    public void zapniPole(int koho, int i) {
        cile[koho][i].setBackground(Color.lightGray);
    }

    public void vypniVsechnaPole() {
        for (JButton b : policka) {
            if(b.getBackground() != Color.white) b.setBackground(Color.white);
        }
        for (int y = 0; y < 4; y++) {
            for (JButton b : cile[y]) {
                if(b.getBackground() != Color.white) b.setBackground(Color.white);
            }
        }
    }

}