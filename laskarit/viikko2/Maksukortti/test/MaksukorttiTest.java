/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sampo
 */
public class MaksukorttiTest {
    
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
     @Test
     public void hello() {}
     
     @Test
     public void konstruktoriAsettaaSaldonOikein() {
         assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiVahentaaSaldoaOikein() {
         kortti.syoEdullisesti();
         
         assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiVahentaaSaldoaOikein() {
         kortti.syoMaukkaasti();
         
         assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         kortti.syoEdullisesti();
         
         assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         
         assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
     }
     
     @Test
     public void kortilleVoiLadataRahaa() {
         kortti.lataaRahaa(25);
         
         assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
     }
     
     @Test
     public void kortinSaldoEiYlitaMaksimiArvoa() {
         kortti.lataaRahaa(200);
         
         assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
     }
     
     @Test
     public void negatiivisenSummanLataaminenEiMuutaSaldoa() {
         kortti.lataaRahaa(-10);
         
         assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void kortillaVoidaanOstaaMaukasAteriaKunSaldoOnNelja() {
         Maksukortti kortti4 = new Maksukortti(4);
         
         kortti4.syoMaukkaasti();
         
         assertEquals("Kortilla on rahaa 0.0 euroa", kortti4.toString());
     }
     
     @Test
     public void kortillaVoidaanOstaaEdullinenAteriaKunSaldoOnKaksiPuoli() {
         Maksukortti kortti2puoli = new Maksukortti(2.5);
         
         kortti2puoli.syoEdullisesti();
         
         assertEquals("Kortilla on rahaa 0.0 euroa", kortti2puoli.toString());
     }
}
