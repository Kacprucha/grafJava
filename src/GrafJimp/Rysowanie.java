package GrafJimp;

import java.awt.*;
import java.util.ArrayList;

public class Rysowanie {

    public static void rysowanieGrafu(Graf graf, int r, int wysokoscJednostki, int szerokoscJednostki, Graphics2D g2D) {
        int promien = r;
        int odlegolosc;
        int[] konceKrawedzi;
        int xp = 0, yp = 0, xk = 0, yk = 0;

        Krawedz[] listaKrawedzi = graf.getKrawedzi();
        int bazowexp, bazoweyp, bazowexk, bazoweyk;
        odlegolosc = promien / 4;
        promien = promien / 2;
        g2D.setStroke(new BasicStroke((float) (0.5 * odlegolosc)));

        for (int i = 0; i < graf.getLiczbaKrawedzi(); i++) {
            konceKrawedzi = listaKrawedzi[i].getPolaczenie();

            bazowexp = ((((listaKrawedzi[i].getPolaczenie()[0] / graf.getWiersze()) + 1) * szerokoscJednostki) - (promien * 2));
            bazoweyp = (((listaKrawedzi[i].getPolaczenie()[0] + 1) - (graf.getWiersze() * ((listaKrawedzi[i].getPolaczenie()[0]) / graf.getWiersze()))) * wysokoscJednostki) - (promien * 2);

            bazowexk = ((((listaKrawedzi[i].getPolaczenie()[1]) / graf.getWiersze()) + 1) * szerokoscJednostki) - (promien * 2);
            bazoweyk = (((listaKrawedzi[i].getPolaczenie()[1] + 1) - (graf.getWiersze() * ((listaKrawedzi[i].getPolaczenie()[1]) / graf.getWiersze()))) * wysokoscJednostki) - (promien * 2);

            if (konceKrawedzi[1] == konceKrawedzi[0] - 1) {
                xp = bazowexp + promien - odlegolosc;
                yp = bazoweyp;

                xk = bazowexk + promien - odlegolosc;
                yk = bazoweyk + (2 * promien);

                g2D.setColor(Kolory.wybierzKolorRGB(listaKrawedzi[i].getWaga(), graf.getWagaMax(), graf.getWagaMin()));
            }

            if (konceKrawedzi[1] == konceKrawedzi[0] + graf.getWiersze()) {
                xp = bazowexp + (2 * promien);
                yp = bazoweyp + promien - odlegolosc;

                xk = bazowexk;
                yk = bazoweyk + promien - odlegolosc;

                g2D.setColor(Kolory.wybierzKolorRGB(listaKrawedzi[i].getWaga(),graf.getWagaMax(), graf.getWagaMin()));
            }

            if (konceKrawedzi[1] == konceKrawedzi[0] + 1) {
                xp = bazowexp + promien + odlegolosc;
                yp = bazoweyp + (2 * promien);

                xk = bazowexk + promien + odlegolosc;
                yk = bazoweyk;

                g2D.setColor(Kolory.wybierzKolorRGB(listaKrawedzi[i].getWaga(), graf.getWagaMax(), graf.getWagaMin()));
            }

            if (konceKrawedzi[1] == konceKrawedzi[0] - graf.getWiersze()) {
                xp = bazowexp;
                yp = bazoweyp + promien + odlegolosc;

                xk = bazowexk + (2 * promien);
                yk = bazoweyk + promien + odlegolosc;

                g2D.setColor(Kolory.wybierzKolorRGB(listaKrawedzi[i].getWaga(), graf.getWagaMax(), graf.getWagaMin()));
            }

            g2D.drawLine(xp, yp, xk, yk);
        }
    }

