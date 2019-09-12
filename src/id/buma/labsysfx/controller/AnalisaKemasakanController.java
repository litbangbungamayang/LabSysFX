/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import id.buma.labsysfx.MainApp;
import id.buma.labsysfx.dao.AnalisaTebuDAOSQL;
import id.buma.labsysfx.dao.PetakKebunDAOSQL;
import id.buma.labsysfx.dao.ReportsPrintingDAOSQL;
import id.buma.labsysfx.dao.VarietasDAOSQL;
import id.buma.labsysfx.model.AnalisaTebu;
import id.buma.labsysfx.model.FisikTebu;
import id.buma.labsysfx.model.HasilAnalisaHarianCS;
import id.buma.labsysfx.model.PetakKebun;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author  Bayu Anandavi Muhardika
 * 
 */

public class AnalisaKemasakanController implements Initializable {

//<editor-fold defaultstate="collapsed" desc="FXML definitions">
    @FXML
    private JFXButton btnSubMenuDashboard;
    @FXML
    private JFXButton btnSubMenuInputDataAnalisa;
    @FXML
    private JFXButton btnSubMenuLaporanAnalisa;
    @FXML
    private JFXButton btnBackMainMenu;
    @FXML
    private Button btnTesJson;
    @FXML
    private TabPane containerAnkem;
    @FXML
    private Tab pageDashboard;
    @FXML
    private Tab pageInputDataAnalisa;
    @FXML
    private Tab pageInputDataAnalisa2;
    @FXML
    private Tab pageLaporan;
    @FXML
    private JFXTextField txtPetak;
    @FXML
    private JFXTextField txtRonde;
    @FXML
    private JFXTextField txtJenisAnalisa;
    @FXML
    private JFXButton btnNextAnalisa;
    @FXML
    private JFXButton btnViewData;
    @FXML
    private JFXButton btnPreviewPetak;
    @FXML
    private JFXTextField txtBobotTebuAtas;
    @FXML
    private JFXTextField txtBobotNiraAtas;
    @FXML
    private JFXTextField txtBobotTebuTengah;
    @FXML
    private JFXTextField txtBobotNiraTengah;
    @FXML
    private JFXTextField txtBobotTebuBawah;
    @FXML
    private JFXTextField txtBobotNiraBawah;
    @FXML
    private JFXTextField txtPenggerek;
    @FXML
    private JFXTextField txtBrixAtas;
    @FXML
    private JFXTextField txtBrixTengah;
    @FXML
    private JFXTextField txtBrixBawah;
    @FXML
    private JFXTextField txtBrixCampur;
    @FXML
    private JFXTextField txtPolAtas;
    @FXML
    private JFXTextField txtPolTengah;
    @FXML
    private JFXTextField txtPolBawah;
    @FXML
    private JFXTextField txtPolCampur;
    @FXML
    private JFXTextField txtSuhu;
    @FXML
    private JFXTextField txtKoreksiSuhu;
    @FXML
    private JFXTextField txtInputPanjang;
    @FXML
    private JFXTextField txtInputRuas;
    @FXML
    private JFXTextField txtInputDiameter;
    @FXML
    private TitledPane titPaneInputData;
    @FXML
    private TitledPane titPaneHasilAnalisa;
    @FXML
    private JFXButton btnHitung;
    @FXML
    private Button btnTambahFisik;
    @FXML
    private Button btnHapusFisik;
    @FXML
    private TableColumn tcNomor;
    @FXML
    private TableColumn<FisikTebu, Double> tcPanjang;
    @FXML
    private TableColumn<FisikTebu, Integer> tcRuas;
    @FXML
    private TableColumn<FisikTebu, Double> tcDiameter;
    @FXML
    private TableView<FisikTebu> tvFisik;
    @FXML
    private TableView<AnalisaTebu> tvHasil;
    @FXML
    private TableColumn tcNoSampel;
    @FXML
    private TableColumn<AnalisaTebu, String> tcKodePetak;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcBxA;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcBxT;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcBxB;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcBxC;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcPolA;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcPolT;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcPolB;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcPolC;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcRendA;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcRendT;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcRendB;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcRendC;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcFK;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcKP;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcKDT;
    @FXML
    private TableColumn<AnalisaTebu, Double> tcFaktor;
    @FXML
    private JFXDatePicker dtpTglAnalisa;
    @FXML
    private JFXDatePicker dtpTglLaporanAwal;
    @FXML
    private JFXDatePicker dtpTglLaporanAkhir;
    @FXML
    private JFXButton btnTambahSampel;
    @FXML
    private Text txtNoSampel;
    @FXML
    private Text txtKodePetak;
    @FXML
    private Text txtLuasPetak;
    @FXML
    private Text txtVarietas;
    @FXML
    private Text txtLabelRonde;
    @FXML
    private JFXTextField txtLaporanTstr;
    @FXML
    private JFXButton btnBatal;
    @FXML
    private JFXButton btnSimpan;
    @FXML
    private JFXButton btnHapusAnalisa;
    @FXML
    private JFXButton btnPreview;
    @FXML
    private CheckBox chkLaporanSd;
    @FXML
    private AnchorPane anchorPRonde;
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Deklarasi">
    final ObservableList<FisikTebu> dataFisikTemp = FXCollections.observableArrayList();
    
    ObservableList<FisikTebu> dataFisik = FXCollections.observableArrayList();
    
    final ObservableList<ObservableList<FisikTebu>> listDataFisik = FXCollections.observableArrayList();
    
    final ObservableList<AnalisaTebu> dataAnalisa = FXCollections.observableArrayList();
    
    ObservableList<PetakKebun> listPetakKebun = FXCollections.observableArrayList();
    
    List<String> listPopUpPetak = new ArrayList<>();
    
    private PetakKebun petakKebun;
    
    private int jenisAnalisa = -1;
    
