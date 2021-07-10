/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author Dejan
 */
public class radniNaloziTabela {
    
    private int idNaloga;
    private int brojNaloga;
    private String nazivNaloga;
    private int idProizvoda;
    private int kolicina;
    private Timestamp datumZavrsetka;
   

    public radniNaloziTabela(int idNaloga, int brojNaloga, String nazivNaloga, int idProizvoda, int kolicina, Timestamp datumZavrsetka) {
        this.idNaloga = idNaloga;
        this.brojNaloga = brojNaloga;
        this.nazivNaloga = nazivNaloga;
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
        this.datumZavrsetka = datumZavrsetka;
    }

    radniNaloziTabela(int aInt, int aInt0, String string, int aInt1, int aInt2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    radniNaloziTabela(String s, int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdNaloga() {
        return idNaloga;
    }

    public void setIdNaloga(int idNaloga) {
        this.idNaloga = idNaloga;
    }

    public int getBrojNaloga() {
        return brojNaloga;
    }

    public void setBrojNaloga(int brojNaloga) {
        this.brojNaloga = brojNaloga;
    }

    public String getNazivNaloga() {
        return nazivNaloga;
    }

    public void setNazivNaloga(String nazivNaloga) {
        this.nazivNaloga = nazivNaloga;
    }

    public int getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(int idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Timestamp getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(Timestamp datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }
    
    
    
    
    
    
}
