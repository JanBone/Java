
public class Main {

    public static void main(String[] args) {
	   Punkt p1 = new Punkt(1,1);
	   Punkt p2 = new Punkt(0,0);
	   Prosta y = new Prosta(1,0,0);
	   System.out.println(p1.toString());
	   p1.odbij(y);
	   System.out.println(p1.toString() + " punkt (1,1) po odbiciu względem osi y");
	   p1.obroc(p2,180);
	   System.out.println(p1.toString() + " punkt (-1,1) po obrocie o 90 wokól punkta (0,0)");
	   Wektor v = new Wektor(2,3);
	   p2.przesun(v);
	   System.out.println(p2.toString() + " punkt (0,0) po przesunięciu o [2,3]");
	   Prosta pr = new  Prosta(1,1,0);
	   Prosta pr2 = Prosta.przesun(pr, v);
	   System.out.println(pr2.toString() + " prosta (1,1,0) po przesunięciu o [2,3]");
	   Prosta x_o = new  Prosta(0,1,0);
	   Prosta y_o = new Prosta(1,0,0);
	   Punkt przeciecie = Prosta.punkt_p(x_o,y_o);
	   System.out.println(przeciecie.toString() + " prostej (0,1,0) i prostej (1,0,0) ");
	   System.out.println(Prosta.prostopadle(y_o,x_o) + " Sprawdzamy czy ox i oy sa prostopadle");
	   System.out.println(Prosta.rownolegle(y_o,x_o) + " Sprawdzamy czy ox i oy sa rownolegle");
	   // Trojkat tr = new Trojkat(new Punkt(1,0),new Punkt(2,0), new Punkt(3,0)); czy mozemy utworzyc nieistniejacy trojkat
        Trojkat tr = new Trojkat(new Punkt(1,0),new Punkt(4,0), new Punkt(3,2));
        tr.przesun(v);
        System.out.println(tr.toString() + "Trójkąt ((1,0), (4,0), (3,2)) po przesunięciu o wektor [2,3]" );




    }
}
