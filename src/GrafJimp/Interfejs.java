package GrafJimp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Interfejs extends JFrame implements ActionListener {

    private Graf graf;

    JPanel poleNaPrzyciski = new JPanel();
    JPanel poleNaKomunikaty = new JPanel();
    Border ramka = BorderFactory.createLineBorder(Color.BLACK, 3);
    JLabel komunikaty = new JLabel();
    JButton przyciskBFS;
    JButton przyciksDijkstra;
    JButton przyciskGeneracja;
    JButton przyciskWczytaj;
    JButton przyciskWagi;
    JButton przyciskZapisz;

    Interfejs(Graf g) {
        graf = g;

        przyciskBFS = new JButton();
        //przyciskBFS.setBounds(50, 32, 70, 35);
        przyciskBFS.addActionListener(this);
        przyciskBFS.setText("BFS");
        przyciskBFS.setFocusable(false);

        przyciksDijkstra = new JButton();
        //przyciksDijkstra.setBounds(140, 32, 100, 35);
        przyciksDijkstra.addActionListener(this);
        przyciksDijkstra.setText("Dijkstra");
        przyciksDijkstra.setFocusable(false);

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

        //poleNaPrzyciski.setBackground(Color.BLACK);
        //poleNaPrzyciski.setOpaque(true);
        poleNaPrzyciski.setPreferredSize(new Dimension(100, 100));
        poleNaPrzyciski.setLayout(new FlowLayout());
        poleNaPrzyciski.setBorder(ramka);

        //poleNaKomunikaty.setBackground(Color.BLUE);
        //poleNaKomunikaty.setOpaque(true);
        poleNaKomunikaty.setPreferredSize(new Dimension(100, 100));
        poleNaKomunikaty.setLayout(new BorderLayout());
        poleNaKomunikaty.setBorder(ramka);

        komunikaty.setHorizontalAlignment(JLabel.CENTER);
        komunikaty.setFont(new Font("Times New Roma", Font.PLAIN, 20));

        this.setTitle("Analizator grafów");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 600);
        this.setLayout(new BorderLayout());
        this.add(poleNaPrzyciski, BorderLayout.NORTH);
        this.add(poleNaKomunikaty, BorderLayout.SOUTH);

        poleNaKomunikaty.add(komunikaty);

        poleNaPrzyciski.add(przyciskWczytaj);
        poleNaPrzyciski.add(przyciskGeneracja);
        poleNaPrzyciski.add(przyciskWagi);
        poleNaPrzyciski.add(przyciskBFS);
        poleNaPrzyciski.add(przyciksDijkstra);
        poleNaPrzyciski.add(przyciskZapisz);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == przyciskBFS) {
            komunikaty.setText("wciskam BFS");
        }

        if(e.getSource() == przyciksDijkstra) {
            komunikaty.setText("wciskam Dijkste");
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

                skaner.close();

                if (error) {
                    komunikaty.setText("Błędnie podano wymiary grafu!");
                } else {
                    komunikaty.setText("generuje graf: " + graf.getKolumny() + "x" + graf.getWiersze());
                }
            } catch (NullPointerException r) {
                komunikaty.setText("wychodzę z generacji");
            }
        }

        if(e.getSource() == przyciskWczytaj) {
            try {
                String plik = JOptionPane.showInputDialog("Proszę podać nazwę pliku zawierającego graf:");
                Scanner skaner = new Scanner(plik);

                if(skaner.hasNext()) {
                    Czytanie.setNazwaPliku(skaner.nextLine());
                    komunikaty.setText("wczytuje plik: " + Czytanie.getNazwaPliku());
                } else {
                    komunikaty.setText("nie podano nazwy pliku zawierającego graf, proszę spróbować jeszcze raz");
                }
                skaner.close();

            } catch (NullPointerException r) {
                komunikaty.setText("wychodzę z wczytywania");
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

                if(graf.getWagaMax() < graf.getWagaMin()) {
                    JOptionPane.showMessageDialog(null, "Pierwsza podana liczba powinna być większa od drugiej!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    error = true;
                    graf.setWagaMax(10);
                    graf.setWagaMin(0);
                }

                skaner.close();

                if (error) {
                    komunikaty.setText("Błędnie podano wagi połączeń między węzłami!");
                } else {
                    komunikaty.setText("generowany graf bedzie miał wagi połączeń: " + graf.getWagaMin() + "-" + graf.getWagaMax());
                }
            } catch (NullPointerException r) {
                komunikaty.setText("wychodzę z wag");
            }
        }

        if(e.getSource() == przyciskZapisz) {
            try {
                String plik = JOptionPane.showInputDialog("Proszę podać nazwę pliku do, którego chcesz zapisać graf:");
                Scanner skaner = new Scanner(plik);

                if(skaner.hasNext()) {
                    String plikDoZapisu = skaner.nextLine();
                    komunikaty.setText("podano plik do zapisu: " + plikDoZapisu);
                } else {
                    komunikaty.setText("nie podano nazwy pliku do zapisu");
                }
                skaner.close();

            } catch (NullPointerException r) {
                komunikaty.setText("wychodzę z zapisu");
            }
        }
    }
}
