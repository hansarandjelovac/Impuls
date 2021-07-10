/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dejan
 */
public class DbConnection {
    String ip;
         

    public static Connection Connect(){
          String ip = "10.0.0.120";

        try {
            
            String url = "jdbc:mysql://"+ ip + "/impuls?serverTimezone=Europe/Belgrade";
            String user = "root";
            String password = "ubiquiti";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Konekcija sa bazom " + ip);
            return conn;
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public boolean isDbConnected(Connection conn) {
    try {
        return conn != null && !conn.isClosed();
    } catch (SQLException ignored) {}

    return false;
}

}
