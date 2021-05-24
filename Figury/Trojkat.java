

public class Trojkat extends Punkt{
    public Punkt p1, p2, p3;
    public Trojkat(Punkt a, Punkt b, Punkt c) throws IllegalArgumentException{
        double pole = ((a.x()-c.x())*(b.y()-c.y()) -(b.x()-c.x())*(a.y()-c.y()))/2;
        System.out.println(pole);
        if (pole == 0){
            throw new IllegalArgumentException("Punkty nie tworzą trójkątu");
        }
        this.p1 = a;
        this.p2 = b;
        this.p3 = c;
    }

    public void przesun(Wektor v){
        p1.przesun(v);
        p2.przesun(v);
        p3.przesun(v);

    }

    public void obroc(Punkt p, double kat){
        p1.obroc(p,kat);
        p2.obroc(p,kat);
        p3.obroc(p,kat);
    }
    public void odbij(Prosta p){
        this.p1.odbij(p);
        this.p2.odbij(p);
        this.p3.odbij(p);
    }

    public String toString(){
        return "(" + this.p1.toString() + "," + this.p2.toString() + "," + this.p3.toString() + ")";
    }

}
