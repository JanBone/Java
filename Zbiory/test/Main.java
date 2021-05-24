package test;

import struktury.Para;
import struktury.ZbiorNaTablicy;
import struktury.ZbiorNaTablicyDynamicznej;

public class Main {

    public static void main(String[] args) {
        Para p = new Para("kot",1);
        Para p2 = new Para("pies",2);
        Para p3 = new Para("ryba",3);
        Para p4 = new Para("ptak",4);
        ZbiorNaTablicy zbior_tab = new ZbiorNaTablicy(4);
        ZbiorNaTablicyDynamicznej zbior_dyn = new ZbiorNaTablicyDynamicznej();
        System.out.print("Ile elementow zawiera zbior przed dodaniem nowego: " + zbior_tab.ile() +"\n");
        System.out.print("Jakie elementy zawiera zbior po dodaniu nowej pary ");
        zbior_tab.wstaw(p);
        zbior_tab.wypisz();
        System.out.print("\n");
        zbior_tab.wstaw(p2);
        zbior_tab.wstaw(p3);
        zbior_tab.wstaw(p4);
        System.out.print("Jeszcze wiecej elementow: ");
        zbior_tab.wypisz();
        zbior_tab.usun("kot");
        System.out.print("\n");
        System.out.print("Liczba elemenetow po usunięciu kota: " + zbior_tab.ile() + "\n" + "Tablica: ");
        zbior_tab.wypisz();
        System.out.print("\n");
        zbior_tab.ustaw(new Para("pies",99));
        System.out.print("Ustawienie psa na 99: ");
        zbior_tab.wypisz();
        System.out.print("\n");
        System.out.print("Czaytaj ryba: " + zbior_tab.czytaj("ryba"));
        System.out.print("\n");
        System.out.print("------------------------------------------------");
        System.out.print("\n");
        zbior_dyn.wstaw(p);
        zbior_dyn.wstaw(p2);
        zbior_dyn.wstaw(p3);
        zbior_dyn.wstaw(p4);
        System.out.print("Rozmiar zbioru po dodaniu 4 elementow: "+ zbior_dyn.ile() + "\n" + "Zbior po dodaniu 4 elementów: ");
        zbior_dyn.wypisz();
        System.out.print("\n");
        //System.out.print(zbior_dyn.liczba_par + "liczbaaaa" + "\n");
        zbior_dyn.ustaw(new Para("pies", 77));
        zbior_dyn.ustaw(new Para("szczur", 88));
        System.out.print("Rozmiar zbioru zmianie wartosci psa i dodaniu szczura: "+ zbior_dyn.ile() + "\n");
        zbior_dyn.wypisz();
        System.out.print("\n");
        zbior_dyn.wstaw(new Para("jelen", 18));
        zbior_dyn.wstaw(new Para("jez", 33));
        zbior_dyn.wstaw(new Para("mysz", 22));
        zbior_dyn.wstaw(new Para("zuk", 19));
        System.out.print("Teraz w zbiorze mamy: " + zbior_dyn.ile() + "obiektów");
        zbior_dyn.wypisz();
        System.out.print("\n");
        zbior_dyn.usun("jelen");
        zbior_dyn.usun("jez");
        zbior_dyn.usun("zuk");
        zbior_dyn.usun("mysz");
        zbior_dyn.usun("szczur");
        zbior_dyn.usun("pies");
        zbior_dyn.usun("ryba");

        System.out.print("Zbiór po usuniciu więcej niz 3/4 obiektów zawiera " +zbior_dyn.ile() + " obiektów, a jego rozmiar jest równy " + zbior_dyn.rozmiar );

    }
}
