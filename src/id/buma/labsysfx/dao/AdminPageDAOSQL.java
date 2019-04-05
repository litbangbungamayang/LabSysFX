/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.controller.ErrorMessages;
import id.buma.labsysfx.database.DB;
import id.buma.labsysfx.model.UserLab;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class AdminPageDAOSQL implements AdminPageDAO {
    
    private final ErrorMessages alert;
    
    public AdminPageDAOSQL(){
        this.alert = new ErrorMessages();
    }

    @Override
    public UserLab getLogin(String username, String password) {
        UserLab userLab = null;
        String sql = "select * from tbl_user where login_name = ? and password = ?";
        try (Connection conn = DB.getConn()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                userLab = new UserLab(
                        rs.getInt("id_user"),
                        rs.getString("nama_user"), 
                        rs.getString("login_name"), 
                        rs.getString("password"), 
                        rs.getString("bagian"), 
                        rs.getString("role")
                );
            }
        } catch (SQLException ex){
            alert.showErrorAlert("Error getLogin!\nError code : " + ex.toString());
        }
        return userLab;
    }
    
}
