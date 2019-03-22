/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class ErrorMessages {

public ErrorMessages(){
    
}

public void showWarningAlert(String message){
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("PERINGATAN!");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
public void showErrorAlert(String message){
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.setTitle("ERROR!");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

public boolean showConfirmation(String message){
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Konfirmasi");
    alert.setHeaderText(null);
    alert.setContentText(message);
    Optional<ButtonType> pilihan = alert.showAndWait();
    return pilihan.get() == ButtonType.OK;
}

public void showInfoAlert(String message){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Informasi");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}
