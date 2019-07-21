/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public interface ReportsPrintingDAO {
    
    public JasperPrint cetakTes(String kodePetak);
    
    public JasperPrint laporanHarianTs(java.sql.Date tglAnalisa);
    
    public JasperPrint laporanHarianTr(java.sql.Date tglAnalisa);
    
    public JasperPrint laporanHarianTsi(java.sql.Date tglAnalisa);
    
    public JasperPrint laporanPeriodeTs(java.sql.Date tglAwal, java.sql.Date tglAkhir);
    
    public JasperPrint laporanPeriodeTr(java.sql.Date tglAwal, java.sql.Date tglAkhir);
    
    public JasperPrint laporanPeriodeTsi(java.sql.Date tglAwal, java.sql.Date tglAkhir);
    
    public JasperPrint viewDataPetak(String kodePetak);
    
}
