/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Timestamp;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Dejan
 */
public class zastojiTabela {
    
    private Timestamp zastojPocetak;
    private Timestamp zastojKraj;
    private Timestamp trajanjeZastoja;
    private ComboBox idZastoja;

    public zastojiTabela(Timestamp zastojPocetak, Timestamp zastojKraj, Timestamp trajanjeZastoja,ObservableList vrsteZastojaLista) {
        this.zastojPocetak = zastojPocetak;
        this.zastojKraj = zastojKraj;
        this.trajanjeZastoja = trajanjeZastoja;
        this.idZastoja = new ComboBox(vrsteZastojaLista);
    }



    public Timestamp getZastojPocetak() {
        return zastojPocetak;
    }

    public void setZastojPocetak(Timestamp zastojPocetak) {
        this.zastojPocetak = zastojPocetak;
    }

    public Timestamp getZastojKraj() {
        return zastojKraj;
    }

    public void setZastojKraj(Timestamp zastojKraj) {
        this.zastojKraj = zastojKraj;
    }

    public Timestamp getTrajanjeZastoja() {
        return trajanjeZastoja;
    }

    public void setTrajanjeZastoja(Timestamp trajanjeZastoja) {
        this.trajanjeZastoja = trajanjeZastoja;
    }

    public ComboBox getIdZastoja() {
        return idZastoja;
    }

    public void setIdZastoja(ComboBox idZastoja) {
        this.idZastoja = idZastoja;
    }

    
    
    

    
}
