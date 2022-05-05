package GrafJimp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EkranPomocy {

    JFrame ekran = new JFrame();

    JPanel poleNaPrzyciski = new JPanel();
    JPanel poleNaKomunikaty = new JPanel();
    Border ramkaBoczna = BorderFactory.createMatteBorder(150, 100, 0, 100, Color.LIGHT_GRAY);
    Border ramkaDolna = BorderFactory.createLineBorder(Color.BLACK, 3);
    JLabel komunikaty = new JLabel();

    JLabel infoOWczytywaniu = new JLabel("<- za pomocą tego przycisku możemy wczytywać graf zapisany w pliku");
    JLabel infoOGeneracji = new JLabel("<- za pomocą tego przycisku możemy generować grafy o podanych ilościach kolumn i wierszy");
    JLabel infoOWagach = new JLabel("<- za pomocą tego przycisku możemy wybrać jakie wagi będą mieć połączenia w generowanym grafie");
    JLabel infoOBFS = new JLabel("<- wciskając ten przycisk graf zostanie podany analizie algorytmem BFS");
    JLabel infoODjikstra = new JLabel("<- wciskając ten przycisk graf zostanie podany analizie algorytmem Djikstry");
    JLabel infoOZapisie = new JLabel("<- za pomocą tego przycisku możemy wybrać plik do kórego chcemy zapisać wygenerowany graf");
    JLabel infoOWyczysc = new JLabel("<- kliknięcie tego przycisku spowoduje narysowanie podstaowej formy grafu nie poddanej analizie algorytmami");
    JLabel infoOPomoc = new JLabel("<- kliknięcie tego przycisku wyświetla ekrna pomocy");

    JRadioButton przyciskBFS;
    JRadioButton przyciksDijkstra;
    JButton przyciskGeneracja;
    JButton przyciskWczytaj;
    JButton przyciskWagi;
    JButton przyciskZapisz;
    JButton przyciskPomoc;
    JButton przyciskWyczysc;

    ButtonGroup przyciskiAlgorytmy;

    EkranPomocy() {
        przyciskBFS = new JRadioButton("BFS");
        przyciskBFS.setBackground(Color.LIGHT_GRAY);
        przyciskBFS.setFocusable(false);

        przyciksDijkstra = new JRadioButton("Dijkstra");
        przyciksDijkstra.setBackground(Color.LIGHT_GRAY);
        przyciksDijkstra.setFocusable(false);

        przyciskiAlgorytmy = new ButtonGroup();
        przyciskiAlgorytmy.add(przyciskBFS);
        przyciskiAlgorytmy.add(przyciksDijkstra);

        przyciskGeneracja = new JButton();
        przyciskGeneracja.setText("Wygeneruj graf");
        przyciskGeneracja.setFocusable(false);

        przyciskWczytaj = new JButton();
        przyciskWczytaj.setText("Wczytaj graf");
        przyciskWczytaj.setFocusable(false);

        przyciskWagi = new JButton();
        przyciskWagi.setText("Ustawienie wag");
        przyciskWagi.setFocusable(false);

        przyciskZapisz = new JButton();
        przyciskZapisz.setText("Zapisz");
        przyciskZapisz.setFocusable(false);

        przyciskPomoc = new JButton();
        przyciskPomoc.setText("Pomoc");
        przyciskPomoc.setFocusable(false);

        przyciskWyczysc = new JButton();
        przyciskWyczysc.setText("Wyczyść");
        przyciskWyczysc.setFocusable(false);

        poleNaPrzyciski.setBounds(0, 0 ,150,510);
        poleNaPrzyciski.setLayout(new FlowLayout());
        poleNaPrzyciski.setBorder(ramkaBoczna);

        poleNaKomunikaty.setBounds(0, 510, 900, 62);
        poleNaKomunikaty.setLayout(new BorderLayout());
        poleNaKomunikaty.setBorder(ramkaDolna);

        komunikaty.setHorizontalAlignment(JLabel.CENTER);
        komunikaty.setFont(new Font("Times New Roma", Font.PLAIN, 20));

        infoOWczytywaniu.setBounds(155, 140, 900, 50);
        infoOGeneracji.setBounds(155, 173, 900, 50);
        infoOWagach.setBounds(155, 206, 900, 50);
        infoOBFS.setBounds(155, 239, 900, 50);
        infoODjikstra.setBounds(155, 268, 900, 50);
        infoOZapisie.setBounds(155, 293, 900, 50);
        infoOWyczysc.setBounds(155, 326, 900, 50);
        infoOPomoc.setBounds(155, 359, 900, 50);

        ekran.setTitle("Ekran Pomocy");
        ekran.setSize(900, 600);
        ekran.setLayout(null);
        ekran.add(poleNaPrzyciski);
        ekran.add(poleNaKomunikaty);

        ekran.add(infoOWczytywaniu);
        ekran.add(infoOGeneracji);
        ekran.add(infoOWagach);
        ekran.add(infoOBFS);
        ekran.add(infoODjikstra);
        ekran.add(infoOZapisie);
        ekran.add(infoOWyczysc);
        ekran.add(infoOPomoc);

        ekran.setResizable(false);

        poleNaKomunikaty.add(komunikaty);

        poleNaPrzyciski.add(przyciskWczytaj);
        poleNaPrzyciski.add(przyciskGeneracja);
        poleNaPrzyciski.add(przyciskWagi);
        poleNaPrzyciski.add(przyciskBFS);
        poleNaPrzyciski.add(przyciksDijkstra);
        poleNaPrzyciski.add(przyciskZapisz);
        poleNaPrzyciski.add(przyciskWyczysc);
        poleNaPrzyciski.add(przyciskPomoc);

        komunikaty.setText("Tu wyświetlają się istotne komunikaty.");

        ekran.setVisible(true);
        }
}
