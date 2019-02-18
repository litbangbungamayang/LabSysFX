/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import javafx.scene.control.Alert;

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
    alert.setTitle("ERROR!");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}
