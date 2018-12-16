package ohtu.kivipaperisakset;

public abstract class KPS extends Peli {

    protected static IO io;
    protected Toimitsija toimitsija;
    protected String ekanSiirto;
    protected String tokanSiirto;

    public KPS(IO io, Toimitsija toimitsija) {
        this.io = io;
        this.toimitsija = toimitsija;
    }

    public void pelaa() {
        ekanSiirto();
        tokanSiirto();

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            toimitsija.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(toimitsija);
            System.out.println();

            ekanSiirto();
            
            tokanSiirto();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(toimitsija);
    }

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    void ekanSiirto() {
        System.out.println("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = io.nextLine();
    }
    
    abstract void tokanSiirto();
}
