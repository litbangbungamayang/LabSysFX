/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.controller.ErrorMessages;
import id.buma.labsysfx.database.DB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class AnalisaTebuDAOSQL implements AnalisaTebuDAO {
    
    private final ErrorMessages errMsg = new ErrorMessages();

    @Override
    public int getRonde(String kodePetak) {
        String sql = "call getRonde(?)";
        try (Connection conn = DB.getConn()){
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, kodePetak);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                return rs.getInt("hasil");
            }
        } catch (SQLException ex) {
            errMsg.showErrorAlert("Error DAO getRonde!" + "\n" + 
                    "Error code : " + ex.getMessage());
        }
        return 0;
    }
    
}
