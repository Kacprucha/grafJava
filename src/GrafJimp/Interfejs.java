package GrafJimp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Interfejs extends JFrame implements ActionListener {

    private Graf graf;
    private Algorytmy algorytmy = new Algorytmy();
    private int początekBFS = 0;
    private int początekDijkstra = 0;

    JPanel poleNaPrzyciski = new JPanel();
    JPanel poleNaKomunikaty = new JPanel();
    Rysowanie poleNaGraf;
    Border ramkaBoczna = BorderFactory.createMatteBorder(150, 100, 0, 100, Color.LIGHT_GRAY);
    Border ramkaDolna = BorderFactory.createLineBorder(Color.BLACK, 3);
    JLabel komunikaty = new JLabel();
    JRadioButton przyciskBFS;
    JRadioButton przyciksDijkstra;
    JButton przyciskGeneracja;
    JButton przyciskWczytaj;
    JButton przyciskWagi;
    JButton przyciskZapisz;
    JButton przyciskPomoc;
    JButton przyciskWyczysc;

    ButtonGroup przyciskiAlgorytmy;

    Interfejs(Graf g) {
        graf = g;

        przyciskBFS = new JRadioButton("BFS");
        przyciskBFS.setBackground(Color.LIGHT_GRAY);
        //przyciskBFS.setBounds(50, 32, 70, 35);
        przyciskBFS.addActionListener(this);
        //przyciskBFS.setText("BFS");
        przyciskBFS.setFocusable(false);

        przyciksDijkstra = new JRadioButton("Dijkstra");
        przyciksDijkstra.setBackground(Color.LIGHT_GRAY);
        //przyciksDijkstra.setBounds(140, 32, 100, 35);
        przyciksDijkstra.addActionListener(this);
        //przyciksDijkstra.setText("Dijkstra");
        przyciksDijkstra.setFocusable(false);

        przyciskiAlgorytmy = new ButtonGroup();
        przyciskiAlgorytmy.add(przyciskBFS);
        przyciskiAlgorytmy.add(przyciksDijkstra);

        przyciskGeneracja = new JButton();
        //przyciskGeneracja.setBounds(260, 32, 200, 35);
        przyciskGeneracja.addActionListener(this);
        przyciskGeneracja.setText("Wygeneruj graf");
        przyciskGeneracja.setFocusable(false);

        przyciskWczytaj = new JButton();
        przyciskWczytaj.addActionListener(this);
        przyciskWczytaj.setText("Wczytaj graf");
        przyciskWczytaj.setFocusable(false);

        przyciskWagi = new JButton();
        przyciskWagi.addActionListener(this);
        przyciskWagi.setText("Ustawienie wag");
        przyciskWagi.setFocusable(false);

        przyciskZapisz = new JButton();
        przyciskZapisz.addActionListener(this);
        przyciskZapisz.setText("Zapisz");
        przyciskZapisz.setFocusable(false);

        przyciskPomoc = new JButton();
        przyciskPomoc.addActionListener(this);
        przyciskPomoc.setText("Pomoc");
        przyciskPomoc.setFocusable(false);

        przyciskWyczysc = new JButton();
        przyciskWyczysc.addActionListener(this);
        przyciskWyczysc.setText("Wyczyść");
        przyciskWyczysc.setFocusable(false);

        //poleNaPrzyciski.setBackground(Color.BLACK);
        //poleNaPrzyciski.setOpaque(true);
        poleNaPrzyciski.setPreferredSize(new Dimension(150, 100));
        poleNaPrzyciski.setLayout(new FlowLayout());
        poleNaPrzyciski.setBorder(ramkaBoczna);

        //poleNaKomunikaty.setBackground(Color.BLUE);
        //poleNaKomunikaty.setOpaque(true);
        poleNaKomunikaty.setPreferredSize(new Dimension(100, 50));
        poleNaKomunikaty.setLayout(new BorderLayout());
        poleNaKomunikaty.setBorder(ramkaDolna);

        komunikaty.setHorizontalAlignment(JLabel.CENTER);
        komunikaty.setFont(new Font("Times New Roma", Font.PLAIN, 20));

        poleNaGraf = new Rysowanie(graf, komunikaty);

        this.setTitle("Analizator grafów");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setLayout(new BorderLayout());
        this.add(poleNaPrzyciski, BorderLayout.WEST);
        this.add(poleNaKomunikaty, BorderLayout.SOUTH);
        this.add(poleNaGraf, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);

        poleNaKomunikaty.add(komunikaty);

        poleNaPrzyciski.add(przyciskWczytaj);
        poleNaPrzyciski.add(przyciskGeneracja);
        poleNaPrzyciski.add(przyciskWagi);
        poleNaPrzyciski.add(przyciskBFS);
        poleNaPrzyciski.add(przyciksDijkstra);
        poleNaPrzyciski.add(przyciskZapisz);
        poleNaPrzyciski.add(przyciskWyczysc);
        poleNaPrzyciski.add(przyciskPomoc);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == przyciskBFS) {
            komunikaty.setText("wciskam BFS");
            int[] kolor = new int [graf.getLiczbaWierzchołków()];
            Wierzcholek[] rodzic = new Wierzcholek [graf.getLiczbaWierzchołków()];
            int[] odległość = new int [graf.getLiczbaWierzchołków()];
            algorytmy.wykonajAlgorytmBFS(graf, kolor, rodzic, odległość, początekBFS);
            for(int i=0;i<graf.getLiczbaWierzchołków();i++){
                if(odległość[i]==-1 && i!=początekBFS) {
                    break;
                }
                if(i==graf.getLiczbaWierzchołków()-1){
                    komunikaty.setText("Graf jest spójny");
                }else{
                    komunikaty.setText("Graf nie jest spójny");
                }
            }
        }

        if(e.getSource() == przyciksDijkstra) {
            komunikaty.setText("wciskam Dijkste");
            double [] odległości = new double[graf.getLiczbaWierzchołków()];
            algorytmy.wykonajAlgorytmDijkstry(graf, początekDijkstra, odległości);
            for(int i =0;i<graf.getLiczbaWierzchołków();i++){
                System.out.println(odległości[i]);
            }
        }

        if(e.getSource() == przyciskGeneracja) {
            boolean error = false;

            try {
                String kolumnyIwiersze = JOptionPane.showInputDialog("Proszę podać liczbę kolumn i wierszy generowanego grafu:");
                Scanner skaner = new Scanner(kolumnyIwiersze);
                if (skaner.hasNextInt()) {
                    graf.setKolumny(skaner.nextInt());
                } else {
                    JOptionPane.showMessageDialog(null, "Proszę podać liczby całkowite!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }

                if (!error) {
                    if (skaner.hasNextInt()) {
                        graf.setWiersze(skaner.nextInt());
                    } else {
                        JOptionPane.showMessageDialog(null, "Proszę podać dwie liczby całkowite!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                }

                if(graf.getWiersze() <= 0 || graf.getKolumny() <= 0) {
                    error = true;
                }

                skaner.close();

                if (error) {
                    komunikaty.setText("Błędnie podano wymiary grafu!");
                } else {
                    komunikaty.setText("generuje graf: " + graf.getKolumny() + "x" + graf.getWiersze());
                    Analizator.generuj(graf);
                    repaint();
                }
            } catch (NullPointerException r) {
                komunikaty.setText("wychodzę z generacji");
            }
        }

        if(e.getSource() == przyciskWczytaj) {
            JFileChooser wybieracz = new JFileChooser();

            int odpowedz = wybieracz.showOpenDialog(null);

            if(odpowedz == JFileChooser.APPROVE_OPTION) {
                File plik = new File(wybieracz.getSelectedFile().getAbsolutePath());
                String nazwa = plik.getName();
                komunikaty.setText("Wczytuje plik o nazwie " + nazwa);

                Analizator.czytaj(graf, plik);
                repaint();
            }
        }

        if(e.getSource() == przyciskWagi) {
            boolean error = false;

            try {
                String wagi = JOptionPane.showInputDialog("Proszę podać wagę maksymalną i minimalną wag połączeń między węzłami:");
                Scanner skaner = new Scanner(wagi);
                if (skaner.hasNextDouble()) {
                    graf.setWagaMax(skaner.nextDouble());
                } else {
                    JOptionPane.showMessageDialog(null, "Proszę podać liczbę!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }

                if (!error) {
                    if (skaner.hasNextDouble()) {
                        graf.setWagaMin(skaner.nextDouble());
                    } else {
                        JOptionPane.showMessageDialog(null, "Proszę podać dwie liczby!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        error = true;
                    }
                }

                skaner.close();

                if (error) {
                    komunikaty.setText("Błędnie podano wagi połączeń między węzłami!");
                } else {
                    if(graf.getWagaMin() > graf.getWagaMax()) {
                        double tempWaga = graf.getWagaMax();
                        graf.setWagaMax(graf.getWagaMin());
                        graf.setWagaMin(tempWaga);
                    }

                    komunikaty.setText("generowany graf bedzie miał wagi połączeń: " + graf.getWagaMin() + "-" + graf.getWagaMax());
                }
            } catch (NullPointerException r) {
                komunikaty.setText("wychodzę z wag");
            }
        }

        if(e.getSource() == przyciskZapisz) {
            if(graf.getWierzcholki() != null) {
                JFileChooser wybieracz = new JFileChooser();

                int odpowedz = wybieracz.showSaveDialog(null);

                if (odpowedz == JFileChooser.APPROVE_OPTION) {
                    graf.setPlikZapisu(new File(wybieracz.getSelectedFile().getAbsolutePath()));
                    komunikaty.setText("Zapisuje plik o nazwie " + graf.getNazwaPlikZapisu());
                    graf.zapiszaGrafDoPliku(komunikaty);
                }
            } else {
                komunikaty.setText("Nie wygenerowano jeszcze grafu, który mozna było by zapisać!");
            }
        }

        if(e.getSource() == przyciskWyczysc) {
            przyciskiAlgorytmy.clearSelection();
            komunikaty.setText("Powrót do początkowego wyglądy grafu.");
        }

        if(e.getSource() == przyciskPomoc) {
            komunikaty.setText("Wyświetlasz okno pomocy.");
            new EkranPomocy();
        }
    }
}
