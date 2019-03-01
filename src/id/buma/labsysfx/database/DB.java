/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.database;

import id.buma.labsysfx.controller.ErrorMessages;
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
   private static Connection conn;
   private static ErrorMessages errMsg;
    
    public static Connection getConn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String server_lokal = "jdbc:mysql://localhost:3306/db_litbang?user=root&password=adminBUMA789";
            String server_kandir = "jdbc:mysql://192.168.208.98:3306/db_litbang?user=admintr&password=ptpn7@jaya&useSSL=false";
            String server_buma = "jdbc:mysql://192.168.39.150:3306/db_litbang?user=root&password=tiptpn7&useSSL=false";
            conn = DriverManager.getConnection(server_lokal);
        } catch (ClassNotFoundException | SQLException ex) {
            errMsg.showErrorAlert("Database tidak terkoneksi! " + ex.toString());
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    public static boolean isConnect(){
        try {
            return getConn() != null;
        } catch (Exception ex) {
            errMsg.showErrorAlert(ex.getMessage());
        }
        return false;
    }
}
