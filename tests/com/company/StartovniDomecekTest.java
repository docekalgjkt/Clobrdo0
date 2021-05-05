package com.company;

import com.company.model.BarvaFigurky;
import com.company.model.Figurka;
import com.company.model.StartovniDomecek;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StartovniDomecekTest {


    @Test
    void getFigurkyDoma() {
    }

    @Test
    void nasaditFigurku() {
        BarvaFigurky mojeB = new BarvaFigurky(0, 0, 39, Color.red);

        StartovniDomecek startovniDomecek = new StartovniDomecek(mojeB, 4);
        Figurka nasazenaFigurka = startovniDomecek.nasaditFigurku();
        assertEquals(3, startovniDomecek.getFigurkyDoma().size());
        assertEquals(nasazenaFigurka.getBarva(), mojeB);

        StartovniDomecek startovniDomecek1 = new StartovniDomecek(mojeB, 0);
        assertNull(startovniDomecek1.nasaditFigurku());
    }

    @Test
    void vratitFigurku() {

    }
}