package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;

public class Tapahtumankuuntelija implements EventHandler {
    
    private Operaatiokirjasto operaatiot;
    private Operaatio edellinen;

    public Tapahtumankuuntelija(Operaatiokirjasto operaatiot) {
        this.operaatiot = operaatiot;
        this.edellinen = null;
    }
    
    @Override
    public void handle(Event event) {
        Operaatio seuraava = operaatiot.valitse(event.getTarget());
        if (seuraava != null) {
            seuraava.suorita();
            edellinen = seuraava;
        } else {
            edellinen.peruuta();
            edellinen = null;
        }
    }
}
