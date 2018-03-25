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

/**
 *
 * @author jpssilve
 */
public class KassapaateTest {
    
    private Kassapaate kassapaate;
    private Maksukortti kortti;
    private Maksukortti edullinenEiTarpeeksi;
    private Maksukortti maukasEiTarpeeksi;
    
    public KassapaateTest() {
    }
   
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
        edullinenEiTarpeeksi = new Maksukortti(230);
        maukasEiTarpeeksi = new Maksukortti(390);
    }
    
    @Test
    public void uudenRahamaaraOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void uudenMyytyjenEdullistenLounaidenMaaraOikein() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void uudenMyytyjenMaukkaidenLounaidenMaaraOikein() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenKateisOstoPalauttaaOikeanVaihtoRahan() {
        assertEquals(260, kassapaate.syoEdullisesti(500));
    }
    
    @Test
    public void maukkaanKateisOstoPalauttaaOikeanVaihtorahan() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void edullisenKateisOstoKasvattaaKassaaOikein() {
        kassapaate.syoEdullisesti(500);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanKateisOstoKasvttaaKassaaOikein() {
        kassapaate.syoMaukkaasti(600);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullinenJosRahaEiRiitaKassaEiKasva() {
        kassapaate.syoEdullisesti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukasJosRahatEiRiitaKasssaEiKasva() {
        kassapaate.syoMaukkaasti(300);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullinenJosRahaEiRiitaOikeaVaihtoraha() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
    }
    
    @Test
    public void maukasJosRahaEiRiitaOikeaVaihtoRaha() {
        assertEquals(300, kassapaate.syoMaukkaasti(300));
    }
    
    @Test
    public void edullinenJosRahaEiRiitaMyytyjenLounaidenMaaraSailyy() {
        kassapaate.syoEdullisesti(210);
        kassapaate.syoMaukkaasti(230);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasJosRahatEiRiitaMyytyjenLounaidenMaaraSailyy() {
        kassapaate.syoMaukkaasti(250);
        kassapaate.syoMaukkaasti(390);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenJosRahatRiittaaMyytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(240);
        kassapaate.syoEdullisesti(250);
        kassapaate.syoEdullisesti(500);
        assertEquals(3, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasJosRahatRiittaaMyytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(400);
        kassapaate.syoMaukkaasti(500);
        assertEquals(2, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiOstoPalauttaaTrueJosRahatRiittaa() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukasKorttiOstoPalauttaTrueJosRahatRiittaa() {
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullinenRahatEiRiitaKorttiPalauttaaFalse() {
        assertFalse(kassapaate.syoEdullisesti(edullinenEiTarpeeksi));
    }
    
    @Test
    public void maukasRahatEiRiitaKorttiPalauttaaFalse() {
        assertFalse(kassapaate.syoMaukkaasti(maukasEiTarpeeksi));
    }
    
    @Test
    public void edullinenKortiltaVeloitetaan() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void maukasKortiltaVeloitetaan() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void edullinenJosRahatEiRiitaKortinSaldoEiMuutu() {
        kassapaate.syoEdullisesti(edullinenEiTarpeeksi);
        assertEquals(230, edullinenEiTarpeeksi.saldo());
    }
    
    @Test
    public void maukasJosRahatEiRiitaKortinSaldoEiMuutu() {
        kassapaate.syoMaukkaasti(maukasEiTarpeeksi);
        assertEquals(390, maukasEiTarpeeksi.saldo());
    }
    
    @Test
    public void edullinenKorttiOstoKasvattaaMyytyja() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKorttiOstoKasvattaaMyytyja() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiOstoEiKasvataMyytyjaJosRahatEiRiita() {
        kassapaate.syoEdullisesti(edullinenEiTarpeeksi);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKorttiOsotEiKasvataMyytyjaJosRahatEiRiita() {
        kassapaate.syoMaukkaasti(maukasEiTarpeeksi);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKassanRahaMaaraEiMuutuKorttiostossa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    } 
    
    @Test
    public void maukasKassanRahaMaaraEiMuutuKorttiostossa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void lataaminenKasvattaaKortinSaldoa() {
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaKassaaOikein() {
        kassapaate.lataaRahaaKortille(kortti, 600);
        assertEquals(100600, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void negatiivistaSaldoEivoiLadataKassa() {
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void negatiivistaSaldoaEiVoiLadataKortti() {
        kassapaate.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
    }
            
}
