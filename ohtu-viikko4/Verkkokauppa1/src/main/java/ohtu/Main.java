package ohtu;

import ohtu.verkkokauppa.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        //Kirjanpito kp = new Kirjanpito();
        //Kauppa kauppa = new Kauppa(new Varasto(kp), new Pankki(kp), new Viitegeneraattori());
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
        Kauppa kauppa = (Kauppa) context.getBean(Kauppa.class);
        Kirjanpito kp = (Kirjanpito) context.getBean(Kirjanpito.class);

        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        for (String tapahtuma : kp.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
