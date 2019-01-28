/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import id.buma.labsysfx.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author  Bayu Anandavi Muhardika
 * 
 */

public class LaboratPabrikController implements Initializable {

    @FXML
    private JFXButton btnSubMenuDashboard;
    @FXML
    private JFXButton btnSubMenuKelilingDepan;
    @FXML
    private JFXButton btnSubMenuKelilingBelakang;
    @FXML
    private JFXButton btnBackMainMenu;
    @FXML
    private TabPane containerLaborat;
    @FXML
    private Tab pageDashboard;
    @FXML
    private Tab pageKelilingDepan;
    @FXML
    private Tab pageKelilingBelakang;
    
    private MainApp mainApp;
    
    private MainScreenController msc;
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public LaboratPabrikController(){
        
    }
    
    public void setMainScreenController(MainScreenController msc){
        this.msc = msc;
    }
    
    public void showDashboard(){
        containerLaborat.getSelectionModel().select(pageDashboard);
    }
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSubMenuKelilingDepan.setOnAction((event) -> {
            containerLaborat.getSelectionModel().select(pageKelilingDepan);
        });
        btnSubMenuDashboard.setOnAction((event) -> {
            containerLaborat.getSelectionModel().select(pageDashboard);
        });
        btnBackMainMenu.setOnAction(((event) -> {
            msc.getTabPane().getSelectionModel().select(msc.getTabMainMenu());
        }));
    }
    
}
