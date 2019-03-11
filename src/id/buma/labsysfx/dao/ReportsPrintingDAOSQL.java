/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.database.DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

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
    
}
