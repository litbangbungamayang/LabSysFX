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
import id.buma.labsysfx.model.FisikTebu;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private JFXDatePicker dtpTglAnalisa;
    
    final ObservableList<FisikTebu> dataFisik = FXCollections.observableArrayList();
    
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
            txtBobotTebuAtas.validate();
        });
        
        txtBobotNiraAtas.getValidators().add(validatorWajib);
        txtBobotNiraAtas.getValidators().add(validatorDesimal);
        txtBobotNiraAtas.focusedProperty().addListener((observable) -> {
            txtBobotNiraAtas.validate();
        });
        
        txtBobotTebuTengah.getValidators().add(validatorWajib);
        txtBobotTebuTengah.getValidators().add(validatorDesimal);
        txtBobotTebuTengah.focusedProperty().addListener((observable) -> {
            txtBobotTebuTengah.validate();
        });
        
        txtBobotNiraTengah.getValidators().add(validatorWajib);
        txtBobotNiraTengah.getValidators().add(validatorDesimal);
        txtBobotNiraTengah.focusedProperty().addListener((observable) -> {
            txtBobotNiraTengah.validate();
        });
        
        txtBobotTebuBawah.getValidators().add(validatorWajib);
        txtBobotTebuBawah.getValidators().add(validatorDesimal);
        txtBobotTebuBawah.focusedProperty().addListener((observable) -> {
            txtBobotTebuBawah.validate();
        });
        
        txtBobotNiraBawah.getValidators().add(validatorWajib);
        txtBobotNiraBawah.getValidators().add(validatorDesimal);
        txtBobotNiraBawah.focusedProperty().addListener((observable) -> {
            txtBobotNiraBawah.validate();
        });
        
        txtPenggerek.getValidators().add(validatorWajib);
        txtPenggerek.getValidators().add(validatorDesimal);
        txtPenggerek.focusedProperty().addListener((observable) -> {
            txtPenggerek.validate();
        });
        
        txtBrixAtas.getValidators().add(validatorWajib);
        txtBrixAtas.getValidators().add(validatorDesimal);
        txtBrixAtas.focusedProperty().addListener((observable) -> {
            txtBrixAtas.validate();
        });
        
        txtBrixTengah.getValidators().add(validatorWajib);
        txtBrixTengah.getValidators().add(validatorDesimal);
        txtBrixTengah.focusedProperty().addListener((observable) -> {
            txtBrixTengah.validate();
        });
        
        txtBrixBawah.getValidators().add(validatorWajib);
        txtBrixBawah.getValidators().add(validatorDesimal);
        txtBrixBawah.focusedProperty().addListener((observable) -> {
            txtBrixBawah.validate();
        });
        
        txtBrixCampur.getValidators().add(validatorWajib);
        txtBrixCampur.getValidators().add(validatorDesimal);
        txtBrixCampur.focusedProperty().addListener((observable) -> {
            txtBrixCampur.validate();
        });
        
        txtPolAtas.getValidators().add(validatorWajib);
        txtPolAtas.getValidators().add(validatorDesimal);
        txtPolAtas.focusedProperty().addListener((observable) -> {
            txtPolAtas.validate();
        });
        
        txtPolTengah.getValidators().add(validatorWajib);
        txtPolTengah.getValidators().add(validatorDesimal);
        txtPolTengah.focusedProperty().addListener((observable) -> {
            txtPolTengah.validate();
        });
        
        txtPolBawah.getValidators().add(validatorWajib);
        txtPolBawah.getValidators().add(validatorDesimal);
        txtPolBawah.focusedProperty().addListener((observable) -> {
            txtPolBawah.validate();
        });
        
        txtPolCampur.getValidators().add(validatorWajib);
        txtPolCampur.getValidators().add(validatorDesimal);
        txtPolCampur.focusedProperty().addListener((observable) -> {
            txtPolCampur.validate();
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
    
    public void validasiInput(){
        if (txtBobotTebuAtas.validate() && txtBobotTebuTengah.validate() && txtBobotTebuBawah.validate() &&
                txtBobotNiraAtas.validate() && txtBobotNiraTengah.validate() && txtBobotNiraBawah.validate() &&
                txtBrixAtas.validate() && txtBrixTengah.validate() && txtBrixBawah.validate() && txtBrixCampur.validate() &&
                txtPolAtas.validate() && txtPolTengah.validate() && txtPolBawah.validate() && txtPolCampur.validate() &&
                txtSuhu.validate() && txtKoreksiSuhu.validate() &&
                dataFisik.size() > 0){
            System.out.println("Semua OK");
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
