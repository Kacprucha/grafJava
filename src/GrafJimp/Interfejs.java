package GrafJimp;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Interfejs extends JFrame {

    private Graf graf;
    private Algorytmy algorytmy = new Algorytmy();
    private int początekBFS = 0;
    private int początekDijkstra = 0;
    private int koniecDjikstry;
    int wynikAnalizy;
    double[] odległości;

    JPanel poleNaPrzyciski = new JPanel();
    JPanel poleNaKomunikaty = new JPanel();
    PoleNaGraf poleNaGraf;
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

    JTextField poleNaKolumny;
    JTextField poleNaWiersze;
    JTextField poleNaPoczatek;
    JTextField poleNaKoniec;

    ButtonGroup przyciskiAlgorytmy;

    Interfejs(Graf g) {
        graf = g;

        przyciskBFS = new JRadioButton("BFS");
        przyciskBFS.setBackground(Color.LIGHT_GRAY);
        przyciskBFS.setFocusable(false);
        przyciskBFS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                komunikaty.setText("wciskam BFS");

                if (graf.getWierzcholki() != null && wynikAnalizy == 0) {
                    komunikaty.setText("wciskam BFS");
                    poleNaGraf.kliknientyBFS();
                    poleNaGraf.wyczyszczonyDji();

                    int[] kolor = new int[graf.getLiczbaWierzchołków()];
                    Wierzcholek[] rodzic = new Wierzcholek[graf.getLiczbaWierzchołków()];
                    int[] odległość = new int[graf.getLiczbaWierzchołków()];

                    algorytmy.wykonajAlgorytmBFS(graf, kolor, rodzic, odległość, początekBFS);

                    poleNaGraf.setTablicaBFS(odległość);

                    for (int i = 0; i < graf.getLiczbaWierzchołków(); i++) {
                        if (odległość[i] == -1 && i != początekBFS) {
                            break;
                        }
                        if (i == graf.getLiczbaWierzchołków() - 1) {
                            komunikaty.setText("Graf jest spójny");
                            graf.setSpojny(true);
                            repaint();
                        } else {
                            komunikaty.setText("Graf nie jest spójny");
                            graf.setSpojny(false);
                            if(graf.getLiczbaWierzchołków() < 100000) {
                                repaint();
                            }
                        }
                    }
                } else {
                    komunikaty.setText("Nie wygenerowano / wczytano żadnego grafu aby rozpocząć analizę algorytmem.");
                    przyciskiAlgorytmy.clearSelection();
                }
            }
        });

        przyciksDijkstra = new JRadioButton("Dijkstra");
        przyciksDijkstra.setBackground(Color.LIGHT_GRAY);
        przyciksDijkstra.setFocusable(false);
        przyciksDijkstra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (graf.getWierzcholki() != null && wynikAnalizy == 0) {
                    try {
                        początekDijkstra = Integer.parseInt(poleNaPoczatek.getText());
                    } catch (NumberFormatException r) {
                        if (!(Objects.equals(poleNaPoczatek.getText(), "początek Djikstry"))) {
                            JOptionPane.showMessageDialog(null, "Proszę podać liczby naturalną jako początek dla algorytmu Djikstra!\nProgram automatycznie przypisuje początek jako wierzchołek 0.", "Błąd", JOptionPane.ERROR_MESSAGE);
                            początekDijkstra = 0;
                        }
                        poleNaPoczatek.setText("0");
                        poleNaKoniec.setText(Integer.toString(poleNaGraf.getKoniecDji()));
                    }
                    try {
                        if (początekDijkstra >= 0 && początekDijkstra < graf.getLiczbaWierzchołków() && Integer.parseInt(poleNaKoniec.getText()) >= 0 && Integer.parseInt(poleNaKoniec.getText()) < graf.getLiczbaWierzchołków()) {
                            odległości = new double[graf.getLiczbaWierzchołków()];
                            algorytmy.wykonajAlgorytmDijkstry(graf, początekDijkstra, odległości, poleNaGraf);

                            poleNaGraf.kliknientyDji();
                            poleNaGraf.wyczyszczonyBFS();

                            poleNaGraf.setPoczatekDji(początekDijkstra);
                            poleNaGraf.setKoniecDji(Integer.parseInt(poleNaKoniec.getText()));
                            komunikaty.setText("Najkrótsza droga z wierzchołka " + początekDijkstra + " do wierzchołka " + poleNaGraf.getKoniecDji() + " wynosi " + odległości[poleNaGraf.getKoniecDji()]);
                            poleNaGraf.setOdleglosci(odległości);
                            if (graf.getLiczbaWierzchołków() < 100000) {
                                repaint();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Proszę podać liczby naturalną dodatnią jako początek lub koniec dla algorytmu Djikstra!\nKoniec dla algorytmu nie może też przekraczać ilości wierzchołków w grafie.", "Błąd", JOptionPane.ERROR_MESSAGE);
                            poleNaPoczatek.setText("0");
                            poleNaKoniec.setText(Integer.toString(graf.getLiczbaWierzchołków() - 1));
                        }
                    } catch (NumberFormatException ee) {
                        JOptionPane.showMessageDialog(null, "Proszę podać liczby naturalną dodatnią jako początek lub koniec dla algorytmu Djikstra!\nKoniec dla algorytmu nie może też przekraczać ilości wierzchołków w grafie.", "Błąd", JOptionPane.ERROR_MESSAGE);
                        poleNaPoczatek.setText("0");
                        poleNaKoniec.setText(Integer.toString(graf.getLiczbaWierzchołków() - 1));
                    }
                } else {
                    komunikaty.setText("Nie wygenerowano / wczytano żadnego grafu aby rozpocząć analizę algorytmem.");
                    przyciskiAlgorytmy.clearSelection();
                }
            }
        });

        przyciskiAlgorytmy = new ButtonGroup();
        przyciskiAlgorytmy.add(przyciskBFS);
        przyciskiAlgorytmy.add(przyciksDijkstra);

        przyciskGeneracja = new JButton();
        przyciskGeneracja.setText("Wygeneruj graf");
        przyciskGeneracja.setFocusable(false);
        przyciskGeneracja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean error = false;

                try {
                    graf.setKolumny(Integer.parseInt(poleNaKolumny.getText()));
                    graf.setWiersze(Integer.parseInt(poleNaWiersze.getText()));

                    if (graf.getWiersze() <= 0 || graf.getKolumny() <= 0) {
                        error = true;
                    }

                    if (error) {
                        komunikaty.setText("Błędnie podano wymiary grafu!");
                    } else {
                        komunikaty.setText("Generuje graf: " + graf.getKolumny() + "x" + graf.getWiersze());

                        przyciskiAlgorytmy.clearSelection();
                        poleNaGraf.wyczyszczonyBFS();
                        poleNaGraf.wyczyszczonyDji();

                        Analizator.generuj(graf);
                        wynikAnalizy = 0;
                        poleNaGraf.setKoniecDji(graf.getLiczbaWierzchołków() - 1);

                        poleNaPoczatek.setText("0");
                        poleNaKoniec.setText(Integer.toString(graf.getLiczbaWierzchołków() - 1));

                        if(graf.getLiczbaWierzchołków() < 100000) {
                            repaint();
                        } else {
                            komunikaty.setText("Graf został wygenerowany ale jest za duży, żeby go narysować");
                            poleNaGraf.removeAll();
                            repaint();
                        }
                    }
                } catch (NumberFormatException r) {
                    komunikaty.setText("Proszę wpisać w odpowiednie pola liczbę kolumn i wierszy! Muszą być to liczby naturalne > 0.");
                }
            }
        });

        przyciskWczytaj = new JButton();
        przyciskWczytaj.setText("Wczytaj graf");
        przyciskWczytaj.setFocusable(false);
        przyciskWczytaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser wybieracz = new JFileChooser();

                int odpowedz = wybieracz.showOpenDialog(null);

                if (odpowedz == JFileChooser.APPROVE_OPTION) {
                    File plik = new File(wybieracz.getSelectedFile().getAbsolutePath());
                    String nazwa = plik.getName();
                    komunikaty.setText("Wczytuje plik o nazwie " + nazwa);

                    wynikAnalizy = Analizator.czytaj(graf, plik);
                    if(wynikAnalizy != 0) {
                        komunikaty.setText("Błędny format pliku: " + nazwa);
                        przyciskiAlgorytmy.clearSelection();
                        poleNaGraf.wyczyszczonyBFS();
                        poleNaGraf.wyczyszczonyDji();
                        poleNaPoczatek.setText("początek Djikstry");
                        poleNaKoniec.setText("koniec Djikstry");

                        repaint();
                    } else {
                        komunikaty.setText("Wczytano plik o nazwie " + nazwa);

                        poleNaGraf.setKoniecDji(graf.getLiczbaWierzchołków() - 1);

                        poleNaPoczatek.setText("0");
                        poleNaKoniec.setText(Integer.toString(graf.getLiczbaWierzchołków() - 1));

                        przyciskiAlgorytmy.clearSelection();
                        poleNaGraf.wyczyszczonyBFS();
                        poleNaGraf.wyczyszczonyDji();

                        if(graf.getLiczbaWierzchołków() < 100000) {
                            repaint();
                        } else {
                            komunikaty.setText("Graf został wczytany ale jest za duży, żeby go narysować");
                            poleNaGraf.removeAll();
                        }
                    }
                }
            }
        });

        przyciskWagi = new JButton();
        przyciskWagi.setText("Ustawienie wag");
        przyciskWagi.setFocusable(false);
        przyciskWagi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean error = false;

                try {
                    String wagi = JOptionPane.showInputDialog("Proszę podać wagę maksymalną i minimalną wag połączeń między węzłami:");
                    wagi = wagi.replace('.', ',');
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

                    if (graf.getWagaMin() < 0 || graf.getWagaMax() < 0) {
                        error = true;
                        graf.setWagaMin(0);
                        graf.setWagaMax(10);
                        JOptionPane.showMessageDialog(null, "Wagi powinny być liczbami dodatnimi!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    }

                    skaner.close();

                    if (error) {
                        komunikaty.setText("Błędnie podano wagi połączeń między węzłami!");
                    } else {
                        if (graf.getWagaMin() > graf.getWagaMax()) {
                            double tempWaga = graf.getWagaMax();
                            graf.setWagaMax(graf.getWagaMin());
                            graf.setWagaMin(tempWaga);
                        }

                        komunikaty.setText("Generowany graf bedzie miał wagi połączeń: " + graf.getWagaMin() + "-" + graf.getWagaMax());
                    }
                } catch (NullPointerException r) {
                    komunikaty.setText("Wychodzę z ustawiania wag");
                }
            }
        });

        przyciskZapisz = new JButton();
        przyciskZapisz.setText("Zapisz");
        przyciskZapisz.setFocusable(false);
        przyciskZapisz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (graf.getWierzcholki() != null) {
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
        });

        przyciskPomoc = new JButton();
        przyciskPomoc.setText("Pomoc");
        przyciskPomoc.setFocusable(false);
        przyciskPomoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                komunikaty.setText("Wyświetlasz okno pomocy.");
                new EkranPomocy();
            }
        });

        przyciskWyczysc = new JButton();
        przyciskWyczysc.setText("Wyczyść");
        przyciskWyczysc.setFocusable(false);
        przyciskWyczysc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                przyciskiAlgorytmy.clearSelection();
                poleNaGraf.wyczyszczonyBFS();
                poleNaGraf.wyczyszczonyDji();
                repaint();
                komunikaty.setText("Powrót do początkowego wyglądy grafu.");
            }
        });

        poleNaKolumny = new JTextField();
        poleNaKolumny.setPreferredSize(new Dimension(100, 20));
        poleNaKolumny.setText("liczba kolumn");

        poleNaWiersze = new JTextField();
        poleNaWiersze.setPreferredSize(new Dimension(100, 20));
        poleNaWiersze.setText("liczba wierszy");

        poleNaPoczatek = new JTextField();
        poleNaPoczatek.setPreferredSize(new Dimension(100, 20));
        poleNaPoczatek.setText("początek Djikstry");

        poleNaKoniec = new JTextField("koniec Djikstry");
        poleNaKoniec.setPreferredSize(new Dimension(100, 20));

        poleNaPrzyciski.setPreferredSize(new Dimension(150, 100));
        poleNaPrzyciski.setLayout(new FlowLayout());
        poleNaPrzyciski.setBackground(Color.lightGray);
        poleNaPrzyciski.setBorder(ramkaBoczna);

        poleNaKomunikaty.setPreferredSize(new Dimension(100, 50));
        poleNaKomunikaty.setLayout(new BorderLayout());
        poleNaKomunikaty.setBorder(ramkaDolna);

        komunikaty.setHorizontalAlignment(JLabel.CENTER);
        komunikaty.setFont(new Font("Times New Roma", Font.PLAIN, 20));

        poleNaGraf = new PoleNaGraf(graf, komunikaty, this);

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
        poleNaPrzyciski.add(poleNaKolumny);
        poleNaPrzyciski.add(poleNaWiersze);
        poleNaPrzyciski.add(przyciskGeneracja);
        poleNaPrzyciski.add(przyciskWagi);
        poleNaPrzyciski.add(przyciskBFS);
        poleNaPrzyciski.add(przyciksDijkstra);
        poleNaPrzyciski.add(poleNaPoczatek);
        poleNaPrzyciski.add(poleNaKoniec);
        poleNaPrzyciski.add(przyciskZapisz);
        poleNaPrzyciski.add(przyciskWyczysc);
        poleNaPrzyciski.add(przyciskPomoc);
    }
}
