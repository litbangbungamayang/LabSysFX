/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.controller.ErrorMessages;
import id.buma.labsysfx.database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class VarietasDAOSQL implements VarietasDAO {
    
    private final ErrorMessages errMsg = new ErrorMessages();

    @Override
    public String getNamaVarietas(String idVarietas) {
        String namaVarietas = null;
        String sql = "select * from tbl_varietas where id_varietas = (?)";
        try (Connection conn = DB.getConn()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idVarietas);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                namaVarietas = rs.getString("nama_varietas");
            }
        } catch (SQLException ex) {
            errMsg.showErrorAlert("Error DAO getNamaVarietas! " + "\n" +
                    "Error code : " + ex.getMessage());
        }
        return namaVarietas;
    }
    
}
