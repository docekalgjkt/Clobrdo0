package com.company.app;

import com.company.model.HraciPlocha;
import com.company.model.Kostka;

public class Main {

    public static void main(String[] args) {

        Kostka kostka = new Kostka(6);

        new HraciPlocha(40, 4, 4, kostka);
    }
}
