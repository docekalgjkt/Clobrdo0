package com.company;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HraciPlochaTest {

    @Test
    void nasaditFigurku() {}

    @Test
    void posunFigurky() {}

    @Test
    void vyhodit() {
    }

    @Test
    void konecTahu() {}

    @Test
    void spocitanaCesta() {
        Kostka kostka = new Kostka(6);
        HraciPlocha hraciPlocha = new HraciPlocha(40, 4, 4, kostka);

        int kam = new Random().nextInt(hraciPlocha.getDelkaPlochy() + kostka.hod());

        int vysledek = hraciPlocha.getSpocitanaCesta(kam);

        assertTrue(vysledek < 40 && vysledek > 0);
    }

    @Test
    void jeVolno() {}
}