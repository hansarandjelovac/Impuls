/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.DbConnection.Connect;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import javafx.application.Application;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableArray;
import javafx.concurrent.Task;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Dejan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label kocke;
    //public int brojKocki;
    @FXML
    private Label aktivanNalog;
    @FXML
    private Label napravljenoKomada;

    @FXML
    private TableColumn<radniNaloziTabela, Integer> colIdNaloga;
    @FXML
    private TableColumn<radniNaloziTabela, Integer> colBrojNaloga;
    @FXML
    private TableColumn<radniNaloziTabela, String> colNazivNaloga;
    @FXML
    private TableColumn<radniNaloziTabela, Integer> colProizvod;
    @FXML
    private TableColumn<radniNaloziTabela, Integer> colKolicina;
    @FXML
    private TableColumn<radniNaloziTabela, Timestamp> colDatumZavrsetka;
    @FXML
    private TableView<radniNaloziTabela> tableView;
    private ObservableList<radniNaloziTabela> data;
    private ObservableList<zastojiTabela> zastojiLista;
    private ObservableList<vrsteZastoja> vrsteZastojaLista;
    private int brojKocki;

    @FXML
    private TableView<zastojiTabela> tableViewZastoji;
    @FXML
    private TableColumn<zastojiTabela, Timestamp> colPocetakZastoja;
    @FXML
    private TableColumn<zastojiTabela, Timestamp> colKrajZastoja;
    @FXML
    private TableColumn<zastojiTabela, Timestamp> colTrajanjeZastoja;
    @FXML
    private TableColumn<zastojiTabela, ComboBox> colDefinicijaZastoja;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iscitavanjeNalogaClass task = new iscitavanjeNalogaClass();
        new Thread(task).start();

        iscitavanjeZastojaClass taskIscitavanjeZastoja = new iscitavanjeZastojaClass();
        new Thread(taskIscitavanjeZastoja).start();

        brojanjeClass taskBrojanje = new brojanjeClass();
        new Thread(taskBrojanje).start();

        aktNalogClass aktNalog = new aktNalogClass();
        new Thread(aktNalog).start();

        napravljenoKomadaClass danasNapravljeno = new napravljenoKomadaClass();
        new Thread(danasNapravljeno).start();

    }

    public class iscitavanjeNalogaClass extends Task {

        @Override
        public void run() {

            while (true) {
                try {
                    Connection conn = DbConnection.Connect();
                    data = FXCollections.observableArrayList();
                    ResultSet rs = conn.createStatement().executeQuery("select * from radniNalozi");
                    while (rs.next()) {
                        data.add(new radniNaloziTabela(rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getTimestamp(8)));
                    }
                    rs.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println("Error" + ex);
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                colIdNaloga.setCellValueFactory(new PropertyValueFactory<>("idNaloga"));
                colBrojNaloga.setCellValueFactory(new PropertyValueFactory<>("brojNaloga"));
                colNazivNaloga.setCellValueFactory(new PropertyValueFactory<>("nazivNaloga"));
                colProizvod.setCellValueFactory(new PropertyValueFactory<>("idProizvoda"));
                colKolicina.setCellValueFactory(new PropertyValueFactory<>("kolicina"));
                colDatumZavrsetka.setCellValueFactory(new PropertyValueFactory<>("datumZavrsetka"));

                tableView.setItems(null);
                tableView.setItems(data);
                try {
                    Thread.sleep(5000);
                    System.out.println("TIMEOUT nalozi");
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        protected Object call() throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public class iscitavanjeZastojaClass extends Task {

        @Override
        public void run() {

            //PRAVLJENJE LISTE ZA KOMBO ID ZASTOJA               
            ObservableList vrsteZastojaLista = FXCollections.observableArrayList();
            Connection conn = DbConnection.Connect();
            ResultSet rs = null;
            try {
                rs = conn.createStatement().executeQuery("select * from vrsteZastoja");
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs.next()) {
                    vrsteZastojaLista.add(rs.getString("nazivZastoja"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs.close();

            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //while (true) {

                //PRAVLJENJE LISTE ZASTOJA          
                try {
                    // Connection conn = DbConnection.Connect();
                    zastojiLista = FXCollections.observableArrayList();
                    ResultSet rs1 = conn.createStatement().executeQuery("select * from zastoji");
                    System.out.println(vrsteZastojaLista);
                    while (rs1.next()) {

                        zastojiLista.add(new zastojiTabela(rs1.getTimestamp("zastojPocetak"), rs1.getTimestamp("zastojKraj"), rs1.getTimestamp("trajanjeZastoja"), FXCollections.observableArrayList(vrsteZastojaLista)));

                    }
                    rs1.close();

                } catch (SQLException ex) {
                    System.err.println("Error" + ex);
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }

                colPocetakZastoja.setCellValueFactory(new PropertyValueFactory<>("zastojPocetak"));
                colKrajZastoja.setCellValueFactory(new PropertyValueFactory<>("zastojKraj"));
                colTrajanjeZastoja.setCellValueFactory(new PropertyValueFactory<>("trajanjeZastoja"));
                colDefinicijaZastoja.setCellValueFactory(new PropertyValueFactory<>("idZastoja"));

                System.out.println(vrsteZastojaLista);

                tableViewZastoji.setItems(null);
                tableViewZastoji.setItems(zastojiLista);
                

                
//                while (true){
//                    Platform.runLater(()->{
//                    tableViewZastoji.getColumns().get(0).setVisible(false);
//                    tableViewZastoji.getColumns().get(0).setVisible(true);
//                    });
//                    try {
//                    Thread.sleep(5000);
//                    System.out.println("TIMEOUT");
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                }
                

           // }
 
        }

        @Override
        protected Object call() throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public class brojanjeClass extends Task {

        private int brojKocki;

        @Override
        public void run() {
            int brojKocki = 0;

            while (true) {

                try {

                    Connection conn = DbConnection.Connect();
                    String query = "SELECT COUNT(*) FROM komadi WHERE id > (SELECT MAX(ID) FROM komadi WHERE gotovKomad IS NOT NULL);";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    rs.next();

                    this.brojKocki = rs.getInt(1);
                    Platform.runLater(() -> {;
                        kocke.setText(Integer.toString(brojKocki));
                        System.out.println(brojKocki);
                    });
                    rs.close();
                    st.close();
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                }

            }

        }

        @Override
        protected Object call() throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public class aktNalogClass extends Task {

        public String nalogBroj;


        @Override
        public void run() {

            while (true) {

                try {

                    Connection conn = DbConnection.Connect();
                    String query = "SELECT idNaloga from radniNalozi where status = 'A'";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    rs.next();

                    this.nalogBroj = rs.getString(1);
                    Platform.runLater(() -> {
                        aktivanNalog.setText(nalogBroj);
                        System.out.println(nalogBroj);
                    });
                    rs.close();
                    st.close();
                    conn.close();
                } catch (SQLException ex) {

                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        protected Object call() throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public class napravljenoKomadaClass extends Task {

        public int napravljenoDanas;

        //int brojKocki;
        @Override
        public void run() {

            while (true) {

                try {

                    Connection conn = DbConnection.Connect();
                    String query = "select count(*) from komadi where gotovKomad = '1' and DATE(vreme) = curdate()";
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    rs.next();

                    this.napravljenoDanas = rs.getInt(1);
                    Platform.runLater(() -> {
                        napravljenoKomada.setText(Integer.toString(napravljenoDanas));
                        System.out.println(napravljenoDanas);
                    });
                    rs.close();
                    st.close();
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

                }

            }
        }

        @Override
        protected Object call() throws Exception {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
