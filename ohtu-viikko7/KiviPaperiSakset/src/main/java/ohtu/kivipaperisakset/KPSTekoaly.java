package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPS {
    
    private ITekoaly tekoaly;

    public KPSTekoaly(IO io, Toimitsija toimitsija, ITekoaly tekoaly) {
        super(io, toimitsija);
        this.tekoaly = tekoaly;
    }

    @Override
    void tokanSiirto() {
        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
    }
}