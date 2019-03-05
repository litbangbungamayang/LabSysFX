/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXButton;
import id.buma.labsysfx.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class MainScreenController implements Initializable {
    
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnOK;
    @FXML
    private Text lblUsername;
    @FXML
    private Tab tabMainMenu;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabContent;
    @FXML
    private JFXButton menuLaboratPabrik;
    @FXML
    private JFXButton menuAnkem;
    @FXML
    private JFXButton btnAdmin;
    
    
    private MainApp mainApp;
    
    public MainScreenController(){
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public MainApp getMainApp(){
        return mainApp;
    }
    
    public TabPane getTabPane(){
        return tabPane;
    }
    
    public Tab getTabMainMenu(){
        return tabMainMenu;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnOK.setOnAction(((event) -> {
            tabPane.getSelectionModel().select(tabMainMenu);
        }));
        btnAdmin.setOnAction((event) -> {
            try {
                tabPane.getSelectionModel().select(tabContent);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/AdminPage.fxml"));
                BorderPane adminPage = (BorderPane) loader.load();
                tabContent.setContent(adminPage);
                AdminPageController apc = loader.getController();
                apc.setMainScreenController(this);
                apc.showDashboard();
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menuLaboratPabrik.setOnAction((event) -> {
            
            try {
                tabPane.getSelectionModel().select(tabContent);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/LaboratPabrik.fxml"));
                BorderPane laboratPabrik = (BorderPane) loader.load();
                tabContent.setContent(laboratPabrik);
                LaboratPabrikController lpc = loader.getController();
                lpc.setMainApp(mainApp);
                lpc.setMainScreenController(this);
                lpc.showDashboard();
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menuAnkem.setOnAction(((event) -> {
            try {
                tabPane.getSelectionModel().select(tabContent);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/LabKemasakan.fxml"));
                BorderPane ankemContent = (BorderPane) loader.load();
                tabContent.setContent(ankemContent);
                AnalisaKemasakanController akc = loader.getController();
                akc.setMainApp(mainApp);
                akc.setMainScreenController(this);
                akc.showDashboard();
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
    }
}
