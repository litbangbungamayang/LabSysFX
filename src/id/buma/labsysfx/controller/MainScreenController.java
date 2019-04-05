/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXButton;
import id.buma.labsysfx.MainApp;
import id.buma.labsysfx.dao.AdminPageDAOSQL;
import id.buma.labsysfx.model.UserLab;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableObjectValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private Tab tabLogin;
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
    @FXML
    private JFXButton btnLogout;
    @FXML
    private HBox hboxUsername;
    @FXML
    private VBox vboxAdmin;
    
    public UserLab userLab;
    
    public final AdminPageDAOSQL adminPageDao = new AdminPageDAOSQL();
    
    private final ErrorMessages alert = new ErrorMessages();
    
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
            if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()){
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                userLab = adminPageDao.getLogin(username, password);
                if (userLab != null){
                    lblUsername.setText(userLab.getNamaUser());
                    hboxUsername.setVisible(true);
                    tabPane.getSelectionModel().select(tabMainMenu);   
                    if (!userLab.getRole().equals("ADM")){
                        vboxAdmin.setVisible(false);
                    } else {
                        vboxAdmin.setVisible(true);
                    }
                } else {
                    alert.showErrorAlert("Username atau password tidak cocok!");
                }
                
            }
            
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
        btnCancel.setOnAction((event) -> {
            System.exit(0);
        });
        btnLogout.setOnAction((event) -> {
            userLab = null;
            hboxUsername.setVisible(false);
            tabPane.getSelectionModel().select(tabLogin);
            txtUsername.clear();
            txtPassword.clear();
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
        
        
        /******* DAFTAR BINDINGS **************/
    }
}
