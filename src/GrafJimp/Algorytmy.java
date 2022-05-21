package GrafJimp;

import java.util.ArrayList;
import java.util.Arrays;

public class Algorytmy {


    public void wykonajAlgorytmBFS(Graf graf, int [] kolor, Wierzcholek [] rodzic, int [] odleglosc, int poczatekBFS) {

        for(Wierzcholek w : graf.getWierzcholki()){
            kolor[w.getNumer()] = 0;
            rodzic[w.getNumer()] = null;
            odleglosc[w.getNumer()] = -1;
            //System.out.println(Arrays.toString(w.getNumerySąsiadów()));
        }

        kolor[poczatekBFS] = 1;
        odleglosc[poczatekBFS] = 0;
        Wierzcholek[] kolejka = new Wierzcholek [graf.getLiczbaWierzchołków()];
        kolejka[0]= graf.getWierzcholki()[poczatekBFS];

        int poczatek = 0;
        int koniec = 0;

        koniec++;
        Wierzcholek u;

        while(poczatek != koniec){
            u = kolejka[poczatek++];

            for(int numerWierzcholka : u.getNumerySąsiadów()){
                if(numerWierzcholka != -1){
                    if(kolor[numerWierzcholka] == 0){
                        kolor[numerWierzcholka] = 1;
                        odleglosc[numerWierzcholka] = odleglosc[u.getNumer()]+1;
                        rodzic[numerWierzcholka] = u;
                        kolejka[koniec++] = graf.getWierzcholki()[numerWierzcholka];
                    }
                }
            }

            kolor[u.getNumer()] = 2;
        }
    }

    public void wykonajAlgorytmDijkstry(Graf graf, int poczatekDijkstra, double [] odleglosci, PoleNaGraf r){
        int[] poprzednik = new int[graf.getLiczbaWierzchołków()];
        int[] kolejka = new int [4*graf.getLiczbaWierzchołków()];

        ArrayList<Krawedz> listaKrawedzi;
        ArrayList<ArrayList<Krawedz>> lista = new ArrayList<>();

        for(int i = 0; i < graf.getLiczbaWierzchołków(); i++) {
            listaKrawedzi = new ArrayList<>();
            lista.add(listaKrawedzi);
        }

        int u;

        int poczatek = 0;
        int koniec = 0;

        for(Wierzcholek w : graf.getWierzcholki()){
            odleglosci[w.getNumer()]=Double.MAX_VALUE;
            poprzednik[w.getNumer()]=-1;
        }

        odleglosci[poczatekDijkstra] = 0;

        kolejka[koniec++] = poczatekDijkstra;
        odleglosci[poczatekDijkstra] = 0.0;

        double min = odleglosci[poczatekDijkstra];
        int min_i = poczatekDijkstra;
        int tmp;
        int nrSasiada;

        Wierzcholek [] tmpWierzcholki = new Wierzcholek[graf.getLiczbaWierzchołków()];
        for(int i =0; i<graf.getLiczbaWierzchołków();i++) {
            tmpWierzcholki[i] = new Wierzcholek(graf.getWierzcholki()[i]);
        }

        while(poczatek != koniec){

            for(int i = poczatek; i < koniec; i++){
                if(odleglosci[kolejka[i]] <= min){
                    min = odleglosci[kolejka[i]];
                    min_i = i;
                }
            }
            tmp = kolejka[poczatek];
            kolejka[poczatek] = kolejka[min_i];
            kolejka[min_i] = tmp;

            u = kolejka[poczatek];
            nrSasiada = 0;
            for(int i : tmpWierzcholki[u].getNumerySąsiadów()){
                if(i != -1){
                    for(int j = 0; j < graf.getLiczbaKrawedzi(); j++){
                        Krawedz k = graf.getKrawedzi()[j];
                        if(k.getPolaczenie()[0] == u && k.getPolaczenie()[1] == i && odleglosci[i] > odleglosci[u] + k.getWaga()){
                            odleglosci[i] = odleglosci[u] + k.getWaga();

                            lista.get(i).clear();
                            for(int l = 0; l < lista.get(u).size(); l++) {
                                if(!lista.get(i).contains(lista.get(u).get(l))) {
                                    lista.get(i).add(lista.get(u).get(l));
                                }
                            }
                            if(!lista.get(i).contains(k)) {
                                lista.get(i).add(k);
                            }

                            kolejka[koniec++] = i;
                            poprzednik[i] = u;
                            //System.out.println(tmpWierzcholki[u].getNumerySąsiadów()[nrSasiada]);
                            //System.out.println(graf.getWierzcholki()[u].getNumerySąsiadów()[nrSasiada]);
                            tmpWierzcholki[u].setNumerSasiada(nrSasiada, -1);
                            //.out.println(tmpWierzcholki[u].getNumerySąsiadów()[nrSasiada]);
                            //.out.println(graf.getWierzcholki()[u].getNumerySąsiadów()[nrSasiada]);
                            nrSasiada++;
                            break;
                        }
                    }
                }
            }
            poczatek++;;
        }
        if(r!=null)
            r.setListaDji(lista);
    }
}
