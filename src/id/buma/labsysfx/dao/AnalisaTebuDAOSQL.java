/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.controller.ErrorMessages;
import id.buma.labsysfx.database.DB;
import id.buma.labsysfx.model.AnalisaTebu;
import id.buma.labsysfx.model.FisikTebu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class AnalisaTebuDAOSQL implements AnalisaTebuDAO {
    
    private final ErrorMessages errMsg = new ErrorMessages();

    @Override
    public int getRonde(String kodePetak) {
        String sql = "call getRonde(?)";
        try (Connection conn = DB.getConn()){
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, kodePetak);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                return rs.getInt("hasil");
            }
        } catch (SQLException ex) {
            errMsg.showErrorAlert("Error DAO getRonde!" + "\n" + 
                    "Error code : " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public boolean insertNewData(ObservableList<AnalisaTebu> dataAnalisa,
            ObservableList<ObservableList<FisikTebu>> dataFisik) {
        String sql = "insert into tbl_analisa_kemasakan (" +
                "id_analisa," + //1
                "kode_petak," + //2
                "id_user," + //3
                "jenis_analisa," + //4
                "ronde," + //5
                "no_sampel," + //6
                "tgl_analisa," + //7
                "tgl_posting," + //8
                "berat_tebu_atas," + //9
                "berat_tebu_tengah," + //10
                "berat_tebu_bawah," + //11
                "berat_nira_atas," + //12
                "berat_nira_tengah," + //13
                "berat_nira_bawah," + //14
                "penggerek," + //15
                "brix_baca_atas," + //16
                "brix_baca_tengah," + //17
                "brix_baca_bawah," + //18
                "brix_baca_campur," + //19
                "pol_baca_atas," + //20
                "pol_baca_tengah," + //21
                "pol_baca_bawah," + //22
                "pol_baca_campur," + //23
                "brix_atas," + //24
                "brix_tengah," + //25
                "brix_bawah," + //26
                "brix_campur," + //27
                "pol_atas," + //28
                "pol_tengah," + //29
                "pol_bawah," + //30
                "pol_campur," + //31
                "suhu," + //32
                "koreksi_suhu," + //33
                "faktor," + //34
                "hk_atas," + //35
                "hk_tengah," + //36
                "hk_bawah," + //37
                "hk_campur," + //38
                "nn_atas," + //39
                "nn_tengah," + //40
                "nn_bawah," + //41
                "nn_campur," + //42
                "rend_atas," + //43
                "rend_tengah," + //44
                "rend_bawah," + //45
                "rend_campur," + //46
                "fk," + //47
                "kp," + //48
                "kdt," + //49
                "rata_panjang," + //50
                "rata_ruas," + //51
                "rata_diameter," + //52
                "kg_per_meter" + //53
                ") values(" +
                "?,?,?,?,?,?,?,?,?,?,?," + // 11
                "?,?,?,?,?,?,?,?,?,?," + // 10
                "?,?,?,?,?,?,?,?,?,?," + // 10
                "?,?,?,?,?,?,?,?,?,?," + // 10
                "?,?,?,?,?,?,?,?,?,?,?,?" + // 12
                ")";
        String sqlFisik = "insert into tbl_fisik_tebu ("+
                "id_analisa," +
                "no_urut," +
                "panjang_batang," +
                "jml_ruas," +
                "diameter_batang) values (" +
                "?,?,?,?,?" +
                ")";
        try (Connection conn = DB.getConn()){
            PreparedStatement psAnalisa = DB.getConn().prepareStatement(sql);
            PreparedStatement psFisik = DB.getConn().prepareStatement(sqlFisik);
            conn.setAutoCommit(false);
            
            int indexFisik = 0;
            for (AnalisaTebu itemsAnalisa : dataAnalisa){
                String uuid = UUID.randomUUID().toString().replace("-", "");
                psAnalisa.setString(1, uuid);
                psAnalisa.setString(2, itemsAnalisa.getKodePetak());
                psAnalisa.setInt(3, itemsAnalisa.getIdUser());
                psAnalisa.setInt(4, itemsAnalisa.getJenisAnalisa());
                psAnalisa.setInt(5, itemsAnalisa.getRonde());
                psAnalisa.setInt(6, itemsAnalisa.getNoSampel());
                psAnalisa.setDate(7, itemsAnalisa.getTglAnalisa());
                psAnalisa.setTimestamp(8, itemsAnalisa.getTglPosting());
                psAnalisa.setDouble(9, itemsAnalisa.getBeratTebuAtas());
                psAnalisa.setDouble(10, itemsAnalisa.getBeratTebuTengah());
                psAnalisa.setDouble(11, itemsAnalisa.getBeratTebuBawah());
                psAnalisa.setDouble(12, itemsAnalisa.getBeratNiraAtas());
                psAnalisa.setDouble(13, itemsAnalisa.getBeratNiraTengah());
                psAnalisa.setDouble(14, itemsAnalisa.getBeratNiraBawah());
                psAnalisa.setDouble(15, itemsAnalisa.getPenggerek());
                psAnalisa.setDouble(16, itemsAnalisa.getBrixBacaAtas());
                psAnalisa.setDouble(17, itemsAnalisa.getBrixBacaTengah());
                psAnalisa.setDouble(18, itemsAnalisa.getBrixBacaBawah());
                psAnalisa.setDouble(19, itemsAnalisa.getBrixBacaCampur());
                psAnalisa.setDouble(20, itemsAnalisa.getPolBacaAtas());
                psAnalisa.setDouble(21, itemsAnalisa.getPolBacaTengah());
                psAnalisa.setDouble(22, itemsAnalisa.getPolBacaBawah());
                psAnalisa.setDouble(23, itemsAnalisa.getPolBacaCampur());
                psAnalisa.setDouble(24, itemsAnalisa.getBrixAtas());
                psAnalisa.setDouble(25, itemsAnalisa.getBrixTengah());
                psAnalisa.setDouble(26, itemsAnalisa.getBrixBawah());
                psAnalisa.setDouble(27, itemsAnalisa.getBrixCampur());
                psAnalisa.setDouble(28, itemsAnalisa.getPolAtas());
                psAnalisa.setDouble(29, itemsAnalisa.getPolTengah());
                psAnalisa.setDouble(30, itemsAnalisa.getPolBawah());
                psAnalisa.setDouble(31, itemsAnalisa.getPolCampur());
                psAnalisa.setDouble(32, itemsAnalisa.getSuhu());
                psAnalisa.setDouble(33, itemsAnalisa.getKoreksiSuhu());
                psAnalisa.setDouble(34, itemsAnalisa.getFaktorPerah());
                psAnalisa.setDouble(35, itemsAnalisa.getHkAtas());
                psAnalisa.setDouble(36, itemsAnalisa.getHkTengah());
                psAnalisa.setDouble(37, itemsAnalisa.getHkBawah());
                psAnalisa.setDouble(38, itemsAnalisa.getHkCampur());
                psAnalisa.setDouble(39, itemsAnalisa.getNnAtas());
                psAnalisa.setDouble(40, itemsAnalisa.getNnTengah());
                psAnalisa.setDouble(41, itemsAnalisa.getNnBawah());
                psAnalisa.setDouble(42, itemsAnalisa.getNnCampur());
                psAnalisa.setDouble(43, itemsAnalisa.getRendAtas());
                psAnalisa.setDouble(44, itemsAnalisa.getRendTengah());
                psAnalisa.setDouble(45, itemsAnalisa.getRendBawah());
                psAnalisa.setDouble(46, itemsAnalisa.getRendCampur());
                psAnalisa.setDouble(47, itemsAnalisa.getFk());
                psAnalisa.setDouble(48, itemsAnalisa.getKp());
                psAnalisa.setDouble(49, itemsAnalisa.getKdt());
                psAnalisa.setDouble(50, itemsAnalisa.getRataPanjang());
                psAnalisa.setInt(51, itemsAnalisa.getRataRuas());
                psAnalisa.setDouble(52, itemsAnalisa.getRataDiameter());
                psAnalisa.setDouble(53, itemsAnalisa.getKgPerMeter());
                
                // insert fisik tebu
                int indexBatang = 1;
                for (FisikTebu ft : dataFisik.get(indexFisik)){
                    psFisik.setString(1, uuid);
                    psFisik.setInt(2, indexBatang);
                    psFisik.setDouble(3, ft.getPanjang());
                    psFisik.setInt(4, ft.getRuas());
                    psFisik.setDouble(5, ft.getDiameter());
                    indexBatang ++;
                    psFisik.addBatch();
                }
                indexFisik ++;
                
                try {
                    psAnalisa.execute();
                    psFisik.executeBatch();
                    conn.commit();
                } catch (SQLException ex) {
                    conn.rollback();
                    errMsg.showErrorAlert("Error DAO insertNewData!" + "\n" +
                            "Error code : " + ex.toString());
                    Logger.getLogger(AnalisaTebuDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            errMsg.showErrorAlert("Error DAO insertNewData!" + "\n" + 
                    "Error code : " + ex.getMessage());
            Logger.getLogger(AnalisaTebuDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Double getKp(String kodePetak, int rondeSekarang) {
        String sql = "select * from tbl_analisa_kemasakan where kode_petak = ? and ronde = ?";
        try (Connection conn = DB.getConn()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kodePetak);
            ps.setInt(2, rondeSekarang - 2);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            Double jmlRend = 0.00;
            while (rs.next()){
                jmlRend = jmlRend + rs.getDouble("rend_campur");
                i++;
            }
            if (i == 0){
                return 0.00;
            } else {
                DecimalFormat df = new DecimalFormat("#0.00");
                Double hasil = Double.valueOf(df.format(jmlRend/i));
                return hasil;
            }
        } catch (SQLException ex) {
            errMsg.showErrorAlert("Error DAO getKp!" + "\n" +
                    "Error code : " + ex.getMessage());
            Logger.getLogger(AnalisaTebuDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.00;
    }

    @Override
    public Double getKdt(String kodePetak, int rondeSekarang) {
        String sql = "select * from tbl_analisa_kemasakan where kode_petak = ? and ronde = ?";
        try (Connection conn = DB.getConn()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kodePetak);
            ps.setInt(2, rondeSekarang - 2);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            Double jmlHk = 0.00;
            while (rs.next()){
                jmlHk = jmlHk + rs.getDouble("hk_bawah");
                i++;
            }
            if (i == 0){
                return 0.00;
            } else {
                DecimalFormat df = new DecimalFormat("#0.00");
                return Double.valueOf(df.format(jmlHk/i));
            }
        } catch (SQLException ex) {
            errMsg.showErrorAlert("Error DAO getKdt!" + "\n" +
                        "Error code : " + ex.getMessage());
            Logger.getLogger(AnalisaTebuDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.00;
    }

    @Override
    public boolean cekDuplikat(String kodePetak, int rondeSekarang) {
        String sql = "select * from tbl_analisa_kemasakan where kode_petak = ? and ronde = ?";
        try (Connection conn = DB.getConn()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kodePetak);
            ps.setInt(2, rondeSekarang);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()){
                i++;
            }
            if (i == 0) return true;
        } catch (SQLException ex){
            errMsg.showErrorAlert("Error DAO getKdt!" + "\n" +
                        "Error code : " + ex.getMessage());
            Logger.getLogger(AnalisaTebuDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
