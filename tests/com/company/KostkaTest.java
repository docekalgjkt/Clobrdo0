package com.company;

import com.company.model.Kostka;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KostkaTest {

    @Test
    void hod() {
        int pocetStran = 6;
        Kostka kostka = new Kostka(pocetStran);
        int hodnotaHodu = kostka.hod();
        assertTrue(hodnotaHodu > 0 && hodnotaHodu <= pocetStran);
    }
}