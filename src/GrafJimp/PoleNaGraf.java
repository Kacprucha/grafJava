package GrafJimp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PoleNaGraf extends JPanel {

    private int szerokosc;
    private int wysokosc;
    private JLabel skala = new JLabel();

    private final Graf graf;
    private final JLabel komunikaty;
    private final Interfejs interfejs;

    private boolean BFS = false;
    private int[] tablicaBFS;

    private boolean djikstra = false;
    private ArrayList<ArrayList<Krawedz>> listaDji;
    private int koniecDji;
    private int poczatekDji;
    private double[] odleglosci;

    public PoleNaGraf(Graf g, JLabel k, Interfejs inte) {

        graf = g;
        komunikaty = k;
        interfejs = inte;

        this.setPreferredSize(new Dimension(inte.getWidth(), inte.getHeight()));
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

    public void kliknientyDji() {
        djikstra = true;
    }
    public void wyczyszczonyDji() {
        djikstra = false;
    }

    public void setKoniecDji(int k) {
        koniecDji = k;
    }
    public int getKoniecDji() {
        return koniecDji;
    }
    public void setPoczatekDji(int p) {
        poczatekDji = p;
    }
    public int getPoczatekDji() {
        return poczatekDji;
    }
    public void setListaDji(ArrayList<ArrayList<Krawedz>> l) {
        listaDji = l;
    }

    public double[] getOdleglosci() {
        return odleglosci;
    }
    public void setOdleglosci(double[] odleglosci) {
        this.odleglosci = odleglosci;
    }

    public void paint(Graphics g) {
        int BAZAOWY_PROMIEN = 50;
        int WYSOKOSC_SKALI = 50;

        Graphics2D g2D = (Graphics2D) g;

        this.removeAll();

        new SkalaKolorow(this.getWidth(), 25, this.getHeight() - 50, g2D);
        g2D.setColor(Color.BLACK);

        g2D.drawRect(0, this.getHeight() - 50, this.getWidth(), 50);

        /*
        skala.setBounds(100, 100, 100, 100);
        skala.setBackground(Color.BLUE);
        skala.setHorizontalTextPosition(JLabel.CENTER);
        skala.setFont(new Font("Times New Roma", Font.PLAIN, 11));
        skala.setText("dupa");
        this.add(skala);
        skala.setText("jfsnjfnsejfnsjfnsjbfjksbfeskjfbsekfjsebfjbsefbsekkjfjsek");
        */

        if(graf.getLiczbaWierzchołków() != 0) {
            int mnoznikY = 1;
            int mnoznikX = 0;
            int promien, x, y;

            setSzerekosc(this);
            setWysokosc(this);

            int wysokoscJednostki = (this.getWysokosc() - WYSOKOSC_SKALI) / (graf.getWiersze() + 1);
            int szerokoscJednostki = this.getSzerokosc() / (graf.getKolumny() + 1);

            if(wysokoscJednostki > szerokoscJednostki) {
                promien = szerokoscJednostki / 4;
            } else {
                promien = wysokoscJednostki / 4;
            }

            if(promien > BAZAOWY_PROMIEN) {
                promien = BAZAOWY_PROMIEN;
            }

            for(int i = 0; i < graf.getLiczbaWierzchołków(); i++) {
                if(i % graf.getWiersze() == 0) {
                    mnoznikY = 1;
                    mnoznikX++;
                }

                x = (mnoznikX * szerokoscJednostki) - promien;
                y = (mnoznikY * wysokoscJednostki) - promien;

                g2D.fillOval(x, y , promien, promien);

                Wezel w = new Wezel(x, y, promien, i, komunikaty, graf, this, interfejs);
                this.add(w);

                mnoznikY++;
            }

            if(BFS) {

                Rysowanie.rysowanieBFS(graf, promien, tablicaBFS, wysokoscJednostki, szerokoscJednostki, g2D);

            } else if(djikstra) {

                Rysowanie.rysowaniejDjikstra(graf, promien, listaDji, wysokoscJednostki, szerokoscJednostki, koniecDji, g2D);

            } else {

                Rysowanie.rysowanieGrafu(graf, promien, wysokoscJednostki, szerokoscJednostki, g2D);
            }
        }

    }

    public static class Wezel extends JLabel implements MouseListener {

        private final int numer;
        private final Graf graf;
        private final JLabel komunikaty;
        private final PoleNaGraf poleNaGraf;
        private final Interfejs interfejs;

        Wezel(int x, int y, int r , int n, JLabel kom, Graf g, PoleNaGraf rys, Interfejs inte) {
            numer = n;
            komunikaty = kom;
            graf = g;
            poleNaGraf = rys;
            interfejs = inte;


            this.setBounds(x, y, r ,r);
            this.addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(poleNaGraf.djikstra) {
                poleNaGraf.setKoniecDji(numer);

                interfejs.repaint();
                komunikaty.setText("Ustawiono koniec Djikstry na wierzchołek " + numer);
            }
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
            if(poleNaGraf.BFS) {
                if(graf.isSpojny()) {
                    komunikaty.setText("Graf jest spójny");
                } else {
                    komunikaty.setText("Graf nie jest spójny");
                }
            } else if (poleNaGraf.djikstra) {
                komunikaty.setText("Najkrótsza droga z wierzchołka " + poleNaGraf.getPoczatekDji() + " do wierzchołka "  + poleNaGraf.getKoniecDji() + " wynosi " + poleNaGraf.getOdleglosci()[poleNaGraf.getKoniecDji()]);
            } else {
                komunikaty.setText("Wygenerowano graf: " + graf.getKolumny() + "x" + graf.getWiersze());
            }
        }
    }
}
