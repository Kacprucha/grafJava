package GrafJimp;

import java.util.Arrays;

public class Wierzcholek {
    private int numer;
    private int[] numerySasiadow = new int[4];
    private double[] wagiPolaczen = new double[4];

    public Wierzcholek(int numer) {
        this.numer = numer;
        for(int i =0;i<4;i++){
            numerySasiadow[i] = -1;
        }
    }

    public Wierzcholek(Wierzcholek w){
        this.numer=w.getNumer();
        this.numerySasiadow= Arrays.copyOf(w.getNumerySąsiadów(), 4);
        this.wagiPolaczen= Arrays.copyOf(w.getWagiPolaczen(), 4);
    }

    public void setNumerSasiada(int numerSasiada, int wartosc) {
        this.numerySasiadow[numerSasiada] = wartosc;
    }
    public int[] getNumerySąsiadów() {
        return numerySasiadow;
    }
    public int getKonkretnyNumerSasiada(int i) {
        return numerySasiadow[i];
    }

    public int getNumer() {
        return numer;
    }

    public void dodajSąsiada(int numerSasiada, int pozycja){
        numerySasiadow[pozycja]=numerSasiada;
    }

    public void setWagiPolaczen(int i, double w) {
        this.wagiPolaczen[i] = w;
    }
    public double[] getWagiPolaczen() {
        return wagiPolaczen;
    }
    public double getKokretnaWagePolaczenia(int i) {
        return wagiPolaczen[i];
    }

    @Override
    public String toString() {
        return "Wierzchołek{" + "numer=" + numer + '}' + "Pierwszy sąsiad:" + numerySasiadow[0];
    }

    @Override
    public Object clone(){
        Wierzcholek w = null;
        try{
            w= (Wierzcholek) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return w;
    }

}
