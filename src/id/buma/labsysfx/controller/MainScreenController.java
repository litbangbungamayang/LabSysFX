/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXButton;
import id.buma.labsysfx.MainApp;
import id.buma.labsysfx.dao.AdminPageDAOSQL;
import id.buma.labsysfx.model.UpdateFile;
import id.buma.labsysfx.model.UserLab;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.security.CodeSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
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
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class MainScreenController implements Initializable {
    
//<editor-fold defaultstate="collapsed" desc="FXML Library">
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
    @FXML
    private Text lblVersion;
//</editor-fold>
    
    public UserLab userLab;
    
    public final AdminPageDAOSQL adminPageDao = new AdminPageDAOSQL();
    
    private volatile ErrorMessages alert = new ErrorMessages();
    
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
    
    public String getMD5 (File file){
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] byteArray = new byte[1024];
                int bytesCount = 0;
                while ((bytesCount = fis.read(byteArray)) != -1){
                    md.update(byteArray, 0, bytesCount);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            md5 = sb.toString();
        }   catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return md5;
    }
    
    public void testDownloadBinaries(){
        try {
            String url = "https://drive.google.com/open?id=1mIP1RxfvLa16Dtd8fZ508rQYQM2D4kpV";
            String url2 = "https://drive.google.com/uc?export=download&confirm=it_O&id=1mIP1RxfvLa16Dtd8fZ508rQYQM2D4kpV";
            String url3 = "https://github.com/litbangbungamayang/LabSysFX/releases/download/untagged-11de29b93ba014f09915/commons-io-2.6.jar";
            String github = "https://github-production-release-asset-2e65be.s3.amazonaws.com/167989373/b5163f00-5a23-11e9-9b3f-800beb3ad24a?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20190409%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20190409T162120Z&X-Amz-Expires=300&X-Amz-Signature=25801ff18f35535bea9ef056003a9aa8e608ff5635cbe41757b3985b890bb90d&X-Amz-SignedHeaders=host&actor_id=28817280&response-content-disposition=attachment%3B%20filename%3Dcommons-io-2.6.jar&response-content-type=application%2Foctet-stream";
            String url4 = "https://drive.google.com/uc?export=download&confirm=it_O&id=1qLV7vcCGbkL3ohhtSn1eZacBYlCb2ddi";
            String workingDir = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toString()).getParent();
            URL alamat = new URL(url3);
            CodeSource cs = getClass().getProtectionDomain().getCodeSource();
            File jarFile = new File(cs.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            File file = new File(jarDir + "/LabSysFX-update.jar");
            FileUtils.copyURLToFile(alamat, file);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Task<Void> downloadUpdate(UpdateFile update){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                synchronized(this){
                    try {
                        ErrorMessages em = new ErrorMessages();
                        String webBcn = "http://apps.bcn.web.id/commons-io-2.6.jar";
                        URL alamat = new URL(webBcn);
                        CodeSource cs = getClass().getProtectionDomain().getCodeSource();
                        File jarFile = new File(cs.getLocation().toURI().getPath());
                        String jarDir = jarFile.getParentFile().getPath();
                        File file = new File(jarDir + "/LabSysFX-update.jar");
                        File oldFile = new File(jarDir + "/LabSysFX.jar");
                        FileUtils.copyURLToFile(alamat, file, 10000, 10000);
                        System.out.println("Download finished!");
                        System.out.println(getMD5(file));
                        Files.move(oldFile.toPath(), oldFile.toPath().resolveSibling("LabSysFX-oldxxx.jar"));
                        Files.move(file.toPath(), file.toPath().resolveSibling("LabSysFX.jar"));
                        if (update.getUrgency().equals("critical")){
                            System.exit(0);
                        }
                        notify();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException | URISyntaxException ex) {
                        Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Download Failed!");
                    }
                        return null;
                    }
            }
        };
        return task;
    }
    
    private Task<Void> checkForUpdate(){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                UpdateFile update = adminPageDao.getUpdatePath();
                System.out.println(update.getUrgency());
                String thisVersion = getClass().getPackage().getImplementationVersion();
                String pattern = "yyyyMMddHHmm";
                org.joda.time.format.DateTimeFormatter daf = DateTimeFormat.forPattern(pattern);
                String thisDateVersion = thisVersion.substring(thisVersion.length()-12);
                DateTime dt = daf.parseDateTime(thisDateVersion);
                if (thisVersion.equals(update.getVersion())){
                    System.out.println("No need to update!");
                } else {
                    System.out.println("Update required!");
                    System.out.println("This version timestamp : " + dt.toString());
                    System.out.println("Available version timestamp : " + update.getDateVersion().toString());
                    if (dt.isBefore(update.getDateVersion())){
                        Thread download = new Thread(downloadUpdate(update));
                        download.start();
                        synchronized(download){
                           try {
                               System.out.println("Downloading file...");
                               download.wait();
                           } catch (InterruptedException ie){
                               
                           }
                        }
                    }
                }
                return null;
            }
        };
        return task;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        lblVersion.setText(getClass().getPackage().getImplementationVersion());
        
        btnOK.setOnAction(((event) -> {
            if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()){
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                userLab = adminPageDao.getLogin(username, password);
                if (userLab != null){
                    lblUsername.setText(userLab.getNamaUser());
                    hboxUsername.setVisible(true);
                    tabPane.getSelectionModel().select(tabMainMenu);
                    Thread updateChecker = new Thread(checkForUpdate());
                    //updateChecker.start();
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
