package GrafJimp;

public class Wierzcholek {
    private int numer;
    private int[] numerySasiadow = new int[4];

    public Wierzcholek(int numer) {
        this.numer = numer;
    }

    public void setNumerySąsiadów(int[] numerySasiadow) {
        this.numerySasiadow = numerySasiadow;
    }

    public void dodajSąsiada(int numerSasiada, int pozycja){
        numerySasiadow[pozycja]=numerSasiada;
    }

    @Override
    public String toString() {
        return "Wierzchołek{" + "numer=" + numer + '}' + "Pierwszy sąsiad:" + numerySasiadow[0];
    }

}
