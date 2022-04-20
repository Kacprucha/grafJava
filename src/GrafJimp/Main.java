package GrafJimp;

public class Main {
    public static void main (String[] args) {

        Graf graf = new Graf();

        graf.setWagaMax(10);
        graf.setWagaMin(0);

        new Interfejs(graf);
    }
}
