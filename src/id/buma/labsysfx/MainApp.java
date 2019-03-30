package id.buma.labsysfx;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import id.buma.labsysfx.controller.MainScreenController;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class MainApp extends Application {
    
    private Stage primaryStage;
    private AnchorPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LabSys - PG. Bungamayang");
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        initRootLayout();
    }
    
    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainScreen.fxml"));
            
            /********* Font Register **********/
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            InputStream fontReg = getClass().getResourceAsStream("/id/buma/labsysfx/assets/fonts/OpenSans-Regular.ttf");
            InputStream fontBold = getClass().getResourceAsStream("/id/buma/labsysfx/assets/fonts/OpenSans-Bold.ttf");
            InputStream fontItalic = getClass().getResourceAsStream("/id/buma/labsysfx/assets/fonts/OpenSans-Italic.ttf");
            ge.registerFont(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontReg));
            ge.registerFont(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontBold));
            ge.registerFont(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontItalic));
            /************************************************************************************/
            
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();
            MainScreenController msc = loader.getController();
            msc.setMainApp(this);
        } catch (IOException | FontFormatException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AnchorPane getRootLayout(){
        return rootLayout;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
