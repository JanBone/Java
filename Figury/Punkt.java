
import java.lang.*;
public class Punkt {
    private double x;
    private double y;
    public Punkt(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Punkt() {
        this.x = 0;
        this.y = 0;
    }

    public double x(){
        return this.x;
    }

    public double y(){
        return this.y;
    }

    public void przesun(Wektor v){
        this.x += v.dx;
        this.y += v.dy;
    }

    public void obroc(Punkt p, double kat){
        kat = Math.toRadians(kat);
        this.x =p.x() + (this.x - p.x())* Math.cos(kat) - (this.y - p.y) * Math.sin(kat);
        this.y = (this.x - p.x()) * Math.sin(kat) + p.y + (this.y - p.y) * Math.cos(kat);
    }

    public void odbij(Prosta p){
        Prosta prostopadla = p.znajdz_prostopadla(this);
        Punkt przeciecie = Prosta.punkt_p(prostopadla,p); //x1 y1
        this.x = 2 * przeciecie.x() - this.x;
        this.y = 2 * przeciecie.y() - this.y;
    }


    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }
}