    private int no_sampel = 1;
    
    private MainApp mainApp;
    
    private MainScreenController msc;
    
    private final PetakKebunDAOSQL petakKebunDao = new PetakKebunDAOSQL();
    
    private final VarietasDAOSQL varietasDao = new VarietasDAOSQL();
    
    private final AnalisaTebuDAOSQL analisaDao = new AnalisaTebuDAOSQL();
    
    private final ReportsPrintingDAOSQL reportsDao = new ReportsPrintingDAOSQL();
    
    private final ErrorMessages alert = new ErrorMessages();
    
    public String inputStatus;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Methods Library">   
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public AnalisaKemasakanController(){
        petakKebun = null;
    }
    
    public void setMainScreenController(MainScreenController msc){
        this.msc = msc;
    }
    
    public void showDashboard(){
        containerAnkem.getSelectionModel().select(pageDashboard);
        datePickerDisplay();
        autoCompleteGroup();
        validatorField();
    }
    
    public Double koreksiDesimal(Double angka){
        Double hasil;
        if (angka > 10){
            hasil = angka/100;
        } else {
            return angka;
        }
        return hasil;
    }
    
    public Double koreksiAngkaAnalisa(Double angka){
        Double hasil;
        if (angka > 100){
            hasil = angka/10;
        } else {
            return angka;
        }
        return hasil;
    }
    
    public Double koreksiAngkaDiameter(Double angka){
        Double hasil;
        if (angka > 10){
            hasil = angka/10;
        } else {
            return angka;
        }
        return hasil;
    }
    
