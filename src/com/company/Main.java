package com.company;

public class Main {

    public static void main(String[] args) {

        Kostka kostka = new Kostka(6);

        new HraciPlocha(40, 4, 4, kostka);
    }
}
