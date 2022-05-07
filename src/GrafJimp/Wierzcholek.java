package GrafJimp;

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

    public void setNumerySąsiadów(int[] numerySasiadow) {
        this.numerySasiadow = numerySasiadow;
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
    public double getKokretnaWagePolaczenia(int i) {
        return wagiPolaczen[i];
    }

    @Override
    public String toString() {
        return "Wierzchołek{" + "numer=" + numer + '}' + "Pierwszy sąsiad:" + numerySasiadow[0];
    }

}
