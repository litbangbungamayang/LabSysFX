/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import id.buma.labsysfx.MainApp;
import id.buma.labsysfx.dao.LabCSDAOSQL;
import id.buma.labsysfx.dao.ReportsPrintingDAO;
import id.buma.labsysfx.dao.ReportsPrintingDAOSQL;
import id.buma.labsysfx.model.HasilAnalisaHarianCS;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.text.Text;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class CSController implements Initializable {
    
//<editor-fold defaultstate="collapsed" desc="FXML Declarations">
    @FXML
    private TabPane containerCS;
    @FXML
    private Tab pageDashboard;
    @FXML
    private Tab pageFaktor1;
    @FXML
    private Tab pageFaktor2;
    @FXML
    private Tab pageLaporan;
    @FXML
    private JFXButton btnBackMainMenu;
    @FXML
    private JFXButton btnSubMenuFaktor;
    @FXML
    private JFXButton btnSubMenuLaporan;
    @FXML
    private JFXButton btnSubMenuDashboard;
    @FXML
    private JFXButton btnLanjutLaporan;
    @FXML
    private JFXButton btnHitungEst;
    @FXML
    private JFXButton btnTerapkanFaktor;
    @FXML
    private JFXButton btnPreview;
    @FXML
    private JFXDatePicker dtpTglTimbang;
    @FXML
    private JFXDatePicker dtpTglLaporan1;
    @FXML
    private JFXDatePicker dtpTglLaporan2;
    @FXML
    private Text txtTglTimbang;
    @FXML
    private Text txtTonTebu;
    @FXML
    private Text txtTonHablur;
    @FXML
    private Text txtRend;
    @FXML
    private Text txtFaktor;
    @FXML
    private Text txtHablurEfektif;
    @FXML
    private JFXTextField txtRendPabrik;
    @FXML
    private CheckBox chkLaporanSd;
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Object Declarations">
    private MainApp mainApp;
    private MainScreenController msc;
    private final ErrorMessages alert = new ErrorMessages();
    private final LabCSDAOSQL labCsDao = new LabCSDAOSQL();
    private final ReportsPrintingDAO reportsDao = new ReportsPrintingDAOSQL();
    private final NumberValidator validatorAngka = new NumberValidator("Input tidak valid! Harus berupa angka.");
    private final DoubleValidator validatorDesimal = new DoubleValidator();
    private final RequiredFieldValidator validatorWajib = new RequiredFieldValidator();
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Method Declarations">
    public CSController(){
        
    }
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    public void setMainScreenController(MainScreenController msc){
        this.msc = msc;
    }
    
    public void showDashboard(){
        containerCS.getSelectionModel().select(pageDashboard);
    }
    
    public void getDataCS(){
        if (dtpTglTimbang.getValue() != null){
            boolean isNotExist = labCsDao.cekFaktorEfektif(dtpTglTimbang.getValue().toString());
            if (isNotExist){
                HasilAnalisaHarianCS hasil = labCsDao.getDataCS(dtpTglTimbang.getValue().toString());
                if (hasil != null){
                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    containerCS.getSelectionModel().select(pageFaktor2);
                    txtTglTimbang.setText(dtpTglTimbang.getValue().toString());
                    txtTonTebu.setText(df.format(hasil.ton_tebu) + " ton");
                    txtTonHablur.setText(df.format(hasil.hablur_analisa) + " ton");
                    txtRend.setText(df.format(hasil.rend));
                } else {
                    alert.showWarningAlert("Data tidak ditemukan!");
                }
            } else {
                alert.showErrorAlert("Faktor untuk tanggal ini sudah diterapkan!");
            }
        } else {
            alert.showErrorAlert("Pilih tanggal timbang!");
        }
    }
    
    public void validatorField(){
        validatorWajib.setMessage("Harus diisi!");
        validatorDesimal.setMessage("Angka desimal tidak valid!");
        
        txtRendPabrik.getValidators().add(validatorAngka);
        txtRendPabrik.getValidators().add(validatorWajib);
        txtRendPabrik.getValidators().add(validatorDesimal);
        txtRendPabrik.focusedProperty().addListener((observable) -> {
            txtRendPabrik.validate();   
        });
        
        dtpTglLaporan1.getValidators().add(validatorWajib);
        dtpTglLaporan1.focusedProperty().addListener((observable) -> {
            dtpTglLaporan1.validate();
        });
        
        dtpTglLaporan2.getValidators().add(validatorWajib);
        dtpTglLaporan2.focusedProperty().addListener((observable) -> {
            dtpTglLaporan2.validate();
        });
    }
    
    public Double hitungFaktorPengali(Double rendPabrik, Double rendCs){
        Double hasil = 0.0;
        hasil = new BigDecimal(((rendPabrik / rendCs)/12) + 0.9).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return hasil;
    }
    
    public void hitungEstimasi(){
        if (txtRendPabrik.validate() && btnHitungEst.getText().equals("Hitung Estimasi")){
            Double rendPabrik = Double.parseDouble(txtRendPabrik.getText());
            Double rendCs = Double.parseDouble(txtRend.getText());
            txtFaktor.setText(String.valueOf(hitungFaktorPengali(rendPabrik, rendCs)));
            txtHablurEfektif.setText(String.valueOf(labCsDao.getHablurEfektif(
                    Double.parseDouble(txtFaktor.getText()), txtTglTimbang.getText())) + " ton");
            btnHitungEst.setText("Hitung Ulang");
            txtRendPabrik.setEditable(false);
        } else {
            if (btnHitungEst.getText().equals("Hitung Ulang")){
                txtRendPabrik.clear();
                txtRendPabrik.setEditable(true);
                btnHitungEst.setText("Hitung Estimasi");
            }
        }
    }
    
    public void resetFields(){
        txtRendPabrik.clear();
        btnHitungEst.setText("Hitung Estimasi");
        btnTerapkanFaktor.setText("Terapkan Faktor");
        txtRendPabrik.setEditable(true);
        txtFaktor.setText(null);
        txtHablurEfektif.setText(null);
    }
    
    public void terapkanFaktor(){
        if (alert.showConfirmation("Anda akan menerapkan faktor " + txtFaktor.getText() + " untuk tanggal timbang " + txtTglTimbang.getText() +
                " dan hablur efektif (TR) menjadi " + txtHablurEfektif.getText() + " ton. Terapkan faktor ini?")){
            if (labCsDao.setFaktorEfektif(Double.parseDouble(txtFaktor.getText()), dtpTglTimbang.getValue().toString())){
                alert.showInfoAlert("Data tersimpan");
                btnTerapkanFaktor.setText("Lihat Laporan Harian");
            } else {
                alert.showErrorAlert("Server tidak merespon atau data tidak valid. Data tidak tersimpan.");
            }
        }
    }
    
    public void previewLaporan(){
        if (!chkLaporanSd.isSelected() && dtpTglLaporan1.validate()){
            JasperPrint jp = reportsDao.viewDailyCS(java.sql.Date.valueOf(dtpTglLaporan1.getValue()));
            JasperViewer.viewReport(jp, false);
        } else {
            if (chkLaporanSd.isSelected() && dtpTglLaporan1.validate() && dtpTglLaporan2.validate()){
                JasperPrint jp = reportsDao.viewCSToDate(java.sql.Date.valueOf(dtpTglLaporan1.getValue()), java.sql.Date.valueOf(dtpTglLaporan2.getValue()));
                JasperViewer.viewReport(jp, false);
            }
        }
    }
//</editor-fold>
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBackMainMenu.setOnAction((event) -> {
            msc.getTabPane().getSelectionModel().select(msc.getTabMainMenu());
        });
        btnSubMenuFaktor.setOnAction((event) -> {
            containerCS.getSelectionModel().select(pageFaktor1);
            msc.setDatePickerFormatting(dtpTglTimbang);
            dtpTglTimbang.setValue(null);
            validatorField();
            resetFields();
        });
        btnSubMenuLaporan.setOnAction((event) -> {
            containerCS.getSelectionModel().select(pageLaporan);
            msc.setDatePickerFormatting(dtpTglLaporan1);
            msc.setDatePickerFormatting(dtpTglLaporan2);
            dtpTglLaporan1.setValue(null);
            dtpTglLaporan2.setValue(null);
        });
        btnSubMenuDashboard.setOnAction((event) -> {
            showDashboard();
        });
        btnLanjutLaporan.setOnAction((event) -> {
            getDataCS();
        });
        btnHitungEst.setOnAction((event) -> {
            hitungEstimasi();
        });
        btnTerapkanFaktor.setOnAction((event) -> {
            if (btnTerapkanFaktor.getText().equals("Terapkan Faktor")){
                terapkanFaktor();
            }
        });
        btnPreview.setOnAction((event) -> {
            previewLaporan();
        });
        
        dtpTglLaporan2.disableProperty().bind(Bindings.not(chkLaporanSd.selectedProperty()));
    }
    
}
