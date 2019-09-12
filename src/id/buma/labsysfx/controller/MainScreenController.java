/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template toBeDownloadedFile, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import id.buma.labsysfx.MainApp;
import id.buma.labsysfx.dao.AdminPageDAOSQL;
import id.buma.labsysfx.model.UpdateFile;
import id.buma.labsysfx.model.UserLab;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.security.CodeSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


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
    private JFXButton menuCS;
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
    
//<editor-fold defaultstate="collapsed" desc="Deklarasi">
    public UserLab userLab;
    
    public final AdminPageDAOSQL adminPageDao = new AdminPageDAOSQL();
    
    private volatile ErrorMessages alert = new ErrorMessages();
    
    private MainApp mainApp;
    
    public MainScreenController(){
        
    }
    
    private final Dialog<Boolean> popUp = alert.showWaitDialog("Mengecek pembaruan aplikasi.");
    
    private final Dialog<Boolean> popUpNotif = alert.showNotificationDialog("Aplikasi akan menutup untuk mengunduh pembaruan.");
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Methods Library">
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

    private Task progressWatcher(File downloadedFile, long fileSize){
        return new Task() {
            
            Dialog<Boolean> popUp = alert.showProgressDialog("Mengunduh pembaruan, mohon tunggu.", super.progressProperty());
            
            @Override
            protected Object call() throws Exception {
                while (downloadedFile.length() < fileSize){
                    updateProgress(downloadedFile.length(), fileSize);
                }
                return true;
            }

            @Override
            protected void running() {
                super.running();
                popUp.show();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                popUp.setResult(Boolean.TRUE);
            }

            @Override
            protected void failed() {
                super.failed();
                popUp.setResult(Boolean.TRUE);
            }
            
        };
    }
    
    private Task downloadWorker(URL alamat, File updateFile, long fileSize, File downloadedFile, Boolean isCritical){
        
        return new Task() {    
            @Override
            protected Object call(){
                try {
                    FileUtils.copyURLToFile(alamat, updateFile, 10000, 10000);
                } catch (IOException ex) {
                    Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.toString());
                }
                return true;
            }

            @Override
            protected void running() {
                super.running();
                if (isCritical){
                    Task progress = progressWatcher(downloadedFile, fileSize);
                    new Thread(progress).start();
                }
            }
        };
    }
    
    private Task updateCheck(){
        return new Task() {
            @Override
            protected Object call() {
                Platform.runLater(() -> {
                    popUp.show();
                });
                Boolean isNeedUpdate;
                Boolean isInconsistence;
                Boolean isCritical;
                UpdateFile update = adminPageDao.getUpdatePath();
                String thisVersion = getClass().getPackage().getImplementationVersion();
                String pattern = "yyyyMMddHHmm";
                DateTimeFormatter daf = DateTimeFormat.forPattern(pattern);
                String thisDateVersion = thisVersion.substring(thisVersion.length()-12);
                DateTime thisDateTime = daf.parseDateTime(thisDateVersion);
                isNeedUpdate = thisDateTime.isBefore(update.getDateVersion());
                isInconsistence = thisDateTime.isAfter(update.getDateVersion());
                isCritical = update.getUrgency().equals("critical");
                // FOR TESTING
                isNeedUpdate = true;
                
                //============
                if (isNeedUpdate){
                    try {
                        Platform.runLater(() -> {                           
                            popUp.setResult(true);
                            popUp.close();
                        });
                        CodeSource cs = getClass().getProtectionDomain().getCodeSource();
                        File jarFile = new File(cs.getLocation().toURI().getPath());
                        String jarDir = jarFile.getParentFile().getPath();
                        File file = new File(jarDir + "/Installer.jar");
                        ProcessBuilder pb = new ProcessBuilder("java","-jar",file.getAbsolutePath());
                        Platform.exit();
                        Process p = pb.start();
                    } catch (URISyntaxException | IOException ex) {
                        Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return true;
            }
        };
    }
    
    private void cekUpdate(){
        popUp.show();
        Boolean isNeedUpdate;
        Boolean isInconsistence;
        Boolean isCritical;
        UpdateFile update = adminPageDao.getUpdatePath();
        String thisVersion = getClass().getPackage().getImplementationVersion();
        String pattern = "yyyyMMddHHmm";
        DateTimeFormatter daf = DateTimeFormat.forPattern(pattern);
        String thisDateVersion = thisVersion.substring(thisVersion.length()-12);
        DateTime thisDateTime = daf.parseDateTime(thisDateVersion);
        isNeedUpdate = thisDateTime.isBefore(update.getDateVersion());
        isInconsistence = thisDateTime.isAfter(update.getDateVersion());
        isCritical = update.getUrgency().equals("critical");
        // FOR TESTING
        //isNeedUpdate = true;

        //============
        if (isNeedUpdate){
            try {
                popUp.setResult(true);
                popUp.close();
                alert.showWarningAlert("Pembaruan aplikasi tersedia. Aplikasi akan menutup dan mengunduh pembaruan.");
                CodeSource cs = getClass().getProtectionDomain().getCodeSource();
                File jarFile = new File(cs.getLocation().toURI().getPath());
                String jarDir = jarFile.getParentFile().getPath();
                File file = new File(jarDir + "/Installer.jar");
                ProcessBuilder pb = new ProcessBuilder("java","-jar",file.getAbsolutePath());
                Platform.exit();
                Process p = pb.start();
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        popUp.setResult(true);
        popUp.close();
    }
    
    private Boolean cekFileUpdater(){
        try {
            CodeSource cs = getClass().getProtectionDomain().getCodeSource();
            File jarFile = new File(cs.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            File file = new File(jarDir + "/Updater.jar");
            if (Files.exists(file.toPath())){
                return true;
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void setDatePickerFormatting(JFXDatePicker dtp){
        dtp.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern(pattern);
                {
                    dtp.setPromptText(pattern.toLowerCase());
                }
            @Override
            public String toString(LocalDate object) {
                if (object != null){
                    return dtf.format(object);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()){
                    return LocalDate.parse(string, dtf);
                } else {
                    return null;
                }
            }
        });
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        lblVersion.setText(getClass().getPackage().getImplementationVersion());
        
        btnOK.setOnAction(((event) -> {
            if (!txtUsername.getText().isEmpty() && !txtPassword.getText().isEmpty()){
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                userLab = adminPageDao.getLogin(username, password);
                if (userLab != null && cekFileUpdater()){
                    lblUsername.setText(userLab.getNamaUser());
                    hboxUsername.setVisible(true);
                    cekUpdate();
                    tabPane.getSelectionModel().select(tabMainMenu);
                    if (!userLab.getRole().equals("ADM")){
                        vboxAdmin.setVisible(false);
                    } else {
                        vboxAdmin.setVisible(true);
                    }
                } else {
                    if(userLab == null) alert.showErrorAlert("Username atau password tidak cocok!");
                    if(!cekFileUpdater()) alert.showErrorAlert("File Updater.jar tidak ada!");
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
            Platform.exit();
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
        menuCS.setOnAction((event) -> {
            try {
                tabPane.getSelectionModel().select(tabContent);
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/LabCS.fxml"));
                BorderPane CSContent = (BorderPane) loader.load();
                tabContent.setContent(CSContent);
                CSController csc = loader.getController();
                csc.setMainApp(mainApp);
                csc.setMainScreenController(this);
                csc.showDashboard();
            } catch (IOException ex) {
                Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        /******* DAFTAR BINDINGS **************/
    }
}
