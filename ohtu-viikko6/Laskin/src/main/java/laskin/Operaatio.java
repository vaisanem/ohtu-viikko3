package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public interface Operaatio {
    
    public void suorita();
    public void peruuta();
    
    public class Sovellusoperaatio {
        
        protected Sovelluslogiikka sovellus;
        protected Integer edellinen;
        private TextField tuloskentta;
        private TextField syotekentta;
        private Button nollaa;
        private Button undo;

        public Sovellusoperaatio(Sovelluslogiikka sovellus, TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo) {
            this.sovellus = sovellus;
            this.edellinen = null;
            this.tuloskentta = tuloskentta;
            this.syotekentta = syotekentta;
            this.nollaa = nollaa;
            this.undo = undo;
            paivita();
        }
        
        public int syote() {
            int arvo = 0;
 
            try {
                arvo = Integer.parseInt(syotekentta.getText());
            } catch (Exception e) {
            }
            
            return arvo;
        }
        
        public void peruuta() {
            sovellus.palauta(edellinen);
            edellinen = null;
            paivita();
        }
        
        public void paivita() {
            syotekentta.setText("");
            tuloskentta.setText("" + sovellus.tulos());
            if (sovellus.tulos() == 0) {
                nollaa.setDisable(true);
            } else nollaa.setDisable(false);
            if (edellinen == null) {
                undo.setDisable(true);
            } else undo.setDisable(false);
        }
        
    }
    
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