    public void validatorField(){
        NumberValidator validatorAngka = new NumberValidator();
        validatorAngka.setMessage("Input tidak valid!");
        RequiredFieldValidator validatorWajib = new RequiredFieldValidator();
        validatorWajib.setMessage("Harus diisi!");
        DoubleValidator validatorDesimal = new DoubleValidator();
        validatorDesimal.setMessage("Input tidak valid! Harus angka desimal!");
        IntegerValidator validatorInteger = new IntegerValidator();
        validatorInteger.setMessage("Input tidak valid! Harus bilangan bulat!");
        
        txtJenisAnalisa.getValidators().add(validatorWajib);
        txtJenisAnalisa.focusedProperty().addListener((observable) -> {
            txtJenisAnalisa.validate();
        });
        
        txtRonde.getValidators().add(validatorAngka);
        txtRonde.getValidators().add(validatorWajib);
        txtRonde.focusedProperty().addListener((observable) -> {
            txtRonde.validate();
        });
        
        txtPetak.getValidators().add(validatorWajib);
        txtPetak.focusedProperty().addListener((observable) -> {
            txtPetak.validate();
        });
        
        txtBobotTebuAtas.getValidators().add(validatorWajib);
        txtBobotTebuAtas.getValidators().add(validatorDesimal);
        txtBobotTebuAtas.focusedProperty().addListener((observable) -> {
            if (!txtBobotTebuAtas.getText().isEmpty()){
                if (txtBobotTebuAtas.validate()){
                    Double angka = Double.parseDouble(txtBobotTebuAtas.getText());
                    txtBobotTebuAtas.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        txtBobotNiraAtas.getValidators().add(validatorWajib);
        txtBobotNiraAtas.getValidators().add(validatorDesimal);
        txtBobotNiraAtas.focusedProperty().addListener((observable) -> {
            if (!txtBobotNiraAtas.getText().isEmpty()){
                if (txtBobotNiraAtas.validate()){
                    Double angka = Double.parseDouble(txtBobotNiraAtas.getText());
                    txtBobotNiraAtas.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        txtBobotTebuTengah.getValidators().add(validatorWajib);
        txtBobotTebuTengah.getValidators().add(validatorDesimal);
        txtBobotTebuTengah.focusedProperty().addListener((observable) -> {
            if (!txtBobotTebuTengah.getText().isEmpty()){
                if (txtBobotTebuTengah.validate()){
                    Double angka = Double.parseDouble(txtBobotTebuTengah.getText());
                    txtBobotTebuTengah.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        txtBobotNiraTengah.getValidators().add(validatorWajib);
        txtBobotNiraTengah.getValidators().add(validatorDesimal);
        txtBobotNiraTengah.focusedProperty().addListener((observable) -> {
            if (!txtBobotNiraTengah.getText().isEmpty()){
                if (txtBobotNiraTengah.validate()){
                    Double angka = Double.parseDouble(txtBobotNiraTengah.getText());
                    txtBobotNiraTengah.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        txtBobotTebuBawah.getValidators().add(validatorWajib);
        txtBobotTebuBawah.getValidators().add(validatorDesimal);
        txtBobotTebuBawah.focusedProperty().addListener((observable) -> {
            if (!txtBobotTebuBawah.getText().isEmpty()){
                if (txtBobotTebuBawah.validate()){
                    Double angka = Double.parseDouble(txtBobotTebuBawah.getText());
                    txtBobotTebuBawah.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        txtBobotNiraBawah.getValidators().add(validatorWajib);
        txtBobotNiraBawah.getValidators().add(validatorDesimal);
        txtBobotNiraBawah.focusedProperty().addListener((observable) -> {
            if (!txtBobotNiraBawah.getText().isEmpty()){
                if (txtBobotNiraBawah.validate()){
                    Double angka = Double.parseDouble(txtBobotNiraBawah.getText());
                    txtBobotNiraBawah.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        txtPenggerek.getValidators().add(validatorWajib);
        txtPenggerek.getValidators().add(validatorDesimal);
        txtPenggerek.focusedProperty().addListener((observable) -> {
            txtPenggerek.validate();
        });
        
        txtBrixAtas.getValidators().add(validatorWajib);
        txtBrixAtas.getValidators().add(validatorDesimal);
        txtBrixAtas.focusedProperty().addListener((observable) -> {
            if (!txtBrixAtas.getText().isEmpty()){
                if (txtBrixAtas.validate()){
                    Double angka = Double.parseDouble(txtBrixAtas.getText());
                    txtBrixAtas.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtBrixTengah.getValidators().add(validatorWajib);
        txtBrixTengah.getValidators().add(validatorDesimal);
        txtBrixTengah.focusedProperty().addListener((observable) -> {
            if (!txtBrixTengah.getText().isEmpty()){
                if (txtBrixTengah.validate()){
                    Double angka = Double.parseDouble(txtBrixTengah.getText());
                    txtBrixTengah.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtBrixBawah.getValidators().add(validatorWajib);
        txtBrixBawah.getValidators().add(validatorDesimal);
        txtBrixBawah.focusedProperty().addListener((observable) -> {
            if (!txtBrixBawah.getText().isEmpty()){
                if (txtBrixBawah.validate()){
                    Double angka = Double.parseDouble(txtBrixBawah.getText());
                    txtBrixBawah.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtBrixCampur.getValidators().add(validatorWajib);
        txtBrixCampur.getValidators().add(validatorDesimal);
        txtBrixCampur.focusedProperty().addListener((observable) -> {
            if (!txtBrixCampur.getText().isEmpty()){
                if (txtBrixCampur.validate()){
                    Double angka = Double.parseDouble(txtBrixCampur.getText());
                    txtBrixCampur.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtPolAtas.getValidators().add(validatorWajib);
        txtPolAtas.getValidators().add(validatorDesimal);
        txtPolAtas.focusedProperty().addListener((observable) -> {
            if (!txtPolAtas.getText().isEmpty()){
                if (txtPolAtas.validate()){
                    Double angka = Double.parseDouble(txtPolAtas.getText());
                    txtPolAtas.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtPolTengah.getValidators().add(validatorWajib);
        txtPolTengah.getValidators().add(validatorDesimal);
        txtPolTengah.focusedProperty().addListener((observable) -> {
            if (!txtPolTengah.getText().isEmpty()){
                if (txtPolTengah.validate()){
                    Double angka = Double.parseDouble(txtPolTengah.getText());
                    txtPolTengah.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtPolBawah.getValidators().add(validatorWajib);
        txtPolBawah.getValidators().add(validatorDesimal);
        txtPolBawah.focusedProperty().addListener((observable) -> {
            if (!txtPolBawah.getText().isEmpty()){
                if (txtPolBawah.validate()){
                    Double angka = Double.parseDouble(txtPolBawah.getText());
                    txtPolBawah.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtPolCampur.getValidators().add(validatorWajib);
        txtPolCampur.getValidators().add(validatorDesimal);
        txtPolCampur.focusedProperty().addListener((observable) -> {
            if (!txtPolCampur.getText().isEmpty()){
                if (txtPolCampur.validate()){
                    Double angka = Double.parseDouble(txtPolCampur.getText());
                    txtPolCampur.setText(String.valueOf(koreksiAngkaAnalisa(angka)));
                }
            }
        });
        
        txtSuhu.getValidators().add(validatorWajib);
        txtSuhu.getValidators().add(validatorDesimal);
        txtSuhu.focusedProperty().addListener((observable) -> {
            txtSuhu.validate();
        });
        
        txtKoreksiSuhu.getValidators().add(validatorWajib);
        txtKoreksiSuhu.getValidators().add(validatorDesimal);
        txtKoreksiSuhu.focusedProperty().addListener((observable) -> {
            txtKoreksiSuhu.validate();
        });
        
        txtInputPanjang.getValidators().add(validatorDesimal);
        txtInputPanjang.focusedProperty().addListener((observable) -> {
            if (!txtInputPanjang.getText().isEmpty()){
                if (txtInputPanjang.validate()){
                    Double angka = Double.parseDouble(txtInputPanjang.getText());
                    txtInputPanjang.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        txtInputRuas.getValidators().add(validatorInteger);
        txtInputRuas.focusedProperty().addListener((observable) -> {
            txtInputRuas.validate();
        });
        
        txtInputDiameter.getValidators().add(validatorDesimal);
        txtInputDiameter.focusedProperty().addListener((observable) -> {
            if (!txtInputDiameter.getText().isEmpty()){
                if (txtInputDiameter.validate()){
                    Double angka = Double.parseDouble(txtInputDiameter.getText());
                    txtInputDiameter.setText(String.valueOf(koreksiAngkaDiameter(angka)));
                }
            }
        });
        
        dtpTglAnalisa.getValidators().add(validatorWajib);
        dtpTglAnalisa.focusedProperty().addListener((observable) -> {
            dtpTglAnalisa.validate();
        });
        
        dtpTglLaporanAwal.getValidators().add(validatorWajib);
        dtpTglLaporanAwal.focusedProperty().addListener((observable) -> {
            dtpTglLaporanAwal.validate();
        });
        
        dtpTglLaporanAkhir.getValidators().add(validatorWajib);
        dtpTglLaporanAkhir.focusedProperty().addListener((observable) -> {
            dtpTglLaporanAkhir.validate();
        });
        
        txtLaporanTstr.getValidators().add(validatorWajib);
        txtLaporanTstr.focusedProperty().addListener((observable) -> {
            txtLaporanTstr.validate();
        });
        
    }
    
    public void autoCompleteGroup(){
        listPetakKebun = petakKebunDao.getAllPetak();
        for (PetakKebun listPetakKebunPetak : listPetakKebun) {
            listPopUpPetak.add(
                    listPetakKebunPetak.getRayon() + " " +
                    listPetakKebunPetak.getNamaKebun() + " " + 
                    listPetakKebunPetak.getNoPetak() + " " + String.valueOf(listPetakKebunPetak.getLuasPetak() + 
                            " Ha " + listPetakKebunPetak.getMasaTanam() + " " +
                    listPetakKebunPetak.getKategori()
                            ));
        }
        JFXAutoCompletePopup<String> autoCompleteJenis = new JFXAutoCompletePopup<>();
        JFXAutoCompletePopup<String> autoCompletePetak = new JFXAutoCompletePopup<>();
        JFXAutoCompletePopup<String> autoCompleteTstr = new JFXAutoCompletePopup<>();
        autoCompleteJenis.getSuggestions().addAll("Analisa Rutin", "Analisa Tebu Bakar", "Analisa Tebu Percobaan");
        autoCompletePetak.getSuggestions().addAll(listPopUpPetak);
        autoCompleteTstr.getSuggestions().addAll("TS", "TR", "TSI");
        autoCompleteJenis.cellLimitProperty().setValue(3);
        autoCompletePetak.cellLimitProperty().setValue(10);
        /* Handler untuk jenis analisa */
        txtJenisAnalisa.textProperty().addListener((observable) -> {
            autoCompleteJenis.filter(string -> string.toLowerCase().contains(txtJenisAnalisa.getText().toLowerCase()));
            autoCompleteJenis.show(txtJenisAnalisa);
        });
        txtJenisAnalisa.focusedProperty().addListener((observable) -> {
            if (txtJenisAnalisa.isFocused()) autoCompleteJenis.show(txtJenisAnalisa);
        });
        autoCompleteJenis.setSelectionHandler((evt) -> {
            txtJenisAnalisa.setText(evt.getObject());
            jenisAnalisa = autoCompleteJenis.getSuggestions().indexOf(txtJenisAnalisa.getText()) + 1;
        });
        
        /********* Handler untuk TSTR laporan *********/
        txtLaporanTstr.textProperty().addListener((observable) -> {
            autoCompleteJenis.filter(string -> string.toLowerCase().contains(txtLaporanTstr.getText().toLowerCase()));
            autoCompleteTstr.show(txtLaporanTstr);
        });
        txtLaporanTstr.focusedProperty().addListener((observable) -> {
            if (txtLaporanTstr.isFocused()) autoCompleteTstr.show(txtLaporanTstr);
        });
        autoCompleteTstr.setSelectionHandler((event) -> {
            txtLaporanTstr.setText(event.getObject());
        });
        
        /********* Handler untuk petak kebun **********/
        txtPetak.textProperty().addListener((observable) -> {
            if (!txtPetak.getText().isEmpty()){
                autoCompletePetak.filter(string -> string.toLowerCase().contains(txtPetak.getText().toLowerCase()));
                autoCompletePetak.show(txtPetak);
            } else {
                autoCompletePetak.hide();
            }
        });
        autoCompletePetak.setSelectionHandler((evt) -> {
            txtPetak.setText(evt.getObject());
            int indexnya = autoCompletePetak.getSuggestions().indexOf(txtPetak.getText());
            petakKebun = listPetakKebun.get(indexnya);
            txtRonde.setText(String.valueOf(analisaDao.getRonde(petakKebun.getKodePetak())));
            autoCompletePetak.hide();
        });
        
    }
    
    public void resetDataAwal(){
        txtJenisAnalisa.clear();
        txtPetak.clear();
        txtRonde.clear();
        txtPetak.getValidators().clear();
        txtRonde.getValidators().clear();
        petakKebun = null;
        jenisAnalisa = -1;
        listPopUpPetak.clear();
        dataAnalisa.clear();
        listDataFisik.clear();
        dataFisik.clear();
        no_sampel = 1;
        txtNoSampel.setText(String.valueOf(no_sampel));
    }
    
    public void resetField(){
        
        txtBobotNiraAtas.clear();
        txtBobotNiraTengah.clear();
        txtBobotNiraBawah.clear();
        txtBobotTebuAtas.clear();
        txtBobotTebuTengah.clear();
        txtBobotTebuBawah.clear();
        txtPenggerek.clear();
        txtBrixAtas.clear();
        txtBrixTengah.clear();
        txtBrixBawah.clear();
        txtBrixCampur.clear();
        txtPolAtas.clear();
        txtPolTengah.clear();
        txtPolBawah.clear();
        txtPolCampur.clear();
        txtSuhu.clear();
        txtKoreksiSuhu.clear();
        dtpTglAnalisa.setValue(null);
        dataFisikTemp.clear();
        refreshTabelFisik();
        
        txtBobotNiraAtas.getValidators().clear();
        txtBobotNiraTengah.getValidators().clear();
        txtBobotNiraBawah.getValidators().clear();
        txtBobotTebuAtas.getValidators().clear();
        txtBobotTebuTengah.getValidators().clear();
        txtBobotTebuBawah.getValidators().clear();
        txtPenggerek.getValidators().clear();
        txtBrixAtas.getValidators().clear();
        txtBrixTengah.getValidators().clear();
        txtBrixBawah.getValidators().clear();
        txtBrixCampur.getValidators().clear();
        txtPolAtas.getValidators().clear();
        txtPolTengah.getValidators().clear();
        txtPolBawah.getValidators().clear();
        txtPolCampur.getValidators().clear();
        txtSuhu.getValidators().clear();
        txtKoreksiSuhu.getValidators().clear();
        dtpTglAnalisa.getValidators().clear();
        txtInputPanjang.getValidators().clear();
        txtInputRuas.getValidators().clear();
        txtInputDiameter.getValidators().clear();
        
        containerAnkem.requestFocus();
    }
    
    public void resetFieldLaporan(){
        dtpTglLaporanAwal.setValue(null);
        dtpTglLaporanAkhir.setValue(null);
        txtLaporanTstr.clear();
        chkLaporanSd.setSelected(false);
        
        dtpTglLaporanAwal.getValidators().clear();
        dtpTglLaporanAkhir.getValidators().clear();
        txtLaporanTstr.getValidators().clear();
    }
        
    public void refreshTabelFisik(){
        tvFisik.setItems(dataFisikTemp);
        tcNomor.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn p) {
                return new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getIndex() + 1 + " ");
                    }
                };
            }
        });
        tcPanjang.setCellValueFactory(new PropertyValueFactory<>("panjang"));
        tcRuas.setCellValueFactory(new PropertyValueFactory<>("ruas"));
        tcDiameter.setCellValueFactory(new PropertyValueFactory<>("diameter"));
    }
    
    public void refreshTabelHasil(){
        tvHasil.setItems(dataAnalisa);
        tcNoSampel.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getIndex() + 1 + " ");
                    }
                };
            }
        });
        tcKodePetak.setCellValueFactory(new PropertyValueFactory<>("kodePetak"));
        tcBxA.setCellValueFactory(new PropertyValueFactory<>("brixAtas"));
        tcBxB.setCellValueFactory(new PropertyValueFactory<>("brixBawah"));
        tcBxT.setCellValueFactory(new PropertyValueFactory<>("brixTengah"));
        tcBxC.setCellValueFactory(new PropertyValueFactory<>("brixCampur"));
        tcPolA.setCellValueFactory(new PropertyValueFactory<>("polAtas"));
        tcPolT.setCellValueFactory(new PropertyValueFactory<>("polTengah"));
        tcPolB.setCellValueFactory(new PropertyValueFactory<>("polBawah"));
        tcPolC.setCellValueFactory(new PropertyValueFactory<>("polCampur"));
        tcFK.setCellValueFactory(new PropertyValueFactory<>("fk"));
        tcKDT.setCellValueFactory(new PropertyValueFactory<>("kdt"));
        tcKP.setCellValueFactory(new PropertyValueFactory<>("kp"));
        tcRendA.setCellValueFactory(new PropertyValueFactory<>("rendAtas"));
        tcRendT.setCellValueFactory(new PropertyValueFactory<>("rendTengah"));
        tcRendB.setCellValueFactory(new PropertyValueFactory<>("rendBawah"));
        tcRendC.setCellValueFactory(new PropertyValueFactory<>("rendCampur"));
        tcFaktor.setCellValueFactory(new PropertyValueFactory<>("faktorPerah"));
    }
    
    public FisikTebu hitungRataRata(){
        Double rataPanjang = 0.00;
        int rataRuas = 0;
        Double rataDiameter = 0.00;
        Double jmlPanjang = 0.00;
        int jmlData;
        if (!dataFisikTemp.isEmpty()){
            jmlData = dataFisikTemp.size();
            for (FisikTebu fisikBatang : dataFisikTemp) {
                rataPanjang = rataPanjang + fisikBatang.getPanjang();
                rataRuas = rataRuas + fisikBatang.getRuas();
                rataDiameter = rataDiameter + fisikBatang.getDiameter();
            }
            jmlPanjang = duaDesimalDouble(rataPanjang);
            rataPanjang = duaDesimalDouble(rataPanjang/jmlData);
            rataDiameter = duaDesimalDouble(rataDiameter/jmlData);
            rataRuas = Math.round(rataRuas/jmlData);
        }
        FisikTebu rataFisik = new FisikTebu(rataPanjang, rataRuas, rataDiameter, jmlPanjang);
        return rataFisik;
    }
    
    public Double hitungBJ(Double brixBaca){
        Double hasil = 0.00;
        if (brixBaca <= 5.5){
            hasil = ((0.0215*brixBaca) + 5.4802)/5.5;
        } else {
            if (brixBaca <= 10.5){
                hasil = ((0.01983*brixBaca) + 4.878622)/4.9;
            } else {
                if (brixBaca <= 15.5){
                    hasil = ((0.02052*brixBaca) + 4.871334)/4.9;
                } else {
                    if (brixBaca > 15.5){
                        hasil = ((0.02207*brixBaca) + 4.843238)/4.9;
                    }
                }
            }
        }
        return hasil;
    }
    
    public Double hitungPol(Double polBaca, Double bj){
        Double hasil;
        hasil = (polBaca * 0.286)/bj;
        return hasil;
    }
    
    public Double hitungNn(Double pol, Double brix){
        Double hasil;
        hasil = pol - 0.4 * (brix - pol);
        return hasil;
    }
    
    public Double duaDesimal(String angka){
        Double hasil;
        DecimalFormat df = new DecimalFormat("#0.00");
        hasil = Double.valueOf(df.format(Double.parseDouble(angka)));
        return hasil;
    }
    
    public Double satuDesimal(String angka){
        Double hasil;
        DecimalFormat df = new DecimalFormat("#0.0");
        hasil = Double.valueOf(df.format(Double.parseDouble(angka)));
        return hasil;
    }
    
    public Double satuDesimalDouble(Double angka){
        Double hasil;
        DecimalFormat df = new DecimalFormat("#0.0");
        hasil = Double.valueOf(df.format(angka));
        return hasil;
    }
    
    public Double duaDesimalDouble(Double angka){
        Double hasil;
        DecimalFormat df = new DecimalFormat("#0.00");
        hasil = Double.valueOf(df.format(angka));
        return hasil;
    }
    
    public void validasiInput(){
        if (txtBobotTebuAtas.validate() && txtBobotTebuTengah.validate() && txtBobotTebuBawah.validate() &&
                txtBobotNiraAtas.validate() && txtBobotNiraTengah.validate() && txtBobotNiraBawah.validate() &&
                txtBrixAtas.validate() && txtBrixTengah.validate() && txtBrixBawah.validate() && txtBrixCampur.validate() &&
                txtPolAtas.validate() && txtPolTengah.validate() && txtPolBawah.validate() && txtPolCampur.validate() &&
                txtSuhu.validate() && txtKoreksiSuhu.validate() && dtpTglAnalisa.getValue() != null &&
                dataFisikTemp.size() > 0){

            Double beratTebuAtas = duaDesimal(txtBobotTebuAtas.getText());
            Double beratTebuTengah = duaDesimal(txtBobotTebuTengah.getText());
            Double beratTebuBawah = duaDesimal(txtBobotTebuBawah.getText());
            Double beratNiraAtas = duaDesimal(txtBobotNiraAtas.getText());
            Double beratNiraTengah = duaDesimal(txtBobotNiraTengah.getText());
            Double beratNiraBawah = duaDesimal(txtBobotNiraBawah.getText());
            Double brixBacaAtas = satuDesimal(txtBrixAtas.getText());
            Double brixBacaTengah = satuDesimal(txtBrixTengah.getText());
            Double brixBacaBawah = satuDesimal(txtBrixBawah.getText());
            Double brixBacaCampur = satuDesimal(txtBrixCampur.getText());
            Double suhu = satuDesimal(txtSuhu.getText());
            Double koreksiSuhu = Double.parseDouble(txtKoreksiSuhu.getText());
            Double brixAtas = duaDesimalDouble(brixBacaAtas + koreksiSuhu);
            Double brixTengah = duaDesimalDouble(brixBacaTengah + koreksiSuhu);
            Double brixBawah = duaDesimalDouble(brixBacaBawah + koreksiSuhu);
            Double brixCampur = duaDesimalDouble(brixBacaCampur + koreksiSuhu);
            Double polBacaAtas = satuDesimal(txtPolAtas.getText());
            Double polBacaTengah = satuDesimal(txtPolTengah.getText());
            Double polBacaBawah = satuDesimal(txtPolBawah.getText());
            Double polBacaCampur = satuDesimal(txtPolCampur.getText());
            Double faktorPerah = duaDesimalDouble((beratNiraAtas + beratNiraTengah + beratNiraBawah) / (beratTebuAtas + beratTebuTengah + beratTebuBawah));
            Double polAtas = duaDesimalDouble(hitungPol(polBacaAtas, hitungBJ(brixAtas)));
            Double polTengah = duaDesimalDouble(hitungPol(polBacaTengah, hitungBJ(brixTengah)));
            Double polBawah = duaDesimalDouble(hitungPol(polBacaBawah, hitungBJ(brixBawah)));
            Double polCampur = duaDesimalDouble(hitungPol(polBacaCampur, hitungBJ(brixCampur)));
            Double nnAtas = duaDesimalDouble(hitungNn(polAtas, brixAtas));
            Double nnTengah = duaDesimalDouble(hitungNn(polTengah, brixTengah));
            Double nnBawah = duaDesimalDouble(hitungNn(polBawah, brixBawah));
            Double nnCampur = duaDesimalDouble(hitungNn(polCampur, brixCampur));
            Double rendAtas = duaDesimalDouble(nnAtas * faktorPerah);
            Double rendTengah = duaDesimalDouble(nnTengah * faktorPerah);
            Double rendBawah = duaDesimalDouble(nnBawah * faktorPerah);
            Double rendCampur = duaDesimalDouble(nnCampur * faktorPerah);
            Double fk = duaDesimalDouble((rendBawah - rendAtas) / rendBawah * 100);
            java.sql.Date tglAnalisa = java.sql.Date.valueOf(dtpTglAnalisa.getValue());
            java.sql.Timestamp tglPosting = new java.sql.Timestamp(System.currentTimeMillis());
            FisikTebu rataFisik = hitungRataRata();
            Double kp = 0.00;
            Double kdt = 0.00;
            if (Integer.valueOf(txtLabelRonde.getText()) > 2){
                System.out.println(Integer.valueOf(txtLabelRonde.getText()));
                Double rendLalu = analisaDao.getKp(petakKebun.getKodePetak(), Integer.valueOf(txtLabelRonde.getText()));
                Double hkBawahLalu = analisaDao.getKdt(petakKebun.getKodePetak(), Integer.valueOf(txtLabelRonde.getText()));
                if (rendLalu > 0.00) kp = duaDesimalDouble((rendCampur/rendLalu))*100;
                if (hkBawahLalu > 0.00) kdt = duaDesimalDouble((satuDesimalDouble((polBawah/brixBawah)*100)/
                        hkBawahLalu))*100;
            }
            Double kgPerMeter = duaDesimalDouble((beratTebuAtas + beratTebuTengah + beratTebuBawah)/rataFisik.getJmlPanjang());
            /*
            ******
            */
            AnalisaTebu at = new AnalisaTebu(petakKebun.getKodePetak(),
                    msc.userLab.getIdUser(), //idUser
                    jenisAnalisa, //jenisAnalisa
                    Integer.valueOf(txtLabelRonde.getText()), //ronde
                    Integer.valueOf(txtNoSampel.getText()), //noSampel
                    tglAnalisa, //tglAnalisa
                    tglPosting, //tglPosting
                    beratTebuAtas, //beratTebuAtas
                    beratTebuTengah, //beratTebuTengah
                    beratTebuBawah, //beratTebuBawah
                    beratNiraAtas, //beratNiraAtas
                    beratNiraTengah, //beratNiraTengah
                    beratNiraBawah, //beratNiraBawah
                    Double.parseDouble(txtPenggerek.getText()), //penggerek
                    brixBacaAtas, //brixBacaAtas
                    brixBacaTengah, //brixBacaTengah
                    brixBacaBawah, //brixBacaBawah
                    brixBacaCampur, //brixBacaCampur
                    brixAtas, //brixAtas
                    brixTengah, //brixTengah
                    brixBawah, //brix Bawah
                    brixCampur, //brix Campur
                    polBacaAtas, //polBacaAtas
                    polBacaTengah, //polBacaTengah
                    polBacaBawah, //polBacaBawah
                    polBacaCampur, //polBacaCampur
                    polAtas, //pol Atas
                    polTengah, //pol Tengah
                    polBawah, //pol bawah
                    polCampur, //pol campur
                    faktorPerah, //faktor perah
                    satuDesimalDouble((polAtas/brixAtas)*100), //hkAtas
                    satuDesimalDouble((polTengah/brixTengah)*100), //hkTengah
                    satuDesimalDouble((polBawah/brixBawah)*100), //hkBawah
                    satuDesimalDouble((polCampur/brixCampur)*100), //hkCampur
                    nnAtas, //NN Atas
                    nnTengah, //NN Tengah
                    nnBawah, //NN Bawah
                    nnCampur, //NN Campur
                    rendAtas, //rend atas
                    rendTengah, //rend tengah
                    rendBawah, //rend bawah
                    rendCampur, //rend campur
                    suhu, //suhu
                    koreksiSuhu, //koreksi suhu
                    fk, // FK
                    kp, // KP
                    kdt, // KDT
                    rataFisik.getPanjang(), //rata2 panjang
                    rataFisik.getRuas(), //rata2 ruas
                    rataFisik.getDiameter(), //rata2 diameter
                    kgPerMeter // kg/meter
            );
            dataFisik = FXCollections.observableArrayList(dataFisikTemp);
            listDataFisik.add(dataFisik);
            dataAnalisa.add(at);
            refreshTabelHasil();
        } else {
            if (dataFisikTemp.isEmpty()){
                alert.showErrorAlert("Data pengukuran tebu harus ada!");
            } else {
                alert.showErrorAlert("Cek kembali input data!");
            }
        }
    }
    
    public void datePickerDisplay(){
        dtpTglAnalisa.setConverter(new StringConverter<LocalDate>() {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        {
            dtpTglAnalisa.setPromptText(pattern.toLowerCase());
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
        dtpTglLaporanAwal.setConverter(dtpTglAnalisa.getConverter());
        dtpTglLaporanAkhir.setConverter(dtpTglAnalisa.getConverter());
    }
    
    public void loadDataPetak(){
        txtKodePetak.setText(petakKebun.getKodePetak() + " " +
                petakKebun.getRayon() + " " +
                petakKebun.getNamaKebun() + " " + petakKebun.getNoPetak()
                + " " + petakKebun.getMasaTanam() + " " +
                petakKebun.getKategori()
        );
        txtLuasPetak.setText(String.valueOf(petakKebun.getLuasPetak()) + " Ha");
        txtVarietas.setText(varietasDao.getNamaVarietas(petakKebun.getVarietas()));
    }
    
    public void validasiDataAwal(){
        if (petakKebun != null && jenisAnalisa > -1 && 
                analisaDao.cekDuplikat(petakKebun.getKodePetak(), Integer.valueOf(txtRonde.getText()))){
            loadDataPetak();
            containerAnkem.getSelectionModel().select(pageInputDataAnalisa2);
            titPaneInputData.setExpanded(true);
        } else {
            if (jenisAnalisa == -1){
                alert.showErrorAlert("Anda belum memilih jenis analisa!");
            } else {
                if (petakKebun == null){
                    alert.showErrorAlert("Anda belum memilih petak kebun!");
                } else {
                    alert.showErrorAlert("Terjadi duplikat data petak kebun dan ronde!");
                }
            }
        }
    }
    
    public void tesCetak(){
        JasperPrint jp = reportsDao.cetakTes("23062834");
        JasperViewer.viewReport(jp);
        // TODO : buat prosedur cetak periode
    }
    
    public void previewLaporan(){
        dtpTglLaporanAwal.validate();
        if (dtpTglLaporanAwal.getValue() != null && txtLaporanTstr.validate()){
            if (chkLaporanSd.isSelected()){
                dtpTglLaporanAkhir.validate();
                if (dtpTglLaporanAkhir.getValue() != null){
                    java.sql.Date tglAwal = java.sql.Date.valueOf(dtpTglLaporanAwal.getValue());
                    java.sql.Date tglAkhir = java.sql.Date.valueOf(dtpTglLaporanAkhir.getValue());
                    switch (txtLaporanTstr.getText()){
                        case "TS" :
                            JasperPrint jp = reportsDao.laporanPeriodeTs(tglAwal, tglAkhir);
                            JasperViewer.viewReport(jp, false);
                            break;
                        case "TR" :
                            jp = reportsDao.laporanPeriodeTr(tglAwal, tglAkhir);
                            JasperViewer.viewReport(jp,false);
                            break;
                        case "TSI" :
                            jp = reportsDao.laporanPeriodeTsi(tglAwal, tglAkhir);
                            JasperViewer.viewReport(jp,false);
                            break;
                    }
                    /*
                    if (txtLaporanTstr.getText().equals("TS")){
                        JasperPrint jp = reportsDao.laporanPeriodeTs(tglAwal, tglAkhir);
                        JasperViewer.viewReport(jp, false);
                    } else {
                        if (txtLaporanTstr.getText().equals("TR")){
                            JasperPrint jp = reportsDao.laporanPeriodeTr(tglAwal, tglAkhir);
                            JasperViewer.viewReport(jp,false);
                        }
                    }
                    */
                } else {
                    alert.showErrorAlert("Tanggal sampai dengan laporan belum dipilih!");
                }
            } else {
                java.sql.Date tglAwal = java.sql.Date.valueOf(dtpTglLaporanAwal.getValue());
                switch (txtLaporanTstr.getText()){
                    case "TS" :
                        JasperPrint jp = reportsDao.laporanHarianTs(tglAwal);
                        JasperViewer.viewReport(jp, false);
                        break;
                    case "TR" :
                        jp = reportsDao.laporanHarianTr(tglAwal);
                        JasperViewer.viewReport(jp,false);
                        break;
                    case "TSI" :
                        jp = reportsDao.laporanHarianTsi(tglAwal);
                        JasperViewer.viewReport(jp,false);
                        break;
                }
            }
        } else {
            if (dtpTglLaporanAwal.getValue() == null) alert.showErrorAlert("Tanggal laporan belum dipilih!");
            if (!txtLaporanTstr.validate()) alert.showErrorAlert("Kepemilikan tebu belum dipilih!");
        }
    }
    
    public void previewDataKebun(){
        if (petakKebun != null){
            JasperPrint jp =  reportsDao.viewDataPetak(petakKebun.getKodePetak());
            Platform.runLater(() -> {
                JasperViewer.viewReport(jp, false);
            });
        }
    }
    
//</editor-fold>
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btnSubMenuDashboard.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageDashboard);
        });
        btnSubMenuInputDataAnalisa.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageInputDataAnalisa);
            resetDataAwal();
            resetField();
            validatorField();
            titPaneInputData.setExpanded(true);
            btnNextAnalisa.setVisible(true);
            btnViewData.setVisible(false);
            anchorPRonde.setVisible(true);
            inputStatus = "input";
        });
        btnSubMenuLaporanAnalisa.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageLaporan);
            resetFieldLaporan();
            validatorField();
        });
        
        btnBackMainMenu.setOnAction((event) -> {
            msc.getTabPane().getSelectionModel().select(msc.getTabMainMenu());
        });
        btnNextAnalisa.setOnAction((event) -> {
            validasiDataAwal();
        });
        btnTambahFisik.setOnAction((event) -> {
            if(txtInputPanjang.validate() && txtInputRuas.validate() && txtInputDiameter.validate()){
                Double panjang = Double.parseDouble(txtInputPanjang.getText());
                int ruas = Integer.parseInt(txtInputRuas.getText());
                Double diameter = Double.parseDouble(txtInputDiameter.getText());
                FisikTebu ft = new FisikTebu(panjang, ruas, diameter);
                dataFisikTemp.add(ft);
                refreshTabelFisik();
                txtInputPanjang.clear();
                txtInputRuas.clear();
                txtInputDiameter.clear();
                txtInputPanjang.requestFocus();
            }
        });
        btnHapusFisik.setOnAction((event) -> {
            if (tvFisik.getSelectionModel().getSelectedIndex() > -1){
                dataFisikTemp.remove(tvFisik.getSelectionModel().getSelectedIndex());
                refreshTabelFisik();
            }
        });
        btnHitung.setOnAction((event) -> {
            validasiInput();
            resetField();
            titPaneHasilAnalisa.setExpanded(true);
        });
        btnTambahSampel.setOnAction((event) -> {
            resetField();
            no_sampel++;
            txtNoSampel.setText(String.valueOf(no_sampel));
            titPaneInputData.setExpanded(true);
        });
        btnBatal.setOnAction((event) -> {
            if (alert.showConfirmation("Anda yakin akan membatalkan input?")){
                resetDataAwal();
                resetField();
                refreshTabelHasil();
                containerAnkem.getSelectionModel().select(pageInputDataAnalisa);
            }
        });
        btnSimpan.setOnAction((event) -> {
            if (analisaDao.insertNewData(dataAnalisa, listDataFisik)) alert.showInfoAlert("Data berhasil disimpan!");
            resetField();
            resetDataAwal();
            containerAnkem.getSelectionModel().select(pageInputDataAnalisa);
        });
        btnHapusAnalisa.setOnAction((event) -> {
            if (tvHasil.getSelectionModel().getSelectedIndex() > -1 && 
                    alert.showConfirmation("Anda yakin akan menghapus data ini?")){
                dataAnalisa.remove(tvHasil.getSelectionModel().getSelectedIndex());
                if (no_sampel < 1) no_sampel--;
                txtNoSampel.setText(String.valueOf(no_sampel));
                refreshTabelHasil();
            }
        });
        btnPreview.setOnAction((event) -> {
            previewLaporan();
        });
        btnPreviewPetak.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageInputDataAnalisa);
            resetDataAwal();
            resetField();
            validatorField();
            titPaneInputData.setExpanded(true);
            btnViewData.setVisible(true);
            btnNextAnalisa.setVisible(false);
            anchorPRonde.setVisible(false);
            inputStatus = "view";
        });
        btnViewData.setOnAction((event) -> {
            previewDataKebun();
        });
        btnTesJson.setOnAction((event) -> {
            try {
                Gson gson = new GsonBuilder().create();
                URL url = new URL("http://optanaman:optanaman@simpgbuma.ptpn7.com/index.php/apibuma/get_rend_cs/?tgl=2019-08-22");
                URLConnection request = url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                HasilAnalisaHarianCS hasil = gson.fromJson(root, HasilAnalisaHarianCS.class);
                alert.showInfoAlert(hasil.toString());
            } catch (MalformedURLException ex) {
                Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        /****************** DAFTAR BINDINGS **********************/
        btnSimpan.disableProperty().bind(Bindings.size(dataAnalisa).lessThan(1));
        btnTambahSampel.disableProperty().bind(Bindings.size(dataAnalisa).lessThan(1));
        btnHapusFisik.disableProperty().bind(Bindings.size(dataFisikTemp).lessThan(1));
        //txtNoSampel.textProperty().bind(Bindings.size(dataAnalisa).add(1).asString());
        //txtNoSampel.textProperty().bind(no_sampel.asString());
        txtLabelRonde.textProperty().bind(txtRonde.textProperty());
        btnHapusAnalisa.disableProperty().bind(Bindings.size(dataAnalisa).lessThan(1));
        dtpTglLaporanAkhir.disableProperty().bind(Bindings.not(chkLaporanSd.selectedProperty()));
    }
    
}
