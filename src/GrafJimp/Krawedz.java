package GrafJimp;

public class Krawedz {
    private double waga;
    private int [] polaczenie = new int[2];

    public Krawedz(double waga) {
        this.waga = waga;
    }

    public int[] getPolaczenie() {
        return polaczenie;
    }

    public double getWaga() {
        return waga;
    }

    public void dodajPolaczenie(int poczatek, int koniec){
        polaczenie[0]=poczatek;
        polaczenie[1]=koniec;
    }

    @Override
    public String toString() {
        return "Krawędź{" + "waga=" + waga + '}';
        //return "Krawędź{" + "WP =" + polaczenie[0] + " WK = " + polaczenie[1] + '}';
    }
}
