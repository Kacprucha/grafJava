package GrafJimp;

import java.awt.*;

public class Kolory {

    public static Color wybierzKolorRGB(double waga, double wagaMax, double wagaMin) {
        Color kolor = new Color(0, 0, 0);
        int jednostka;
        int numer;
        int wagaCalkowita;
        int wagaPoPrzecinku;
        int r;
        int g;
        int b;

        jednostka = (int) (1020 / (wagaMax - wagaMin));

        if(waga >= wagaMax) {
            kolor = new Color(255, 0, 0);
            return kolor;
        }

        wagaCalkowita = (int) waga;
        wagaPoPrzecinku = Math.round ( (float) (waga - wagaCalkowita) * jednostka);
        wagaCalkowita = (int) (wagaCalkowita - wagaMin);
        numer = wagaCalkowita * jednostka + wagaPoPrzecinku;

        if(waga == wagaMin || wagaCalkowita == wagaMin) {
            kolor = new Color(0, 0, 255);
            return kolor;
        }

        if(numer > 0 && numer <= 255) {
            r = 0;
            g = numer;
            b = 255;

            kolor = new Color(r, g, b);
            return kolor;
        }

        if(numer > 255 && numer <= 510) {
            numer = numer - 255;
            r = 0;
            g = 255;
            b = 255 - numer;

            kolor = new Color(r, g, b);
            return kolor;
        }

        if(numer > 510 && numer <= 765) {
            numer = numer - 510;
            r = numer;
            g = 255;
            b = 0;

            kolor = new Color(r, g, b);
            return kolor;
        }

        if(numer > 765 && numer < 1020) {
            numer = numer - 765;
            r = 255;
            g = 255 - numer;
            b = 0;

            kolor = new Color(r, g, b);
            return kolor;
        }

        return kolor;
    }
}
