package ohtu.kivipaperisakset;

public abstract class Peli {
    
    public static Peli uusiKPSPelaajaVsPelaaja(IO io) {
        return new KPSPelaajaVsPelaaja(io, new Tuomari());
    }
    
    public static Peli uusiKPSTekoaly(IO io) {
        return new KPSTekoaly(io, new Tuomari(), new Tekoaly());
    }
    
    public static Peli uusiKPSParempiTekoaly(IO io, int muistikapasiteetti) {
        return new KPSTekoaly(io, new Tuomari(), new TekoalyParannettu(muistikapasiteetti));
    }
    
    abstract void pelaa();
    
}
