package GrafJimp;

public class Algorytmy {


    public void wykonajAlgorytmBFS(Graf graf, int [] kolor, Wierzcholek [] rodzic, int [] odleglosc, int poczatekBFS) {

        for(Wierzcholek w : graf.getWierzcholki()){
            kolor[w.getNumer()] = 0;
            rodzic[w.getNumer()] = null;
            odleglosc[w.getNumer()] = -1;
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
}
