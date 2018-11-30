
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alustaJoukko(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasitteetti ei saa olla negatiivinen");//heitin vaan jotain :D
        }
        alustaJoukko(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IllegalArgumentException("Kapasitteetti ei saa olla negatiivinen");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei saa olla negatiivinen");//heitin vaan jotain :D
        }
        alustaJoukko(kapasiteetti, kasvatuskoko);

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (alkioidenLkm == joukko.length) {
                int[] uusiJoukko = new int[alkioidenLkm + kasvatuskoko];
                System.arraycopy(joukko, 0, uusiJoukko, 0, alkioidenLkm);
                joukko = uusiJoukko;
            }
            joukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (joukko[i] == luku) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (joukko[i] == luku) { //siis luku löytyy tuosta kohdasta :D
                joukko[i] = joukko[alkioidenLkm-1];
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }
    
    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += joukko[i];
                tuotos += ", ";
            }
            tuotos += joukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] getJoukonKopio() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(joukko, 0, taulu, 0, alkioidenLkm);
        return taulu;
    }
    
    public int[] getJoukko() {
       return joukko; 
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) { //Valmis eristettäväksi JoukkoOperaatiot-luokkaan
        IntJoukko yhdiste = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            yhdiste.lisaa(a.getJoukko()[i]);
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            yhdiste.lisaa(b.getJoukko()[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) { //Valmis eristettäväksi JoukkoOperaatiot-luokkaan
        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            if (b.kuuluu(a.getJoukko()[i])) {
                leikkaus.lisaa(a.getJoukko()[i]);
            }
        }
        return leikkaus;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) { //Valmis eristettäväksi JoukkoOperaatiot-luokkaan
        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            erotus.lisaa(a.getJoukko()[i]);
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            erotus.poista(b.getJoukko()[i]);
        }
        return erotus;
    }
    
    private void alustaJoukko(int kapasiteetti, int kasvatuskoko) {
        joukko = new int[kapasiteetti];
        for (int i = 0; i < joukko.length; i++) {
            joukko[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
        
}