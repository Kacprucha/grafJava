package GrafJimp;

public class Graf {
    static private int kolumny;
    static private int wiersze;
    static private double wagaMax;
    static private double wagaMin;

    public double [] [] macierzSasiedztwa;
    public int [] [] BFS;
    public int [] sasiedzi;

    Graf() {
    }

    static void setKolumny(int k) {
        kolumny = k;
    }
    static int getKolumny() {
        return kolumny;
    }

    static void setWiersze(int w) {
        wiersze = w;
    }
    static int getWiersze() {
        return wiersze;
    }

    static double getWagaMax() {
        return wagaMax;
    }
    static void setWagaMax(double w) {
        wagaMax = w;
    }

    static double getWagaMin() {
        return wagaMin;
    }
    static void setWagaMin(double w) {
        wagaMin = w;
    }
}
