/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.controller.ErrorMessages;
import id.buma.labsysfx.database.DB;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JsonQLQueryExecuterFactory;
import net.sf.jasperreports.engine.query.JsonQueryExecuter;

/**
 *
 * @author Bayu Anandavi Muhardik
 * 
 */

public class ReportsPrintingDAOSQL implements ReportsPrintingDAO{

    private final ErrorMessages alert = new ErrorMessages();
    
    @Override
    public JasperPrint cetakTes(String kodePetak) {
        JasperPrint jp = null;
        try (Connection conn  = DB.getConn()){
            String fileName = "./reports/test_labsys.jasper";
            Map map = new HashMap();
            map.put("p_kode_petak", kodePetak);
            jp = JasperFillManager.fillReport(fileName, map, conn); 
        } catch (SQLException | JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }

    @Override
    public JasperPrint laporanHarianTs(Date tglAnalisa) {
        JasperPrint jp = null;
        try (Connection conn = DB.getConn()){
            //InputStream fileName = getClass().getResourceAsStream("/reports/LaporanHarianTS.jasper");
            //InputStream fileName = new URL("http://apps.bcn.web.id/reports/LaporanHarianTS.jasper").openStream();
            InputStream fileName = new URL(getReportsUrl("LaporanHarianTS.jasper")).openStream();
            String sql =
                    "select * from " +
                    "(select ankem.*, petak.kategori, petak.luas_petak, " +
                        "petak.masa_tanam, petak.nama_kebun, petak.no_kontrak, petak.no_petak, " +
                        "petak.rayon, petak.varietas, varietas.nama_varietas, " +
                        "if(length(no_kontrak) = 15, substring(no_kontrak,9,1) * 1, " +
                            "substring(no_kontrak,9,2) * 1) as afdeling, " +
                        "hk_bawah-hk_atas as selisih_hk, " +
                        "rend_bawah - rend_atas as selisih_rend, " +
                        "1 as kunci " +
                        "from tbl_analisa_kemasakan ankem " +
                        "join tbl_master_petak petak on ankem.kode_petak = petak.kode_petak " +
                        "join tbl_varietas varietas on petak.varietas = varietas.id_varietas) as t1 " +
                    "join " +
                    "(select tuser.nama_user, " +
                        "1 as kunci " +
                        "from tbl_user tuser where tuser.bagian = 'LTB' and tuser.role = 'ASKEP') as t2 " +
                    "on t1.kunci = t2.kunci " +
                    "where t1.rayon like ? and t1.tgl_analisa = ? " +
                    "order by t1.ronde, t1.rayon, t1.afdeling, t1.masa_tanam, t1.no_petak";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "TS%");
            ps.setDate(2, tglAnalisa);
            ResultSet rs = ps.executeQuery();
            JRDataSource jrds = new JRResultSetDataSource(rs);
            Map map = new HashMap();
            map.put("tgl_awal", tglAnalisa);
            jp = JasperFillManager.fillReport(fileName, map, jrds);
        } catch (SQLException|JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing laporanHarianTS method!\nError code :\n" + ex.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }

    @Override
    public JasperPrint laporanPeriodeTs(Date tglAwal, Date tglAkhir) {
        JasperPrint jp = null;
        try (Connection conn = DB.getConn()){
            //InputStream fileName = getClass().getResourceAsStream("/reports/LaporanPeriodeTS.jasper");
            InputStream fileName = new URL("http://apps.bcn.web.id/reports/LaporanPeriodeTS.jasper").openStream();
            String sql =
                    "select * from " +
                    "(select ankem.*, petak.kategori, petak.luas_petak, " +
                        "petak.masa_tanam, petak.nama_kebun, petak.no_kontrak, petak.no_petak, " +
                        "petak.rayon, petak.varietas, varietas.nama_varietas, petak.ton_takmar, " +
                        "if(length(no_kontrak) = 15, substring(no_kontrak,9,1) * 1, " +
                            "substring(no_kontrak,9,2) * 1) as afdeling, " +
                        "hk_bawah-hk_atas as selisih_hk, " +
                        "rend_bawah - rend_atas as selisih_rend, " +
                        "1 as kunci " +
                        "from tbl_analisa_kemasakan ankem " +
                        "join tbl_master_petak petak on ankem.kode_petak = petak.kode_petak " +
                        "join tbl_varietas varietas on petak.varietas = varietas.id_varietas) as t1 " +
                    "join " +
                    "(select tuser.nama_user, " +
                        "1 as kunci " +
                        "from tbl_user tuser where tuser.bagian = 'LTB' and tuser.role = 'ASKEP') as t2 " +
                    "on t1.kunci = t2.kunci " +
                    "where t1.rayon like ? and t1.tgl_analisa >= ? and t1.tgl_analisa <= ?" +
                    "order by t1.ronde, t1.rayon, t1.afdeling, t1.masa_tanam, t1.no_petak";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "TS%");
            ps.setDate(2, tglAwal);
            ps.setDate(3, tglAkhir);
            ResultSet rs = ps.executeQuery();
            JRDataSource jrds = new JRResultSetDataSource(rs);
            Map map = new HashMap();
            map.put("tgl_awal", tglAwal);
            map.put("tgl_akhir", tglAkhir);
            jp = JasperFillManager.fillReport(fileName, map, jrds);
        } catch (SQLException|JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing laporanPeriodeTS method!\nError code :\n" + ex.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }

    @Override
    public JasperPrint laporanHarianTr(Date tglAnalisa) {
        JasperPrint jp = null;
        BigDecimal bd = new BigDecimal(1);
        try (Connection conn = DB.getConn()){
            //InputStream fileName = getClass().getResourceAsStream("/reports/LaporanHarianTR.jasper");
            InputStream fileName = new URL("http://apps.bcn.web.id/reports/LaporanHarianTR.jasper").openStream();
            String sql =
                    "select * from " +
                    "(select ankem.*, petak.kategori, petak.luas_petak, " +
                        "petak.masa_tanam, petak.nama_kebun, petak.no_kontrak, petak.no_petak, " +
                        "petak.rayon, petak.varietas, varietas.nama_varietas, " +
                            "substring(rayon,3,2) as afdeling, " +
                        "hk_bawah-hk_atas as selisih_hk, " +
                        "rend_bawah - rend_atas as selisih_rend, " +
                        "1 as kunci " +
                        "from tbl_analisa_kemasakan ankem " +
                        "join tbl_master_petak petak on ankem.kode_petak = petak.kode_petak " +
                        "join tbl_varietas varietas on petak.varietas = varietas.id_varietas) as t1 " +
                    "join " +
                    "(select tuser.nama_user, " +
                        "1 as kunci " +
                        "from tbl_user tuser where tuser.bagian = 'LTB' and tuser.role = 'ASKEP') as t2 " +
                    "on t1.kunci = t2.kunci " +
                    "where t1.rayon like ? and t1.tgl_analisa = ? " +
                    "order by t1.ronde, t1.rayon, t1.afdeling, t1.masa_tanam, t1.no_petak";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "TR%");
            ps.setDate(2, tglAnalisa);
            ResultSet rs = ps.executeQuery();
            JRDataSource jrds = new JRResultSetDataSource(rs);
            Map map = new HashMap();
            map.put("tgl_awal", tglAnalisa);
            jp = JasperFillManager.fillReport(fileName, map, jrds);
        } catch (SQLException|JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing laporanHarianTR method!\nError code :\n" + ex.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }

    @Override
    public JasperPrint laporanPeriodeTr(Date tglAwal, Date tglAkhir) {
        JasperPrint jp = null;
        try (Connection conn = DB.getConn()){
            //InputStream fileName = getClass().getResourceAsStream("/reports/LaporanPeriodeTR.jasper");
            InputStream fileName = new URL("http://apps.bcn.web.id/reports/LaporanPeriodeTR.jasper").openStream();
            String sql =
                    "select * from " +
                    "(select ankem.*, petak.kategori, petak.luas_petak, " +
                        "petak.masa_tanam, petak.nama_kebun, petak.no_kontrak, petak.no_petak, " +
                        "petak.rayon, petak.varietas, varietas.nama_varietas, petak.ton_takmar, " +
                        "substring(rayon,3,2) as afdeling, " +
                        "hk_bawah-hk_atas as selisih_hk, " +
                        "rend_bawah - rend_atas as selisih_rend, " +
                        "1 as kunci " +
                        "from tbl_analisa_kemasakan ankem " +
                        "join tbl_master_petak petak on ankem.kode_petak = petak.kode_petak " +
                        "join tbl_varietas varietas on petak.varietas = varietas.id_varietas) as t1 " +
                    "join " +
                    "(select tuser.nama_user, " +
                        "1 as kunci " +
                        "from tbl_user tuser where tuser.bagian = 'LTB' and tuser.role = 'ASKEP') as t2 " +
                    "on t1.kunci = t2.kunci " +
                    "where t1.rayon like ? and t1.tgl_analisa >= ? and t1.tgl_analisa <= ?" +
                    "order by t1.ronde, t1.afdeling, t1.masa_tanam, t1.no_petak";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "TR%");
            ps.setDate(2, tglAwal);
            ps.setDate(3, tglAkhir);
            ResultSet rs = ps.executeQuery();
            JRDataSource jrds = new JRResultSetDataSource(rs);
            Map map = new HashMap();
            map.put("tgl_awal", tglAwal);
            map.put("tgl_akhir", tglAkhir);
            jp = JasperFillManager.fillReport(fileName, map, jrds);
        } catch (SQLException|JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing laporanPeriodeTR method!\nError code :\n" + ex.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }

    @Override
    public JasperPrint laporanHarianTsi(Date tglAnalisa) {
        JasperPrint jp = null;
        try (Connection conn = DB.getConn()){
            //InputStream fileName = getClass().getResourceAsStream("/reports/LaporanHarianTSI.jasper");
            InputStream fileName = new URL("http://apps.bcn.web.id/reports/LaporanHarianTSI.jasper").openStream();
            String sql =
                    "select * from " +
                    "(select ankem.*, petak.kategori, petak.luas_petak, " +
                        "petak.masa_tanam, petak.nama_kebun, petak.no_kontrak, petak.no_petak, " +
                        "petak.rayon, petak.varietas, varietas.nama_varietas, " +
                        "if(length(no_kontrak) = 15, substring(no_kontrak,9,1) * 1, " +
                            "substring(no_kontrak,9,2) * 1) as afdeling, " +
                        "hk_bawah-hk_atas as selisih_hk, " +
                        "rend_bawah - rend_atas as selisih_rend, " +
                        "1 as kunci " +
                        "from tbl_analisa_kemasakan ankem " +
                        "join tbl_master_petak petak on ankem.kode_petak = petak.kode_petak " +
                        "join tbl_varietas varietas on petak.varietas = varietas.id_varietas) as t1 " +
                    "join " +
                    "(select tuser.nama_user, " +
                        "1 as kunci " +
                        "from tbl_user tuser where tuser.bagian = 'LTB' and tuser.role = 'ASKEP') as t2 " +
                    "on t1.kunci = t2.kunci " +
                    "where (t1.rayon = 'BEKRI' or t1.rayon = 'TUBU') and t1.tgl_analisa = ? " +
                    "order by t1.ronde, t1.rayon, t1.afdeling, t1.masa_tanam, t1.no_petak";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, tglAnalisa);
            ResultSet rs = ps.executeQuery();
            JRDataSource jrds = new JRResultSetDataSource(rs);
            Map map = new HashMap();
            map.put("tgl_awal", tglAnalisa);
            jp = JasperFillManager.fillReport(fileName, map, jrds);
        } catch (SQLException|JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing laporanHarianTSI method!\nError code :\n" + ex.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }

    @Override
    public JasperPrint laporanPeriodeTsi(Date tglAwal, Date tglAkhir) {
        JasperPrint jp = null;
        try (Connection conn = DB.getConn()){
            //InputStream fileName = getClass().getResourceAsStream("/reports/LaporanPeriodeTSI.jasper");
            InputStream fileName = new URL("http://apps.bcn.web.id/reports/LaporanPeriodeTSI.jasper").openStream();
            String sql =
                    "select * from " +
                    "(select ankem.*, petak.kategori, petak.luas_petak, " +
                        "petak.masa_tanam, petak.nama_kebun, petak.no_kontrak, petak.no_petak, " +
                        "petak.rayon, petak.varietas, varietas.nama_varietas, petak.ton_takmar, " +
                        "if(length(no_kontrak) = 15, substring(no_kontrak,9,1) * 1, " +
                            "substring(no_kontrak,9,2) * 1) as afdeling, " +
                        "hk_bawah-hk_atas as selisih_hk, " +
                        "rend_bawah - rend_atas as selisih_rend, " +
                        "1 as kunci " +
                        "from tbl_analisa_kemasakan ankem " +
                        "join tbl_master_petak petak on ankem.kode_petak = petak.kode_petak " +
                        "join tbl_varietas varietas on petak.varietas = varietas.id_varietas) as t1 " +
                    "join " +
                    "(select tuser.nama_user, " +
                        "1 as kunci " +
                        "from tbl_user tuser where tuser.bagian = 'LTB' and tuser.role = 'ASKEP') as t2 " +
                    "on t1.kunci = t2.kunci " +
                    "where (t1.rayon = 'BEKRI' or t1.rayon = 'TUBU') and t1.tgl_analisa >= ? and t1.tgl_analisa <= ?" +
                    "order by t1.ronde, t1.rayon, t1.afdeling, t1.masa_tanam, t1.no_petak";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, tglAwal);
            ps.setDate(2, tglAkhir);
            ResultSet rs = ps.executeQuery();
            JRDataSource jrds = new JRResultSetDataSource(rs);
            Map map = new HashMap();
            map.put("tgl_awal", tglAwal);
            map.put("tgl_akhir", tglAkhir);
            jp = JasperFillManager.fillReport(fileName, map, jrds);
        } catch (SQLException|JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing laporanPeriodeTSI method!\nError code :\n" + ex.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }

    @Override
    public JasperPrint viewDataPetak(String kodePetak) {
        JasperPrint jp = null;
        try (Connection conn = DB.getConn()){
            //InputStream fileName = new URL("http://apps.bcn.web.id/reports/ViewPetakKebun.jasper").openStream();
            InputStream fileName = new URL(getReportsUrl("ViewPetakKebun.jasper")).openStream();
            Map map = new HashMap();
            map.put("kode_petak", kodePetak);
            jp = JasperFillManager.fillReport(fileName, map, conn);
        } catch (SQLException | MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing viewDataPetak method!\nError code :\n" + ex.toString());
        } catch (IOException | JRException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing viewDataPetak method!\nError code :\n" + ex.toString());
        }
        return jp;
    }

    @Override
    public JasperPrint viewDailyCS(Date tglTimbang) {
        JasperPrint jp = null;
        try {
            InputStream fileName = new URL(getReportsUrl("DailyReportCSDetail.jasper")).openStream();
            Map map = new HashMap();
            InputStream jsonSource = new URL("http://optanaman:optanaman@simpgbuma.ptpn7.com/index.php/apibuma/datacs/?tgl=" + tglTimbang).openStream();
            //InputStream jsonSource = new URL(getReportsUrl("14092019.json")).openStream();
            map.put(JsonQLQueryExecuterFactory.JSON_INPUT_STREAM,jsonSource);
            jp = JasperFillManager.fillReport(fileName, map);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing viewDailyCS method!\nError code :\n" + ex.toString());
        } catch (IOException | JRException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing viewDailyCS method!\nError code :\n" + ex.toString());
        }
        return jp;
    }

    @Override
    public String getReportsUrl(String fileName) {
        String result = "";
        try (Connection conn = DB.getConn()){
            String sql = "select url from tbl_redirect_file where file_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fileName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = rs.getString("url");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public JasperPrint viewCSToDate(Date tgl_1, Date tgl_2) {
        JasperPrint jp = null;
        try {
            InputStream fileName = new URL(getReportsUrl("CSDailyReport_todate.jasper")).openStream();
            Map map = new HashMap();
            InputStream jsonSource = new URL("http://optanaman:optanaman@simpgbuma.ptpn7.com/index.php/apibuma/datacs_sd/?tgl_1=" + 
                    tgl_1 + "&tgl_2=" + tgl_2).openStream();
            //InputStream jsonSource = new URL(getReportsUrl("14092019.json")).openStream();
            map.put(JsonQLQueryExecuterFactory.JSON_INPUT_STREAM,jsonSource);
            map.put("tgl_1", tgl_1);
            map.put("tgl_2", tgl_2);
            jp = JasperFillManager.fillReport(fileName, map);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing viewDailyCS method!\nError code :\n" + ex.toString());
        } catch (IOException | JRException ex) {
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
            alert.showErrorAlert("Error executing viewDailyCS method!\nError code :\n" + ex.toString());
        }
        return jp;
    }
    
}
