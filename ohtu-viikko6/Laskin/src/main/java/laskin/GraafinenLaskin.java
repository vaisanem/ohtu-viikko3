package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GraafinenLaskin extends Pane {

    public GraafinenLaskin() {
        super();
        
        VBox layout = new VBox(10);
        
        TextField tuloskentta = new TextField("0"); 
        tuloskentta.setEditable(false);
        TextField syotekentta = new TextField(""); 
        
        HBox napit = new HBox(10); 
        Button plus = new Button("+");
        Button miinus = new Button("-");
        Button nollaa = new Button("Z");
        Button undo = new Button("undo"); 
        
        napit.getChildren().addAll(plus, miinus, nollaa, undo);
        
        Operaatiokirjasto operaatiot = new Operaatiokirjasto(tuloskentta, syotekentta, plus, miinus, nollaa, undo);
        Tapahtumankuuntelija kasittelija = new Tapahtumankuuntelija(operaatiot);
        plus.setOnAction(kasittelija);
        miinus.setOnAction(kasittelija);
        nollaa.setOnAction(kasittelija);
        undo.setOnAction(kasittelija);
        
        layout.getChildren().addAll(tuloskentta, syotekentta, napit);   
        
        getChildren().add(layout);
    }
}
