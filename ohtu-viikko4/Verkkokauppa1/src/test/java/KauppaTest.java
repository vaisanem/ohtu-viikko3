
import ohtu.verkkokauppa.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;
    
    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        varasto = mock(Varasto.class);
        
        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
    }
    
    @Test
    public void kahdenEriOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));              
        // määritellään että tuote numero 2 on lanttu jonka hinta on 1 ja saldo 1
        when(varasto.saldo(2)).thenReturn(1); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "lanttu", 1));              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli lanttu
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 6);   
    }
    
    @Test
    public void kahdenSamanOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));              ;              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);   
    }
    
    @Test
    public void loppuneenTuotteenOstossaPankinMetodiaTilisiirtoKutsutaanOikein() {
        // määritellään että tuote numero 2 on lanttu jonka hinta on 1 ja saldo 1
        when(varasto.saldo(2)).thenReturn(1); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "lanttu", 1));
        // määritellään että tuote numero 3 on ämpäri jonka hinta on 1 ja saldo 0
        when(varasto.saldo(3)).thenReturn(0); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "ämpäri", 1));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli lanttu
        k.lisaaKoriin(3);     // ostetaan tuotetta numero 3 eli ämpäri
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 1);   
    }
    
    @Test
    public void poistetunTuotteenOstossaPankinMetodiaTilisiirtoKutsutaanOikein() {
        // määritellään että tuote numero 2 on lanttu jonka hinta on 1 ja saldo 1
        when(varasto.saldo(2)).thenReturn(1); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "lanttu", 1));
        // määritellään että tuote numero 3 on ämpäri jonka hinta on 1 ja saldo 0
        when(varasto.saldo(3)).thenReturn(0); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "ämpäri", 1));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli lanttu
        k.lisaaKoriin(3); // ostetaan tuotetta numero 3 eli ämpäri
        k.poistaKorista(2);
        k.poistaKorista(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 0);   
    }
    
    @Test
    public void kaksiOstostaOvatRiippumattomia() {
        ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein();
        kahdenEriOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikein();
    }
    
    @Test
    public void ostoksenPaatyttyaViitegeneraattorinMetodiaUusiKutsutaan() {
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));              ;              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        // tehdään taas ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        // tehdään vielä ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu oikein
        verify(viite, times(3)).uusi();
    }
    
}
