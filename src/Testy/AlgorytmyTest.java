package Testy;

import GrafJimp.*;
import org.junit.jupiter.api.*;

import static org.testng.AssertJUnit.*;



class AlgorytmyTest {
    private Graf graf;
    Algorytmy algorytmy;

    @BeforeEach
    void setUp() {
        graf = new Graf();
        graf.setKolumny(3);
        graf.setWiersze(3);
        algorytmy = new Algorytmy();
        Analizator analizator = new Analizator();
        analizator.generuj(graf);
    }

    @AfterEach
    void tearDown() {
        graf=null;
        algorytmy=null;
    }

    @Test
    void wykonajAlgorytmBFS() {
        int[] kolor = new int [graf.getLiczbaWierzchołków()];
        Wierzcholek[] rodzic = new Wierzcholek [graf.getLiczbaWierzchołków()];
        int[] odległość = new int [graf.getLiczbaWierzchołków()];
        for(int i =0;i<graf.getLiczbaWierzchołków();i++) {
            algorytmy.wykonajAlgorytmBFS(graf, kolor, rodzic, odległość, i);
            for(int j=0;j<graf.getLiczbaWierzchołków();j++){
                if(j!=i){
                    assertNotSame(odległość[j],0);
                    assertNotSame(rodzic[j], null);
                    assertEquals(kolor[j], 2);
                    continue;
                }
                assertEquals(odległość[j], 0);
                assertNull(rodzic[j]);
                assertEquals(kolor[j], 2);
            }

        }
    }

    @Test
    void wykonajAlgorytmDijkstry() {
        for(Krawedz k: graf.getKrawedzi()){
            if (k==null){
                break;
            }
            k.setWaga(1.);
        }
        double [] odległości = new double[graf.getLiczbaWierzchołków()];
        for(int i = 0;i<graf.getLiczbaWierzchołków();i++) {
            algorytmy.wykonajAlgorytmDijkstry(graf, i, odległości, null);
            for(int j =0;j<graf.getLiczbaWierzchołków();j++){
                if(j!=i){
                    assertNotSame(odległości[j], 0.0);
                    continue;
                }
                assertEquals(odległości[j], 0.0);
            }
        }
        for(int i =0;i<graf.getLiczbaWierzchołków();i++){
            System.out.println(odległości[i]);
        }
    }
}