/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.database.DB;
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
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBaseQuery;

/**
 *
 * @author Bayu Anandavi Muhardik
 * 
 */

public class ReportsPrintingDAOSQL implements ReportsPrintingDAO{

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
            String fileName = "./reports/LaporanHarianTS.jasper";
            String sql =
                    "select * from " +
                    "(select ankem.*, petak.kategori, petak.luas_petak, " +
                        "petak.masa_tanam, petak.nama_kebun, petak.no_kontrak, petak.no_petak, " +
                        "petak.rayon, petak.varietas, varietas.nama_varietas, " +
                        "if(rayon = 'TS 1' || rayon = 'TS 2', substring(no_kontrak,9,1), " +
                            "substring(no_kontrak,9,2)) as afdeling, " +
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
                    "where t1.tgl_analisa = ? " +
                    "order by t1.ronde, t1.afdeling, t1.no_petak";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, tglAnalisa);
            ResultSet rs = ps.executeQuery();
            JRDataSource jrds = new JRResultSetDataSource(rs);
            /*
            Map map = new HashMap();
            map.put("tgl_awal", tglAnalisa);
            */
            Map map = new HashMap();
            //map.put("tgl_awal", tglAnalisa);
            jp = JasperFillManager.fillReport(fileName, map, jrds);
        } catch (SQLException | JRException ex){
            Logger.getLogger(ReportsPrintingDAOSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jp;
    }
    
}
