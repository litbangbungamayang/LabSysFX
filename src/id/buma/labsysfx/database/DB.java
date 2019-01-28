/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 * 
 */

public class DB {
   private Connection conn;
    
    public Connection getConnSimpg(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String simpg_lokal = "jdbc:mysql://localhost:3306/simpg?user=root&password=adminBUMA789";
            String simpg_kandir = "jdbc:mysql://192.168.208.98:3306/simpg?user=admintr&password=ptpn7@jaya&useSSL=false";
            String simpg_buma= "jdbc:mysql://192.168.39.150:3306/simpg?user=root&password=tiptpn7&useSSL=false";
            conn = DriverManager.getConnection(simpg_buma);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    } 
}
