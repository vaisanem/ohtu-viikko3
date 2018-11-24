
package ohtu.maksukortti;

import ohtu.maksukortti.Maksukortti;
import ohtu.maksukortti.Kassapaate;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = mock(Maksukortti.class);
    }
    
    @Test
    public void kortiltaVeloitetaanHintaJosRahaaOn() {
        when(kortti.getSaldo()).thenReturn(10);
        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti).osta(eq(Kassapaate.HINTA));
    }

    @Test
    public void kortiltaEiVeloitetaJosRahaEiRiita() {
        when(kortti.getSaldo()).thenReturn(4);
        kassa.ostaLounas(kortti);
        
        verify(kortti, times(1)).getSaldo();
        verify(kortti, times(0)).osta(anyInt());
    }
    
    @Test
    public void positiivinenRahamaaraLadataanKortille() {
        kassa.lataa(kortti, 1);
        
        verify(kortti).lataa(1);
    }
    
    @Test
    public void negatiivistaRahamaaraaEiLadataKortille() {
        kassa.lataa(kortti, -1);
        
        verify(kortti, times(0)).lataa(anyInt());
    }
      
}
