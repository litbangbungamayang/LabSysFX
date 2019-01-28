/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import id.buma.labsysfx.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author  Bayu Anandavi Muhardika
 * 
 */

public class AnalisaKemasakanController implements Initializable {

    @FXML
    private JFXButton btnSubMenuDashboard;
    @FXML
    private JFXButton btnSubMenuInputDataAnalisa;
    @FXML
    private JFXButton btnSubMenuLaporanAnalisa;
    @FXML
    private JFXButton btnBackMainMenu;
    @FXML
    private TabPane containerAnkem;
    @FXML
    private Tab pageDashboard;
    @FXML
    private Tab pageInputDataAnalisa;
    @FXML
    private JFXComboBox<Label> cbxJenisAnalisa;
    @FXML
    private JFXTextField txtKodePetak;
    @FXML
    private JFXTextField txtRonde;
    @FXML
    private JFXTextField txtSampel;
    
    private MainApp mainApp;
    
    private MainScreenController msc;
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public AnalisaKemasakanController(){
        
    }
    
    public void setMainScreenController(MainScreenController msc){
        this.msc = msc;
    }
    
    public void showDashboard(){
        containerAnkem.getSelectionModel().select(pageDashboard);
    }
    
    public void validasiField(){
        NumberValidator validatorAngka = new NumberValidator();
        validatorAngka.setMessage("Input tidak valid!");
        RequiredFieldValidator validatorWajib = new RequiredFieldValidator();
        validatorWajib.setMessage("Harus diisi!");
        
        txtRonde.getValidators().add(validatorAngka);
        txtRonde.getValidators().add(validatorWajib);
        txtRonde.focusedProperty().addListener((observable) -> {
            txtRonde.validate();
        });
        
        txtKodePetak.getValidators().add(validatorWajib);
        txtKodePetak.focusedProperty().addListener((observable) -> {
            txtKodePetak.validate();
        });
        
        txtSampel.getValidators().add(validatorAngka);
        txtSampel.setPromptText("1");
        txtSampel.focusedProperty().addListener((observable) -> {
            txtSampel.validate();
        });
    }
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validasiField();
        btnSubMenuDashboard.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageDashboard);
        });
        btnSubMenuInputDataAnalisa.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageInputDataAnalisa);
            cbxJenisAnalisa.getItems().clear();
            cbxJenisAnalisa.getItems().add(new Label("Analisa rutin"));
            cbxJenisAnalisa.getItems().add(new Label("Analisa tebu bakar"));
            cbxJenisAnalisa.getItems().add(new Label("Analisa tebu percobaan"));
            JFXAutoCompletePopup<String> autoComplete = new JFXAutoCompletePopup<>();
            autoComplete.getSuggestions().addAll("Rayon I Petak 056", "Rayon II Petak 129", "SARENGAT D.");
            autoComplete.setSelectionHandler((evt) -> {
                txtKodePetak.setText(evt.getObject());
            });
            txtKodePetak.textProperty().addListener((observable) -> {
                autoComplete.filter(string -> string.toLowerCase().contains(txtKodePetak.getText().toLowerCase()));
                autoComplete.show(txtKodePetak);
            });
        });
        btnBackMainMenu.setOnAction((event) -> {
            msc.getTabPane().getSelectionModel().select(msc.getTabMainMenu());
        });
    }
    
}
