package GrafJimp;

public class Graf {
    private int kolumny;
    private int wiersze;
    private double wagaMax;
    private double wagaMin;

    public double [] [] macierzSasiedztwa;
    public int [] [] BFS;
    public int [] sasiedzi;

    Graf() {
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
}
