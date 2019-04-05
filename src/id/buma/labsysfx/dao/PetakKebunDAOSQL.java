/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.controller.ErrorMessages;
import id.buma.labsysfx.database.DB;
import id.buma.labsysfx.model.PetakKebun;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class PetakKebunDAOSQL implements PetakKebunDAO {
    
    private final ErrorMessages errorMsg;

    public PetakKebunDAOSQL() {
        this.errorMsg = new ErrorMessages();
    }

    @Override
    public ObservableList<PetakKebun> getAllPetak() {
        ObservableList<PetakKebun> listPetak = FXCollections.observableArrayList();
        String sql = "select * from tbl_master_petak order by rayon";
        try (Connection conn = DB.getConn()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                PetakKebun pk = new PetakKebun(
                        rs.getString("kode_petak"),
                        rs.getString("no_kontrak"),
                        rs.getString("nama_kebun"),
                        rs.getString("no_petak"),
                        rs.getDouble("luas_petak"),
                        rs.getString("masa_tanam"),
                        rs.getString("varietas"),
                        rs.getString("kategori"),
                        rs.getString("rayon")
                );
                listPetak.add(pk);
            }
        } catch (SQLException ex) {
            errorMsg.showErrorAlert("Error DAO getAllPetak!" + "\n" + 
                    "Error code : " + ex.getMessage());
        }
        return listPetak;
    }
    
}
