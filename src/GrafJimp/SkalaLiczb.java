package GrafJimp;

import java.awt.*;

public class SkalaLiczb {
    public SkalaLiczb(int szerokosc, double min, double max, int x0, int y, Graphics2D g2D) {
        int jednostka = (int) (szerokosc / (max - min));
        int x = x0;
        String s;
        int aktualna = (int) min;
        g2D.setColor(Color.BLACK);

        for(int i = 0; i <= szerokosc; i = i + jednostka) {
            s = Integer.toString(aktualna);
            g2D.drawString(s, x, y);
            x = x + jednostka;
            aktualna = aktualna + 1;
        }
    }
}
