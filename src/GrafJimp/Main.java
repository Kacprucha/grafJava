package GrafJimp;

public class Main {
    public static void main (String[] args) {

        Graf graf = new Graf();

        Interfejs i = new Interfejs(graf);
        i.setVisible(true);
    }
}
