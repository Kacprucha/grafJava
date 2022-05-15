package Testy;

import GrafJimp.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AnalizatorTest {
    File f = new File("test1");
    Analizator analizator;
    Graf graf;

    @BeforeEach
    void setUp() {
        analizator = new Analizator();
        graf = new Graf();
    }

    @AfterEach
    void tearDown() {
        analizator = null;
        graf = null;
    }


    @Test
    void czytaj() {
        analizator.czytaj(graf, f);
        int[] poprawneSassiedzi = {1, 0, 1, 0, 1, 2, 3, 4, 5};
        double[] poprawneWagi = {1.232, 2.233, 1.232, 0.485, 7.583, 0.485, 6.593, 2.233, 2.69, 9.515, 7.583, 2.69, 10.0, 3.467, 6.593, 10.0, 3.234, 9.515, 7.383, 3.467, 7.383, 8.487, 3.234, 8.487};
        for (int i = 0; i < graf.getLiczbaWierzchołków(); i++) {
            assertEquals(graf.getWierzcholki()[i].getNumerySąsiadów()[0], poprawneSassiedzi[i]);
        }
        for (int i = 0; i < graf.getLiczbaKrawedzi(); i++) {
            assertEquals(graf.getKrawedzi()[i].getWaga(), poprawneWagi[i]);
        }
    }

    @Test
    void generuj() {
        graf.setKolumny(100);
        graf.setWiersze(100);
        analizator.generuj(graf);
        double wagaMin = graf.getKrawedzi()[0].getWaga();
        double wagaMax = graf.getKrawedzi()[0].getWaga();
        for (int i = 1; i < graf.getLiczbaKrawedzi(); i++) {
            if (graf.getKrawedzi()[i].getWaga() > wagaMax) {
                wagaMax = graf.getKrawedzi()[i].getWaga();
            }
            if (graf.getKrawedzi()[i].getWaga() < wagaMin) {
                wagaMin = graf.getKrawedzi()[i].getWaga();
            }
        }
        assert (wagaMax > 9.9);
        assert (wagaMin < 0.1);
        int iteracja = 0;
        for (Wierzcholek w : graf.getWierzcholki()) {
            if (iteracja < graf.getLiczbaWierzchołków()) {
                assertNotEquals(w, null);
                iteracja++;
                continue;
            }
            assertNull(w);
            iteracja++;
        }
        iteracja = 0;
        for (Krawedz k : graf.getKrawedzi()) {
            if (iteracja < graf.getLiczbaKrawedzi()) {
                assertNotEquals(k, null);
                iteracja++;
                continue;
            }
            assertNull(k);
            iteracja++;
        }
    }
}
