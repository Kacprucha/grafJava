package GrafJimp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Rysowanie extends JPanel {

    private int szerokosc;
    private int wysokosc;
    private Graf graf;
    private JLabel komunikaty;

    private int wysokoscJednostki;
    private int szerokoscJednostki;

    Rysowanie (Graf g, JLabel k) {

        graf = g;
        komunikaty = k;

        this.setPreferredSize(new Dimension(1, 1));
        this.setLayout(null);
    }

    public void setSzerekosc (JPanel p) {
        szerokosc = p.getWidth();
    }
    public int getSzerokosc () {
        return szerokosc;
    }

    public void setWysokosc (JPanel p) {
        wysokosc = p.getHeight();
    }
    public int getWysokosc () {
        return wysokosc;
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        if(graf.getLiczbaWierzchołków() != 0) {
            int mnoznikY = 1;
            int mnoznikX = 0;
            int promien, x, y;

            setSzerekosc(this);
            setWysokosc(this);

            wysokoscJednostki = getWysokosc() / (graf.getKolumny() + 1);
            szerokoscJednostki = getSzerokosc() / (graf.getWiersze() + 1);

            if(wysokoscJednostki > szerokoscJednostki) {
                promien = szerokoscJednostki / 4;
                if(promien > 50) {
                    promien = 50;
                }
            } else {
                promien = wysokoscJednostki / 4;
                if(promien > 50) {
                    promien = 50;
                }
            }

            for(int i = 0; i < graf.getWiersze() * graf.getKolumny(); i++) {
                if(i % graf.getWiersze() == 0) {
                    mnoznikY = 1;
                    mnoznikX++;
                }

                x = (mnoznikX * szerokoscJednostki) - promien;
                y = (mnoznikY * wysokoscJednostki) - promien;

                g2D.fillOval(x, y , promien, promien);

                Wezel w = new Wezel(x, y, promien, i, komunikaty, graf);
                this.add(w);

                mnoznikY++;
            }
        }

    }

    public static class Wezel extends JLabel implements MouseListener {

        private int numer;
        private Graf graf;
        private JLabel komunikaty;

        Wezel(int x, int y, int r ,int n, JLabel kom, Graf g) {
            numer = n;
            komunikaty = kom;
            graf = g;

            this.setBounds(x, y, r ,r);
            this.addMouseListener(this);
        }

        public int getNumer() {
            return numer;
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            komunikaty.setText("Najechano na wezel " + numer);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            komunikaty.setText("graf: " + graf.getKolumny() + "x" + graf.getWiersze());
        }
    }
}
