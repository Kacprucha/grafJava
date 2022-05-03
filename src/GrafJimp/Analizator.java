package GrafJimp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Analizator {
    static private String nazwaPliku;

    Analizator() {

    }

    static void setNazwaPliku(String s) {
        nazwaPliku = s;
    }

    static String getNazwaPliku() {
        return nazwaPliku;
    }

    public static int czytaj(Graf graf, File wejście){
        //File wejście = new File(String.valueOf(Paths.get(nazwaPliku).toAbsolutePath()));
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
                int pozycja =0;
                for(int i =0;i< graf.getKolumny()*graf.getWiersze();i++){
                    linia=skanerPliku.nextLine();
                    String nowaLinia=linia.replaceAll(":", "");
                    System.out.println(nowaLinia);
                    skanerLinii = new Scanner(nowaLinia).useLocale(Locale.US);
                    Wierzcholek wierzcholek =new Wierzcholek(i);
                    System.out.println(skanerLinii.hasNextDouble());
                    System.out.println(skanerLinii.hasNextInt());
                    if(!nowaLinia.isEmpty()) {
                        while (skanerLinii.hasNextDouble() && skanerLinii.hasNextInt()) {
                            int sasiad = skanerLinii.nextInt();
                            System.out.println(sasiad);
                            wierzcholek.dodajSąsiada(sasiad, pozycja++);
                            if (!skanerLinii.hasNextDouble()) {
                                System.out.println(skanerLinii.next());
                            }
                            try {
                                double waga = skanerLinii.nextDouble();
                                Krawedz krawedz = new Krawedz(waga);
                                krawedz.dodajPolaczenie(i, sasiad);
                                graf.dodajKrawedz(krawedz);
                            }catch(NoSuchElementException e){
                                return 1;
                            }
                        }
                    }
                    pozycja=0;
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
}
