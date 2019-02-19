/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.IntegerValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import id.buma.labsysfx.MainApp;
import id.buma.labsysfx.model.AnalisaTebu;
import id.buma.labsysfx.model.FisikTebu;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
    private Tab pageInputDataAnalisa2;
    @FXML
    private JFXTextField txtPetak;
    @FXML
    private JFXTextField txtRonde;
    @FXML
    private JFXTextField txtJenisAnalisa;
    @FXML
    private JFXButton btnNextAnalisa;
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
    private JFXButton btnSimpanData;
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
    private JFXDatePicker dtpTglAnalisa;
    
    final ObservableList<FisikTebu> dataFisik = FXCollections.observableArrayList();
    
    final ObservableList<AnalisaTebu> dataAnalisa = FXCollections.observableArrayList();
    
    private MainApp mainApp;
    
    private MainScreenController msc;
    
    private final ErrorMessages alert = new ErrorMessages();
    
    
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
                    txtInputDiameter.setText(String.valueOf(koreksiDesimal(angka)));
                }
            }
        });
        
        dtpTglAnalisa.getValidators().add(validatorWajib);
        dtpTglAnalisa.focusedProperty().addListener((observable) -> {
            dtpTglAnalisa.validate();
        });
        
    }
    
    public void autoCompleteGroup(){
        JFXAutoCompletePopup<String> autoCompleteJenis = new JFXAutoCompletePopup<>();
        JFXAutoCompletePopup<String> autoCompletePetak = new JFXAutoCompletePopup<>();
        autoCompleteJenis.getSuggestions().addAll("Analisa Rutin", "Analisa Tebu Bakar", "Analisa Tebu Percobaan");
        autoCompletePetak.getSuggestions().addAll("Rayon I Petak 056", "Rayon II Petak 129", "SARENGAT D.");// todo: tambahkan kode generate kode petak disini
        autoCompleteJenis.cellLimitProperty().setValue(3);
        autoCompletePetak.cellLimitProperty().setValue(10);
        txtJenisAnalisa.textProperty().addListener((observable) -> {
            autoCompleteJenis.filter(string -> string.toLowerCase().contains(txtJenisAnalisa.getText().toLowerCase()));
            autoCompleteJenis.show(txtJenisAnalisa);
        });
        txtJenisAnalisa.focusedProperty().addListener((observable) -> {
            if (txtJenisAnalisa.isFocused()) autoCompleteJenis.show(txtJenisAnalisa);
        });
        autoCompleteJenis.setSelectionHandler((evt) -> {
            txtJenisAnalisa.setText(evt.getObject());
        });
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
            autoCompletePetak.hide();
        });
    }
    
    public void resetField(){
        txtJenisAnalisa.clear();
        txtPetak.clear();
        txtRonde.clear();
        txtPetak.getValidators().clear();
        txtRonde.getValidators().clear();
        
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
        dataFisik.clear();
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
        
    public void refreshTabelFisik(){
        tvFisik.setItems(dataFisik);
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
        tcRendC.setCellValueFactory(new PropertyValueFactory<>("rendCampur"));
        tcFK.setCellValueFactory(new PropertyValueFactory<>("fk"));
    }
    
    public FisikTebu hitungRataRata(){
        Double rataPanjang = 0.00;
        int rataRuas = 0;
        Double rataDiameter = 0.00;
        int jmlData;
        if (!dataFisik.isEmpty()){
            jmlData = dataFisik.size();
            for (FisikTebu fisikBatang : dataFisik) {
                rataPanjang = rataPanjang + fisikBatang.getPanjang();
                rataRuas = rataRuas + fisikBatang.getRuas();
                rataDiameter = rataDiameter + fisikBatang.getDiameter();
            }
            rataPanjang = rataPanjang/jmlData;
            rataDiameter = rataDiameter/jmlData;
            rataRuas = Math.round(rataRuas/jmlData);
        }
        FisikTebu rataFisik = new FisikTebu(rataPanjang, rataRuas, rataDiameter);
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
    
    public void validasiInput(){
        if (txtBobotTebuAtas.validate() && txtBobotTebuTengah.validate() && txtBobotTebuBawah.validate() &&
                txtBobotNiraAtas.validate() && txtBobotNiraTengah.validate() && txtBobotNiraBawah.validate() &&
                txtBrixAtas.validate() && txtBrixTengah.validate() && txtBrixBawah.validate() && txtBrixCampur.validate() &&
                txtPolAtas.validate() && txtPolTengah.validate() && txtPolBawah.validate() && txtPolCampur.validate() &&
                txtSuhu.validate() && txtKoreksiSuhu.validate() &&
                dataFisik.size() > 0){
            Double beratTebuAtas = Double.parseDouble(txtBobotTebuAtas.getText());
            Double beratTebuTengah = Double.parseDouble(txtBobotTebuTengah.getText());
            Double beratTebuBawah = Double.parseDouble(txtBobotTebuBawah.getText());
            Double beratNiraAtas = Double.parseDouble(txtBobotNiraAtas.getText());
            Double beratNiraTengah = Double.parseDouble(txtBobotNiraTengah.getText());
            Double beratNiraBawah = Double.parseDouble(txtBobotNiraBawah.getText());
            Double brixBacaAtas = Double.parseDouble(txtBrixAtas.getText());
            Double brixBacaTengah = Double.parseDouble(txtBrixTengah.getText());
            Double brixBacaBawah = Double.parseDouble(txtBrixBawah.getText());
            Double brixBacaCampur = Double.parseDouble(txtBrixCampur.getText());
            Double koreksiSuhu = Double.parseDouble(txtKoreksiSuhu.getText());
            Double brixAtas = brixBacaAtas + koreksiSuhu;
            Double brixTengah = brixBacaTengah + koreksiSuhu;
            Double brixBawah = brixBacaBawah + koreksiSuhu;
            Double brixCampur = brixBacaCampur + koreksiSuhu;
            Double polBacaAtas = Double.parseDouble(txtPolAtas.getText());
            Double polBacaTengah = Double.parseDouble(txtPolTengah.getText());
            Double polBacaBawah = Double.parseDouble(txtPolBawah.getText());
            Double polBacaCampur = Double.parseDouble(txtPolCampur.getText());
            Double faktorPerah = (beratNiraAtas + beratNiraTengah + beratNiraBawah) / (beratTebuAtas + beratTebuTengah + beratTebuBawah);
            Double polAtas = hitungPol(polBacaAtas, hitungBJ(brixAtas));
            Double polTengah = hitungPol(polBacaTengah, hitungBJ(brixTengah));
            Double polBawah = hitungPol(polBacaBawah, hitungBJ(brixBawah));
            Double polCampur = hitungPol(polBacaCampur, hitungBJ(brixCampur));
            Double nnAtas = hitungNn(polAtas, brixAtas);
            Double nnTengah = hitungNn(polTengah, brixTengah);
            Double nnBawah = hitungNn(polBawah, brixBawah);
            Double nnCampur = hitungNn(polCampur, brixCampur);
            Double rendAtas = nnAtas * faktorPerah;
            Double rendTengah = nnTengah * faktorPerah;
            Double rendBawah = nnBawah * faktorPerah;
            Double rendCampur = nnCampur * faktorPerah;
            Double fk = (rendBawah - rendAtas) / rendBawah * 100;
            java.sql.Date tglAnalisa = java.sql.Date.valueOf(dtpTglAnalisa.getValue());
            java.sql.Date tglPosting = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            /*
            Data untuk KP dan KDT sementara dummy sambil nunggu kesiapan koneksi data
            */
            Double kp = 120.00;
            Double kdt = 100.00;
            /*
            ******
            */
            AnalisaTebu at = new AnalisaTebu("kode001",
                    1, //idUser
                    1, //jenisAnalisa
                    1, //ronde
                    1, //noSampel
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
                    faktorPerah, //faktorPerah
                    (polAtas/brixAtas)*100, //hkAtas
                    (polTengah/brixTengah)*100, //hkTengah
                    (polBawah/brixBawah)*100, //hkBawah
                    (polCampur/brixCampur)*100, //hkCampur
                    nnAtas, //NN Atas
                    nnTengah, //NN Tengah
                    nnBawah, //NN Bawah
                    nnCampur, //NN Campur
                    rendAtas, //rend atas
                    rendTengah, //rend tengah
                    rendBawah, //rend bawah
                    rendCampur, //rend campur
                    fk, // FK
                    kp, // KP
                    kdt // KDT
            );
            dataAnalisa.add(at);
            refreshTabelHasil();
        } else {
            if (dataFisik.isEmpty()){
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
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        btnSubMenuDashboard.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageDashboard);
        });
        btnSubMenuInputDataAnalisa.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageInputDataAnalisa);
            datePickerDisplay();
            resetField();
            validatorField();
            autoCompleteGroup();
            titPaneInputData.setExpanded(true);
        });
        btnBackMainMenu.setOnAction((event) -> {
            msc.getTabPane().getSelectionModel().select(msc.getTabMainMenu());
        });
        btnNextAnalisa.setOnAction((event) -> {
            containerAnkem.getSelectionModel().select(pageInputDataAnalisa2);
        });
        btnSimpanData.setOnAction((event) -> {
            titPaneHasilAnalisa.setExpanded(true);
        });
        btnTambahFisik.setOnAction((event) -> {
            if(txtInputPanjang.validate() && txtInputRuas.validate() && txtInputDiameter.validate()){
                Double panjang = Double.parseDouble(txtInputPanjang.getText());
                int ruas = Integer.parseInt(txtInputRuas.getText());
                Double diameter = Double.parseDouble(txtInputDiameter.getText());
                FisikTebu ft = new FisikTebu(panjang, ruas, diameter);
                dataFisik.add(ft);
                refreshTabelFisik();
                txtInputPanjang.clear();
                txtInputRuas.clear();
                txtInputDiameter.clear();
                txtInputPanjang.requestFocus();
            }
        });
        btnHapusFisik.setOnAction((event) -> {
            if (tvFisik.getSelectionModel().getSelectedIndex() > -1){
                dataFisik.remove(tvFisik.getSelectionModel().getSelectedIndex());
                refreshTabelFisik();
            }
        });
        btnSimpanData.setOnAction((event) -> {
            validasiInput();
        });
    }
    
}
