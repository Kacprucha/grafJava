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
    private Wezel[] tablicaWezlow;

    private boolean BFS = false;
    private int[] tablicaBFS;

    private boolean djikstra = false;

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

    public void kliknientyBFS() {
        BFS = true;
    }
    public void wyczyszczonyBFS() {
        BFS = false;
    }

    public void setTablicaBFS(int[] t) {
        tablicaBFS = t;
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        if(this.tablicaWezlow != null) {
            for(int i = 0; i < this.tablicaWezlow.length; i++) {
                this.remove(this.tablicaWezlow[i]);
            }
        }

        if(graf.getWiersze() != 0) {
            this.tablicaWezlow = new Wezel[graf.getWiersze() * graf.getKolumny()];
        }

        if(graf.getLiczbaWierzchołków() != 0) {
            int mnoznikY = 1;
            int mnoznikX = 0;
            int promien, x, y, xp = 0, yp = 0, xk = 0, yk = 0;
            int[] wspolrzedneX = new int[graf.getWiersze() * graf.getKolumny()];
            int[] wspolrzedneY = new int[graf.getWiersze() * graf.getKolumny()];
            int[] konceKrawedzi;
            int odlegolosc;

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

            for(int i = 0; i < graf.getLiczbaWierzchołków(); i++) {
                if(i % graf.getWiersze() == 0) {
                    mnoznikY = 1;
                    mnoznikX++;
                }

                x = (mnoznikX * szerokoscJednostki) - promien;
                y = (mnoznikY * wysokoscJednostki) - promien;

                wspolrzedneX[i] = x;
                wspolrzedneY[i] = y;

                g2D.fillOval(x, y , promien, promien);

                Wezel w = new Wezel(x, y, promien, i, komunikaty, graf);
                this.tablicaWezlow[i] = w;
                this.add(w);

                mnoznikY++;
            }

            if(BFS) {
                Krawedz[] listaKrawedzi = graf.getKrawedzi();
                Krawedz[] krawedzie = new Krawedz[4];
                int ilosc;
                int[] polaczeniaK;

                odlegolosc = promien / 3;
                promien = promien / 2;
                g2D.setStroke(new BasicStroke((float) (0.5 * odlegolosc)));
                g2D.setColor(wybierzKolor(graf.getWagaMin()));


                for(int i = 0; i < tablicaBFS.length; i++) {
                    int poczatek = tablicaBFS[i];

                    if (poczatek >= 0) {
                        poczatek = i;
                        ilosc = 0;

                        try {
                            for (Krawedz k : listaKrawedzi) {
                                polaczeniaK = k.getPolaczenie();
                                if (polaczeniaK[0] == poczatek) {
                                    krawedzie[ilosc++] = k;
                                }
                            }
                        } catch (NullPointerException ignored) {
                        }

                        for (Krawedz k : krawedzie) {
                            try {
                                konceKrawedzi = k.getPolaczenie();

                                if (konceKrawedzi[1] == konceKrawedzi[0] - 1) {
                                    xp = wspolrzedneX[konceKrawedzi[0]] + promien;
                                    yp = wspolrzedneY[konceKrawedzi[0]];

                                    xk = wspolrzedneX[konceKrawedzi[1]] + promien;
                                    yk = wspolrzedneY[konceKrawedzi[1]] + (2 * promien);
                                }

                                if (konceKrawedzi[1] == konceKrawedzi[0] + graf.getWiersze()) {
                                    xp = wspolrzedneX[konceKrawedzi[0]] + (2 * promien);
                                    yp = wspolrzedneY[konceKrawedzi[0]] + promien;

                                    xk = wspolrzedneX[konceKrawedzi[1]];
                                    yk = wspolrzedneY[konceKrawedzi[1]] + promien;
                                }

                                if (konceKrawedzi[1] == konceKrawedzi[0] + 1) {
                                    xp = wspolrzedneX[konceKrawedzi[0]] + promien;
                                    yp = wspolrzedneY[konceKrawedzi[0]] + (2 * promien);

                                    xk = wspolrzedneX[konceKrawedzi[1]] + promien;
                                    yk = wspolrzedneY[konceKrawedzi[1]];
                                }

                                if (konceKrawedzi[1] == konceKrawedzi[0] - graf.getWiersze()) {
                                    xp = wspolrzedneX[konceKrawedzi[0]];
                                    yp = wspolrzedneY[konceKrawedzi[0]] + promien;

                                    xk = wspolrzedneX[konceKrawedzi[1]] + (2 * promien);
                                    yk = wspolrzedneY[konceKrawedzi[1]] + promien;
                                }

                                g2D.drawLine(xp, yp, xk, yk);
                            } catch (NullPointerException ignored) {
                            }
                        }
                    }
                }

            } else if(djikstra) {

            } else {
                Krawedz[] listaKrawedzi = graf.getKrawedzi();
                odlegolosc = promien / 4;
                promien = promien / 2;
                g2D.setStroke(new BasicStroke((float) (0.5 * odlegolosc)));

                for (int i = 0; i < graf.getLiczbaKrawedzi(); i++) {
                    konceKrawedzi = listaKrawedzi[i].getPolaczenie();

                    if (konceKrawedzi[1] == konceKrawedzi[0] - 1) {
                        xp = wspolrzedneX[konceKrawedzi[0]] + promien - odlegolosc;
                        yp = wspolrzedneY[konceKrawedzi[0]];

                        xk = wspolrzedneX[konceKrawedzi[1]] + promien - odlegolosc;
                        yk = wspolrzedneY[konceKrawedzi[1]] + (2 * promien);

                        g2D.setColor(wybierzKolor(listaKrawedzi[i].getWaga()));
                    }

                    if (konceKrawedzi[1] == konceKrawedzi[0] + graf.getWiersze()) {
                        xp = wspolrzedneX[konceKrawedzi[0]] + (2 * promien);
                        yp = wspolrzedneY[konceKrawedzi[0]] + promien - odlegolosc;

                        xk = wspolrzedneX[konceKrawedzi[1]];
                        yk = wspolrzedneY[konceKrawedzi[1]] + promien - odlegolosc;

                        g2D.setColor(wybierzKolor(listaKrawedzi[i].getWaga()));
                    }

                    if (konceKrawedzi[1] == konceKrawedzi[0] + 1) {
                        xp = wspolrzedneX[konceKrawedzi[0]] + promien + odlegolosc;
                        yp = wspolrzedneY[konceKrawedzi[0]] + (2 * promien);

                        xk = wspolrzedneX[konceKrawedzi[1]] + promien + odlegolosc;
                        yk = wspolrzedneY[konceKrawedzi[1]];

                        g2D.setColor(wybierzKolor(listaKrawedzi[i].getWaga()));
                    }

                    if (konceKrawedzi[1] == konceKrawedzi[0] - graf.getWiersze()) {
                        xp = wspolrzedneX[konceKrawedzi[0]];
                        yp = wspolrzedneY[konceKrawedzi[0]] + promien + odlegolosc;

                        xk = wspolrzedneX[konceKrawedzi[1]] + (2 * promien);
                        yk = wspolrzedneY[konceKrawedzi[1]] + promien + odlegolosc;

                        g2D.setColor(wybierzKolor(listaKrawedzi[i].getWaga()));
                    }

                    g2D.drawLine(xp, yp, xk, yk);
                }
            }
        }

    }

    private Color wybierzKolor(double waga) {
        Color kolor = new Color(0, 0, 0);
        int jednostka;
        int numer;
        int wagaCalkowita;
        int wagaPoPrzecinku;
        int r;
        int g;
        int b;

        jednostka = (int) (1020 / (this.graf.getWagaMax() - this.graf.getWagaMin()));

        if(waga == this.graf.getWagaMin()) {
            kolor = new Color(0, 0, 255);
            return kolor;
        }

        if(waga == this.graf.getWagaMax()) {
            kolor = new Color(255, 0, 0);
            return kolor;
        }

        wagaCalkowita = (int) waga;
        wagaPoPrzecinku = Math.round ( (float) (waga - wagaCalkowita) * jednostka);
        wagaCalkowita = (int) (wagaCalkowita - graf.getWagaMin());
        numer = wagaCalkowita * jednostka + wagaPoPrzecinku;

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
            komunikaty.setText("Wygenerowano graf: " + graf.getKolumny() + "x" + graf.getWiersze());
        }
    }
}
