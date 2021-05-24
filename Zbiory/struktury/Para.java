package struktury;
public class Para {
    public final String klucz;
    private double wartosc;
    public Para(String klucz,double wartosc) throws  IllegalArgumentException{
        if (klucz.matches("[a-z]+")){
            this.klucz = klucz;
            this.wartosc = wartosc;
        }
        else{
            throw new IllegalArgumentException("Niepoprawny klucz");

        }


    }
    public String get_klucz(){
        return this.klucz;
    }

    public double getWartosc(){
        return this.wartosc;
    }

    public void setWartosc(double nowa_wartosc){
        this.wartosc = nowa_wartosc;
    }

    @Override
    public String toString() {
        return "Para{" +
                "klucz='" + klucz + '\'' +
                ", wartosc=" + wartosc +
                '}';
    }

    public boolean equals(Object obj){
        Para other = (Para) obj;
        return other.get_klucz() == this.klucz;

    }



}

