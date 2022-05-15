package GrafJimp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Analizator {
    static private String nazwaPliku;

    public Analizator() {

    }

    public static int czytaj(Graf graf, File wejście){
        try {
            Scanner skanerPliku = new Scanner(wejście);
            String linia = skanerPliku.nextLine();
            Scanner skanerLinii = new Scanner(linia);

            try {
                graf.setKolumny(skanerLinii.nextInt());
                graf.setWiersze(skanerLinii.nextInt());

                graf.setWierzcholki(new Wierzcholek[graf.getKolumny()* graf.getWiersze()]);
                graf.setKrawedzi(new Krawedz[4*graf.getKolumny()* graf.getWiersze()]);

                graf.setLiczbaKrawedzi(0);
                graf.setLiczbaWierzcholkow(0);

                int pozycja = 0;

                for(int i = 0; i < graf.getKolumny() * graf.getWiersze(); i++){
                    linia = skanerPliku.nextLine();
                    String nowaLinia = linia.replaceAll(":", "");

                    System.out.println(nowaLinia);

                    skanerLinii = new Scanner(nowaLinia).useLocale(Locale.US);
                    Wierzcholek wierzcholek = new Wierzcholek(i);

                    for(int j = 0; j < 4; j++) {
                        wierzcholek.setWagiPolaczen(j, graf.getWagaMax() + 1);
                    }

                    System.out.println(skanerLinii.hasNextDouble());
                    System.out.println(skanerLinii.hasNextInt());

                    if(!nowaLinia.isEmpty()) {
                        while (skanerLinii.hasNextDouble() && skanerLinii.hasNextInt()) {
                            int sasiad = skanerLinii.nextInt();

                            System.out.println(sasiad);

                            wierzcholek.dodajSąsiada(sasiad, pozycja);

                            if (!skanerLinii.hasNextDouble()) {
                                System.out.println(skanerLinii.next());
                            }
                            try {
                                double waga = skanerLinii.nextDouble();
                                Krawedz krawedz = new Krawedz(waga);
                                krawedz.dodajPolaczenie(i, sasiad);
                                graf.dodajKrawedz(krawedz);

                                wierzcholek.setWagiPolaczen(pozycja, waga);
                                pozycja++;
                            }catch(NoSuchElementException e){
                                return 1;
                            }
                        }
                    }
                    pozycja = 0;
                    graf.dodajWierzcholek(wierzcholek);
                }

            }
            catch(InputMismatchException e) {
                return 1;
            }
        } catch (FileNotFoundException e) {
            return 2;
        }
        graf.wypiszGraf();
        return 0;
    }

    public static void generuj (Graf graf) {

        graf.setWierzcholki(new Wierzcholek[graf.getKolumny() * graf.getWiersze()]);
        graf.setKrawedzi(new Krawedz[4*graf.getKolumny() * graf.getWiersze()]);

        graf.setLiczbaWierzcholkow(0);
        graf.setLiczbaKrawedzi(0);

        Random r = new Random();

        int pozycja = 0;
        double waga;
        int sasiad;

        for(int i = 0; i < graf.getLiczbaWierzchołków(); i++) {
            
        }

        for( int i = 0; i < graf.getKolumny() * graf.getWiersze(); i++) {
            Wierzcholek wierzcholek = new Wierzcholek(i);

            for(int j = 0; j < 4; j++) {
                wierzcholek.setWagiPolaczen(j, graf.getWagaMax() + 1);
            }

            System.out.println("wierzchołek " + i);
            System.out.println("sąsiedzi:");


            sasiad = i - 1;
            if(sasiad >= 0 && (sasiad + 1) % graf.getWiersze() != 0) {
                wierzcholek.dodajSąsiada(sasiad, pozycja);

                waga = graf.getWagaMin() + (graf.getWagaMax() - graf.getWagaMin()) * r.nextDouble();
                Krawedz krawedz = new Krawedz(waga);
                krawedz.dodajPolaczenie(i, sasiad);
                graf.dodajKrawedz(krawedz);

                wierzcholek.setWagiPolaczen(pozycja, waga);
                pozycja++;

                System.out.print(sasiad + " waga - " + waga + "\n");
            }

            sasiad = i + graf.getWiersze();
            if(sasiad < graf.getKolumny() * graf.getWiersze()) {
                wierzcholek.dodajSąsiada(sasiad, pozycja);

                waga = graf.getWagaMin() + (graf.getWagaMax() - graf.getWagaMin()) * r.nextDouble();
                Krawedz krawedz = new Krawedz(waga);
                krawedz.dodajPolaczenie(i, sasiad);
                graf.dodajKrawedz(krawedz);

                wierzcholek.setWagiPolaczen(pozycja, waga);
                pozycja++;

                System.out.print(sasiad + " waga - " + waga + "\n");
            }

            sasiad = i + 1;
            if(sasiad < graf.getKolumny() * graf.getWiersze() && sasiad % graf.getWiersze() != 0) {
                wierzcholek.dodajSąsiada(sasiad, pozycja);

                waga = graf.getWagaMin() + (graf.getWagaMax() - graf.getWagaMin()) * r.nextDouble();
                Krawedz krawedz = new Krawedz(waga);
                krawedz.dodajPolaczenie(i, sasiad);
                graf.dodajKrawedz(krawedz);

                wierzcholek.setWagiPolaczen(pozycja, waga);
                pozycja++;

                System.out.print(sasiad + " waga - " + waga + "\n");
            }

            sasiad = i - graf.getWiersze();
            if(sasiad >= 0) {
                wierzcholek.dodajSąsiada(sasiad, pozycja);

                waga = graf.getWagaMin() + (graf.getWagaMax() - graf.getWagaMin()) * r.nextDouble();
                Krawedz krawedz = new Krawedz(waga);
                krawedz.dodajPolaczenie(i, sasiad);
                graf.dodajKrawedz(krawedz);

                wierzcholek.setWagiPolaczen(pozycja, waga);
                pozycja++;

                System.out.print(sasiad + " waga - " + waga + "\n");
            }

            pozycja = 0;
            graf.dodajWierzcholek(wierzcholek);

            System.out.println("koniec");
        }

    }
}
