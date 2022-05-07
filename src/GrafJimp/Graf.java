package GrafJimp;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Graf {
    private int kolumny;
    private int wiersze;
    private double wagaMax;
    private double wagaMin;

    private int liczbaWierzcholkow=0;
    private int liczbaKrawedzi=0;

    public double [] [] macierzSasiedztwa;
    public int [] [] BFS;
    public int [] sasiedzi;

    private Krawedz [] krawedzi;
    private Wierzcholek [] wierzcholki;

    private File plikZapisu;

    Graf() {
        this.setWagaMax(10);
        this.setWagaMin(0);
    }

    public void setKolumny(int k) {
        kolumny = k;
    }
    public int getKolumny() {
        return kolumny;
    }

    public void setWiersze(int w) {
        wiersze = w;
    }
    public int getWiersze() {
        return wiersze;
    }

    public double getWagaMax() {
        return wagaMax;
    }
    public void setWagaMax(double w) {
        wagaMax = w;
    }

    public double getWagaMin() {
        return wagaMin;
    }
    public void setWagaMin(double w) {
        wagaMin = w;
    }

    public void setWierzcholki(Wierzcholek[] wierzcholki) {
        this.wierzcholki = wierzcholki;
    }
    public Wierzcholek[] getWierzcholki() {
        return wierzcholki;
    }

    public void setKrawedzi(Krawedz[] krawedzi) {
        this.krawedzi = krawedzi;
    }
    public Krawedz[] getKrawedzi() {
        return krawedzi;
    }

    public void setPlikZapisu(File plikZapisu) {
        this.plikZapisu = plikZapisu;
    }

    public String getNazwaPlikZapisu() {
        return plikZapisu.getName();
    }

    public int getLiczbaWierzchołków() { return liczbaWierzcholkow; }
    public void setLiczbaWierzcholkow(int liczbaWierzcholkow) {
        this.liczbaWierzcholkow = liczbaWierzcholkow;
    }

    public void setLiczbaKrawedzi(int liczbaKrawedzi) {
        this.liczbaKrawedzi = liczbaKrawedzi;
    }
    public int getLiczbaKrawedzi() { return liczbaKrawedzi; }


    public void dodajKrawedz(Krawedz krawedz){
        if(liczbaKrawedzi >= krawedzi.length){
            Krawedz[] nKrawedzi = new Krawedz[2* krawedzi.length];
            System.arraycopy(krawedzi,0,nKrawedzi,0, krawedzi.length);
        }
        krawedzi[liczbaKrawedzi++] = krawedz;
    }

    public void dodajWierzcholek(Wierzcholek wierzcholek){
        if(liczbaWierzcholkow >= wierzcholki.length){
            Wierzcholek[] nWierzcholki = new Wierzcholek[2*wierzcholki.length];
            System.arraycopy(wierzcholki,0,nWierzcholki,0,wierzcholki.length);
        }
        wierzcholki[liczbaWierzcholkow++] = wierzcholek;
    }
    public void wypiszGraf(){
        System.out.println("Tablica wierzchołków:");
        for(int i = 0; i < liczbaWierzcholkow; i++){
            System.out.println(wierzcholki[i]);
        }

        System.out.println("Tablica krawędzi:");
        for(int i = 0; i < liczbaKrawedzi; i++){
            System.out.println(krawedzi[i]);
        }
    }

    public void zapiszaGrafDoPliku(JLabel komunikat) {
        try {
            Wierzcholek[] wierzcholeki = this.getWierzcholki();
            Wierzcholek w;

            plikZapisu.createNewFile();

            FileWriter pisarz = new FileWriter(plikZapisu);
            pisarz.write(this.getKolumny() + " " + this.getWiersze() + "\n");

            for(int i = 0; i < this.liczbaWierzcholkow; i++) {
                w = wierzcholeki[i];

                for(int j = 0; j < 4; j++) {
                    if(w.getKonkretnyNumerSasiada(j) != -1) {
                        pisarz.write(w.getKonkretnyNumerSasiada(j) + " :" + w.getKokretnaWagePolaczenia(j) + "\t");
                    }
                }

                pisarz.write("\n");
            }

            pisarz.close();

        } catch (IOException e) {
            komunikat.setText("Wystąpił błąd! Zapisywanie grafu zostało wstrzymane.");
            e.printStackTrace();
        }

    }
}
