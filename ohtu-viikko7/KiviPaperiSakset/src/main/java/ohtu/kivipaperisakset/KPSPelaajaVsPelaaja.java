package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KPS {

    public KPSPelaajaVsPelaaja(IO io, Toimitsija toimitsija) {
        super(io, toimitsija);
    }

    @Override
    void tokanSiirto() {
        System.out.println("Toisen pelaajan siirto: ");
        tokanSiirto = io.nextLine();
    }
    
    
}