    public static void rysowanieBFS(Graf graf, int r, int[] tablicaBFS, int wysokoscJednostki, int szerokoscJednostki, Graphics2D g2D) {
        int promien = r;
        int odlegolosc;

        Krawedz[] listaKrawedzi = graf.getKrawedzi();
        Krawedz[] krawedzie = new Krawedz[4];
        int[] konceKrawedzi;
        int ilosc;
        int[] polaczeniaK;
        int xp = 0, yp = 0, xk = 0, yk = 0;

        odlegolosc = promien / 3;
        promien = promien / 2;
        g2D.setStroke(new BasicStroke((float) (0.5 * odlegolosc)));
        g2D.setColor(Kolory.wybierzKolorRGB(graf.getWagaMin(), graf.getWagaMax(), graf.getWagaMin()));

        for(int i = 0; i < tablicaBFS.length; i++) {
            int poczatek = tablicaBFS[i];
            int bazowexp, bazoweyp, bazowexk, bazoweyk, waga;

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

                        waga = Math.max(tablicaBFS[konceKrawedzi[0]], tablicaBFS[konceKrawedzi[1]]);

                        bazowexp = ((((k.getPolaczenie()[0] / graf.getWiersze()) + 1) * szerokoscJednostki) - (promien * 2));
                        bazoweyp = (((k.getPolaczenie()[0] + 1) - (graf.getWiersze() * ((k.getPolaczenie()[0]) / graf.getWiersze()))) * wysokoscJednostki) - (promien * 2);

                        bazowexk = ((((k.getPolaczenie()[1]) / graf.getWiersze()) + 1) * szerokoscJednostki) - (promien * 2);
                        bazoweyk = (((k.getPolaczenie()[1] + 1) - (graf.getWiersze() * ((k.getPolaczenie()[1]) / graf.getWiersze()))) * wysokoscJednostki) - (promien * 2);

                        if (konceKrawedzi[1] == konceKrawedzi[0] - 1) {
                            xp = bazowexp + promien;
                            yp = bazoweyp;

                            xk = bazowexk + promien;
                            yk = bazoweyk + (2 * promien);

                            g2D.setColor(Kolory.wybierzKolorRGB(waga, graf.getKolumny() + graf.getWiersze() - 2, 0));
                        }

                        if (konceKrawedzi[1] == konceKrawedzi[0] + graf.getWiersze()) {
                            xp = bazowexp + (2 * promien);
                            yp = bazoweyp + promien;

                            xk = bazowexk;
                            yk = bazoweyk + promien;

                            g2D.setColor(Kolory.wybierzKolorRGB(waga, graf.getKolumny() + graf.getWiersze() - 2, 0));
                        }

                        if (konceKrawedzi[1] == konceKrawedzi[0] + 1) {
                            xp = bazowexp + promien;
                            yp = bazoweyp + (2 * promien);

                            xk = bazowexk + promien;
                            yk = bazoweyk;

                            g2D.setColor(Kolory.wybierzKolorRGB(waga, graf.getKolumny() + graf.getWiersze() - 2, 0));
                        }

                        if (konceKrawedzi[1] == konceKrawedzi[0] - graf.getWiersze()) {
                            xp = bazowexp;
                            yp = bazoweyp + promien;

                            xk = bazowexk + (2 * promien);
                            yk = bazoweyk + promien;

                            g2D.setColor(Kolory.wybierzKolorRGB(waga, graf.getKolumny() + graf.getWiersze() - 2, 0));
                        }

                        g2D.drawLine(xp, yp, xk, yk);
                    } catch (NullPointerException ignored) {
                    }
                }
            }
        }
    }

    public static void rysowaniejDjikstra(Graf graf, int r, ArrayList<ArrayList<Krawedz>> listaDji, int wysokoscJednostki, int szerokoscJednostki, int koniecDji, Graphics2D g2D) {
        int promien = r;

        int bazowexp, bazoweyp, bazowexk, bazoweyk, odlegolosc;
        int xp = 0, yp = 0, xk = 0, yk = 0;
        int[] konceKrawedzi;

        odlegolosc = promien / 3;
        promien = promien / 2;
        g2D.setStroke(new BasicStroke((float) (0.5 * odlegolosc)));
        g2D.setColor(Kolory.wybierzKolorRGB(graf.getWagaMin(), graf.getWagaMax(), graf.getWagaMin()));

        for(Krawedz k : listaDji.get(koniecDji)) {

            try {
                konceKrawedzi = k.getPolaczenie();

                bazowexp = ((((k.getPolaczenie()[0] / graf.getWiersze()) + 1) * szerokoscJednostki) - (promien * 2));
                bazoweyp = (((k.getPolaczenie()[0] + 1) - (graf.getWiersze() * ((k.getPolaczenie()[0]) / graf.getWiersze()))) * wysokoscJednostki) - (promien * 2);

                bazowexk = ((((k.getPolaczenie()[1]) / graf.getWiersze()) + 1) * szerokoscJednostki) - (promien * 2);
                bazoweyk = (((k.getPolaczenie()[1] + 1) - (graf.getWiersze() * ((k.getPolaczenie()[1]) / graf.getWiersze()))) * wysokoscJednostki) - (promien * 2);

                if (konceKrawedzi[1] == konceKrawedzi[0] - 1) {
                    xp = bazowexp + promien;
                    yp = bazoweyp;

                    xk = bazowexk + promien;
                    yk = bazoweyk + (2 * promien);
                }

                if (konceKrawedzi[1] == konceKrawedzi[0] + graf.getWiersze()) {
                    xp = bazowexp + (2 * promien);
                    yp = bazoweyp + promien;

                    xk = bazowexk;
                    yk = bazoweyk + promien;
                }

                if (konceKrawedzi[1] == konceKrawedzi[0] + 1) {
                    xp = bazowexp + promien;
                    yp = bazoweyp + (2 * promien);

                    xk = bazowexk + promien;
                    yk = bazoweyk;
                }

                if (konceKrawedzi[1] == konceKrawedzi[0] - graf.getWiersze()) {
                    xp = bazowexp;
                    yp = bazoweyp + promien;

                    xk = bazowexk + (2 * promien);
                    yk = bazoweyk + promien;
                }

                g2D.drawLine(xp, yp, xk, yk);
            } catch (NullPointerException ignored) {}
        }
    }
}
