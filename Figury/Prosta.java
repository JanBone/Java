

public class Prosta {
    public final double a;
    public final double b;
    public final double c;

    public Prosta(double a, double b, double c) throws IllegalArgumentException{
        this.a = a;
        this.b = b;
        this.c = c;

        if (a*a + b*b == 0)
        {
            throw new IllegalArgumentException("To nie jest prosta");
        }
    }
    public static Prosta przesun(Prosta p, Wektor w){
        return new Prosta(p.a, p.b, p.c + w.dx*p.a + w.dy*p.b);
    }

    public static boolean rownolegle(Prosta p1, Prosta p2){
        if (p1.a * p2.b - p2.a*p1.b == 0){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean prostopadle(Prosta p1, Prosta p2){
        if (p1.a * p2.a + p1.b*p2.b == 0){
            return true;
        }
        else {
            return false;
        }
    }
    public static Punkt punkt_p(Prosta p1, Prosta p2) throws IllegalArgumentException{  //punkt przeciecia
        if (rownolegle(p1, p2)){
            throw new IllegalArgumentException("Proste są rownoległe");
        }
        else {
            double w = p1.a * p2.b - p2.a * p1.b;
            double wx = -p1.c * p2.b + p2.c * p1.b;
            double wy = -p1.a * p2.c + p2.a * p1.c;
            return new Punkt(wx/w,wy/w);
        }
    }

    public Prosta  znajdz_prostopadla(Punkt pkt){ ///znajdowanie prostopadlej prostej p przechodzacej przez punkt pkt
        Prosta p1 = new Prosta(this.b,-this.a,this.a*pkt.y()-this.b*pkt.x());
        return p1;
    }
    public String toString(){
        return "(" + this.a + "," + this.b + "," + this.c + ")";
    }
}
