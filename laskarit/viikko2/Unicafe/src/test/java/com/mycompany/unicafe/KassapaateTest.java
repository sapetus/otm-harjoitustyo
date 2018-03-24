/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti kortti;
    Maksukortti korttiEiTarpeeksiSaldoa;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
        korttiEiTarpeeksiSaldoa = new Maksukortti(100);
    }

    @Test
    public void kassassaOikeaMaaraRahaaJaMyytyjaAterioitaEiOle() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenAterianOstoToimiiRiittavallaKateisella() {
        assertEquals(kassapaate.syoEdullisesti(500), 260);
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
    }

    @Test
    public void edullisenAterianOstoEiToimiRiittamattomallaKateisella() {
        assertEquals(kassapaate.syoEdullisesti(200), 200);
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void edullisenAterianOstoToimiiKortilla() {
        assertEquals(kassapaate.syoEdullisesti(kortti), true);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 1);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    @Test
    public void edullisenAterianOstoEiToimiKortillaJollaEiOleTarpeeksiSaldoa() {
        assertEquals(kassapaate.syoEdullisesti(korttiEiTarpeeksiSaldoa), false);
        assertEquals(korttiEiTarpeeksiSaldoa.saldo(), 100);
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void maukkaanAterianOstoToimiiRiittavallaKateisella() {
        assertEquals(kassapaate.syoMaukkaasti(500), 100);
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
    }

    @Test
    public void maukkaanAterianOstoEiToimiRiittamattomallaKateisella() {
        assertEquals(kassapaate.syoMaukkaasti(200), 200);
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void maukkaanAterianOstoToimiiKortilla() {
        assertEquals(kassapaate.syoMaukkaasti(kortti), true);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 1);
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    @Test
    public void maukkaanAterianOstoEiToimiKortillaJollaEiOleTarpeeksiSaldoa() {
        assertEquals(kassapaate.syoMaukkaasti(korttiEiTarpeeksiSaldoa), false);
        assertEquals(korttiEiTarpeeksiSaldoa.saldo(), 100);
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kortinJaKassapaatteenSaldoMuuttuuKortilleRahaaLadattaessa() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertEquals(2000, kortti.saldo());
        assertEquals(101000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortinJaKassapaatteenSaldoEiMuutuLadattaessaNegatiivinenLuku() {
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
