package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public interface Operaatio {
    
    public void suorita();
    public void peruuta();
    
    public class Summa extends Sovellusoperaatio implements Operaatio {

        public Summa(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
            super(sovellus, tuloskentta, syotekentta, nollaa, undo);
        }

        @Override
        public void suorita() {
            edellinen = sovellus.tulos();
            sovellus.plus(syote());
            paivita();
        }

        @Override
        public void peruuta() {
            super.peruuta();
        }
    }
    
    public class Erotus extends Sovellusoperaatio implements Operaatio {

        public Erotus(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
            super(sovellus, tuloskentta, syotekentta, nollaa, undo);
        }

        @Override
        public void suorita() {
            edellinen = sovellus.tulos();
            sovellus.miinus(syote());
            paivita();
        }
        
        @Override
        public void peruuta() {
            super.peruuta();
        }
    }
    
    public class Nollaus extends Sovellusoperaatio implements Operaatio {

        public Nollaus(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
            super(sovellus, tuloskentta, syotekentta, nollaa, undo);
        }

        @Override
        public void suorita() {
            edellinen = sovellus.tulos();
            sovellus.nollaa();
            paivita();
        }
        
        @Override
        public void peruuta() {
            super.peruuta();
        }
    }
}
