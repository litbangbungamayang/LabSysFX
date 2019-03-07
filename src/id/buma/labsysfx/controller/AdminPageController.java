/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class AdminPageController implements Initializable{
    
    //<editor-fold defaultstate="collapsed" desc="FXML Definitions">
    @FXML
    private TabPane containerAdmin;
    @FXML
    private Tab pageDashboard;
    @FXML
    private Tab adminUser;
    @FXML
    private JFXButton btnAdminUser;
    @FXML
    private JFXButton btnKembali;
    
    //</editor-fold>
    
    private MainScreenController msc;

    public AdminPageController(){
        
    }
    
    public void setMainScreenController(MainScreenController msc){
        this.msc = msc;
    }
    
    public void showDashboard(){
        containerAdmin.getSelectionModel().select(pageDashboard);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdminUser.setOnAction((event) -> {
            containerAdmin.getSelectionModel().select(adminUser);
        });
        btnKembali.setOnAction((event) -> {
            msc.getTabPane().getSelectionModel().select(msc.getTabMainMenu());
        });
    }
    
}
