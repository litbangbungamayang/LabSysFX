/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.model.HasilAnalisaHarianCS;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */
public interface LabCSDAO {
    
    public HasilAnalisaHarianCS getDataCS(String tglAnalisa);
    
    public Double getHablurEfektif(Double faktor, String tglTimbang);
    
    public boolean setFaktorEfektif(Double faktor, String tglTimbang);
    
    public boolean cekFaktorEfektif(String tglTimbang);
    
}
