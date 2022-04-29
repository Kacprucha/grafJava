package GrafJimp;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Czytanie {
    static private String nazwaPliku;

    Czytanie () {

    }

    static void setNazwaPliku(String s) {
        nazwaPliku = s;
    }

    static String getNazwaPliku() {
        return nazwaPliku;
    }

    public static void czytaj(Graf graf){
        File wejscie = new File(String.valueOf(Paths.get(nazwaPliku).toAbsolutePath()));
        try {
            Scanner skanerPliku = new Scanner(wejscie);
            String linia = skanerPliku.nextLine();
            Scanner skanerLinii = new Scanner(linia);

            try {
                graf.setKolumny(skanerLinii.nextInt());
                graf.setWiersze(skanerLinii.nextInt());

                graf.setWierzcholki(new Wierzcholek[graf.getKolumny()* graf.getWiersze()]);
                graf.setKrawedzi(new Krawedz[4*graf.getKolumny()* graf.getWiersze()]);

                int pozycja =0;

                for(int i = 0; i < graf.getKolumny() * graf.getWiersze(); i++) {
                    linia=skanerPliku.nextLine();
                    String nowaLinia = linia.replaceAll(":", "");

                    System.out.println(nowaLinia);

                    skanerLinii = new Scanner(nowaLinia).useLocale(Locale.US);

                    Wierzcholek wierzcholek = new Wierzcholek(i);

                    System.out.println(skanerLinii.hasNextDouble());
                    System.out.println(skanerLinii.hasNextInt());

                    while(skanerLinii.hasNextDouble() && skanerLinii.hasNextInt()){
                        System.out.println("ta pętla się wykonuje");

                        int sasiad =skanerLinii.nextInt();

                        System.out.println(sasiad);

                        wierzcholek.dodajSąsiada(sasiad, pozycja++);

                        System.out.println("dodano wierzchołek");

                        if(!skanerLinii.hasNextDouble()){
                            System.out.println(skanerLinii.next());
                        }

                        double waga = skanerLinii.nextDouble();

                        System.out.println(waga);

                        Krawedz krawedz = new Krawedz(waga);
                        krawedz.dodajPolaczenie(i, sasiad);

                        System.out.println("dodano połączenie");

                        graf.dodajKrawedz(krawedz);
                    }
                    pozycja = 0;
                    graf.dodajWierzcholek(wierzcholek);
                }
            }
            catch(InputMismatchException e) {
                System.out.println(":((((");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się odnaleźć pliku :<");
        }
        graf.wypiszGraf();
    }
}
