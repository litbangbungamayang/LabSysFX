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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class MainApp extends Application {
    
    private Stage primaryStage;
    private AnchorPane rootLayout;
    public Boolean update = false;
    
    @Override
    public void start(Stage primaryStage) throws FontFormatException, IOException {
        /********* Font Register **********/
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        List<InputStream> lsFonts = new ArrayList<>();
        InputStream fontReg = getClass().getResourceAsStream("/id/buma/labsysfx/assets/fonts/OpenSans-Regular.ttf");
        lsFonts.add(fontReg);
        InputStream fontBold = getClass().getResourceAsStream("/id/buma/labsysfx/assets/fonts/OpenSans-Bold.ttf");
        lsFonts.add(fontBold);
        InputStream fontItalic = getClass().getResourceAsStream("/id/buma/labsysfx/assets/fonts/OpenSans-Italic.ttf");
        lsFonts.add(fontItalic);
        fontReg = getClass().getResourceAsStream("/id/buma/labsysfx/assets/fonts/segoeui.ttf");
        lsFonts.add(fontReg);
        for (InputStream is : lsFonts){
            java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, is);
            if (ge.registerFont(font)) {System.out.println(font.getName() + " OK");} else {System.out.println(font.getName() + " Failed");}
        }
        /************************************************************************************/
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LabSys - PG. Bungamayang");
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        initRootLayout();
    }
    
    public void initRootLayout(){
        try {            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainScreen.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();
            MainScreenController msc = loader.getController();
            msc.setMainApp(this);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public AnchorPane getRootLayout(){
        return rootLayout;
    }
    
    private void callJar(File updaterFile){
        
        try {
            JarFile jarFile = null;
            String mainClass = null;
            try {
                jarFile = new JarFile(updaterFile);
                final Manifest manifest = jarFile.getManifest();
                mainClass = manifest.getMainAttributes().getValue("Main-Class");
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    jarFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            final URLClassLoader child = new URLClassLoader(new URL[]{updaterFile.toURI().toURL()},this.getClass().getClassLoader());
            final Class classToLoad = Class.forName(mainClass, true, child);
            final Method method = classToLoad.getDeclaredMethod("main", String[].class);
            final Object[] arguments = {new String[0]};
            method.invoke(null, arguments);
        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void launchUpdater(){
        try {
            CodeSource cs = getClass().getProtectionDomain().getCodeSource();
            File jarFile = new File(cs.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            File file = new File(jarDir + "/Updater.jar");
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", file.toString());
            Process p = pb.start();
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stop() throws Exception {
        if (update){
            CodeSource cs = getClass().getProtectionDomain().getCodeSource();
            File jarFile = new File(cs.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            File updater = new File(jarDir + "/Updater.jar");
            callJar(updater);
        }
        super.stop();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
