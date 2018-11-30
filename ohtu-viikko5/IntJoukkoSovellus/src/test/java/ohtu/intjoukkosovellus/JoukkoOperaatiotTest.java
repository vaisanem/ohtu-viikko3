
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {
    
    
    @Test
    public void testSomething() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(3,4);
        
        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.getJoukonKopio();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }
    
    @Test
    public void leikkausTest() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(2,3);
        
        IntJoukko tulos = IntJoukko.leikkaus(eka, toka);
        int[] vastauksenLuvut = tulos.getJoukonKopio();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {2};
        
        assertArrayEquals(odotettu, vastauksenLuvut);
    }
    
    @Test
    public void erotusTest() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(2,3);
        
        IntJoukko tulos = IntJoukko.erotus(toka, eka);
        int[] vastauksenLuvut = tulos.getJoukonKopio();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {3};
        
        assertArrayEquals(odotettu, vastauksenLuvut);
    }

    private IntJoukko teeJoukko(int... luvut) {
        IntJoukko joukko = new IntJoukko();
        
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
}
