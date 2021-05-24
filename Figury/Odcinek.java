

public class Odcinek extends Punkt {
    public Punkt p1, p2;
    public Odcinek(Punkt a, Punkt b) throws IllegalArgumentException{
        if (a.x() == b.x() && a.y()==b.y()){
            throw new IllegalArgumentException("Podane punkty nie tworzą odcinka");

        }
        p1 = a;
        p2 = b;

    }
    public void przesun(Wektor v){
        p1.przesun(v);
        p2.przesun(v);
    }
    public void obroc(Punkt p, double kat){
        p1.obroc(p,kat);
        p2.obroc(p,kat);
    }

    public void odbij(Prosta p ){
        this.p1.odbij(p);
        this.p2.odbij(p);
    }

}
