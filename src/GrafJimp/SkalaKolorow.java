package GrafJimp;

import java.awt.*;

public class SkalaKolorow {
    public SkalaKolorow(int szerokosc, int wysokosc, int y, Graphics2D g2D) {
        double temp = (double) szerokosc / 241;
        int jednostka = (int) Math.round(temp);
        int x = 0;
        float h = (float) 240 / 360;
        int kat = 240;

        for(int i = szerokosc; i >= 0; i = i - jednostka) {
            g2D.setColor(Color.getHSBColor(h, 1, 1));
            g2D.fillRect(x, y, jednostka, wysokosc);
            x = x + jednostka;
            kat--;
            h = (float) kat / 350;
        }
    }
}
