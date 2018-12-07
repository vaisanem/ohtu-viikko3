package laskin;

import java.util.HashMap;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Operaatiokirjasto {
    
    private HashMap<Button, Operaatio> operaatiot;
    private Sovelluslogiikka sovellus;
    

    public Operaatiokirjasto(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.sovellus = new Sovelluslogiikka();
        this.operaatiot = new HashMap<>();
        operaatiot.put(plus, new Operaatio.Summa(sovellus, tuloskentta, syotekentta, nollaa, undo));
        operaatiot.put(miinus, new Operaatio.Erotus(sovellus, tuloskentta, syotekentta, nollaa, undo));
        operaatiot.put(nollaa, new Operaatio.Nollaus(sovellus, tuloskentta, syotekentta, nollaa, undo));
    }
    
    public Operaatio valitse(EventTarget event_target) {        
        return operaatiot.getOrDefault(event_target, null);
    }
    
}
