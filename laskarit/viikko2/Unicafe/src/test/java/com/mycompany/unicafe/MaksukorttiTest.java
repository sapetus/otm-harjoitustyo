package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {
    
    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikea() {
        assertEquals("Saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLisaaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        
        assertEquals("Saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
        assertEquals(true, kortti.otaRahaa(10));
    }
    
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(10);
        
        assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneJosRahaaEiOleTarpeeksi() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void saldoEiMuutuJosSaldoEiRiita() {
        kortti.otaRahaa(100);
        
        assertEquals(10, kortti.saldo());
    }
}
