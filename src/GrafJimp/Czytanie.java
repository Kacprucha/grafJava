package GrafJimp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Czytanie {
    static private String nazwaPliku;

    private char c;
    private char poprzedniZnak;
    private int aktualnyWezel, aktualnySasiad, licznikSasiadow;
    private double waga;
    final Scanner skanerPliku;
    final Scanner skanerLinii;
    private String linia;

    Czytanie (Graf graf) {
        this.skanerPliku = new Scanner(nazwaPliku);
        this.linia = skanerPliku.nextLine();
        this.skanerLinii = new Scanner(linia);
        try {
            graf.setKolumny(skanerLinii.nextInt());
            graf.setWiersze(skanerLinii.nextInt());
        }
        catch(InputMismatchException e) {
            System.out.println(":((((");
        }
    }

    static void setNazwaPliku(String s) {
        nazwaPliku = s;
    }

    static String getNazwaPliku() {
        return nazwaPliku;
    }
}
