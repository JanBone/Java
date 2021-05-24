package struktury;

public  class ZbiorNaTablicy extends Zbior{
    final int rozmiar;
    Para[] tablica;
    int liczba_par = 0;
    public ZbiorNaTablicy(int rozmiar) throws  IllegalArgumentException{
        if (rozmiar < 2){
            throw new IllegalArgumentException("Rozmiar tablicy nie może byc mniejszy niz 2");
        }
        else{
            this.rozmiar = rozmiar;
            this.tablica = new Para[rozmiar];
        }
    }

    public Para szukaj(String klucz) throws IllegalArgumentException{
        for (int k=0; k<this.liczba_par;k+=1){
            Para p = tablica[k];
            if (p.get_klucz() == klucz){
                return p;
            }
        }

        throw new IllegalArgumentException("Zbior nie zawiera pary  o podanym kluczu");

    }
    public boolean czy_istnieje(String klucz){
        for (int i=0; i<this.liczba_par;i+=1){
            Para p = tablica[i];
            if (p.get_klucz() == klucz){
                return true;
            }
        }
        return false;
    }
    public void wstaw(Para p) throws IllegalArgumentException{
        if (this.liczba_par == rozmiar){
            throw new IllegalArgumentException("Nie ma miejsca dla wstawienia nowej pary");

        }
        else{
            if(!czy_istnieje(p.get_klucz())){
                tablica[liczba_par] = p;
                this.liczba_par += 1;
            }
            else{
                throw new IllegalArgumentException("Taki klucz juz istnieje");
            }
        }
    }

    public void usun(String k) throws IllegalArgumentException{
        if(czy_istnieje(k)){
            for (int i=0; i<liczba_par;i+=1){
                Para pr = tablica[i];
                if (pr.get_klucz() == k){
                    for (int j=i; j<this.liczba_par-1;j+=1) {
                        tablica[j] = tablica[j+1];
                    }
                    this.liczba_par -=1;
                }
                break;
            }

        }
        else{
            throw new IllegalArgumentException("Para o podanym kluczu nie istnieje");
        }
    }

    public double czytaj(String k) throws IllegalArgumentException{
        for (int i=0; i<this.liczba_par;i+=1){
            Para p = tablica[i];
            if (p.get_klucz() == k){
                return p.getWartosc();
            }
        }
        throw new IllegalArgumentException("Para o podanym kluczu nie istnieje");
    }

    public  void ustaw(Para pr) throws IllegalArgumentException{
        int z = 0;
        for (int i=0; i<this.liczba_par;i+=1){
            Para p = this.tablica[i];
            if (p.get_klucz() == pr.get_klucz()){
                p.setWartosc(pr.getWartosc());
                z = 1;
            }
            break;
        }
        if (z != 1){
            wstaw(pr);

        }

    }
    public void czysc(){
        this.tablica = null;
        this.liczba_par = 0;
    }

    public int ile(){
        return this.liczba_par;
    }

    public void wypisz(){
        if (liczba_par == 0){
            System.out.print("Tablica jest pusta");
        }
        else{
            for (int i=0; i<this.liczba_par;i+=1){
                Para p = tablica[i];
                System.out.print(p.toString() + ",   ");
            }
        }

    }



}
