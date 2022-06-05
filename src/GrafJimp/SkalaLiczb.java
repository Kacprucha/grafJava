package GrafJimp;

import java.awt.*;

public class SkalaLiczb {
    public SkalaLiczb(int szerokosc, double min, double max, int x0, int y, Graphics2D g2D) {
        int jednostka = (int) Math.round((szerokosc - x0) / (max - min + 1));
        int jednostka0 = jednostka;
        int mnoznik = 1;
        int x = x0;
        String s;
        int aktualna = (int) min;
        g2D.setColor(Color.BLACK);

        if(jednostka == 0) {
            jednostka = 1;
            jednostka0 = 1;
        }

        while(jednostka <= ((szerokosc - x0) / 20)) {
            mnoznik++;
            jednostka = jednostka + jednostka0;
        }


        for(; aktualna <= max; aktualna = aktualna + mnoznik) {
            s = Integer.toString(aktualna);
            g2D.drawString(s, x, y);
            x = x + jednostka;
        }

    }
}
