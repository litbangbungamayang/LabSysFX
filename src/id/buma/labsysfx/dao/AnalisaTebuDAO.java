/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.model.AnalisaTebu;
import id.buma.labsysfx.model.FisikTebu;
import javafx.collections.ObservableList;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public interface AnalisaTebuDAO {
    
    public int getRonde(String kodePetak);
    
    public boolean insertNewData(ObservableList<AnalisaTebu> dataAnalisa, 
            ObservableList<ObservableList<FisikTebu>> dataFisik);
    
    public Double getKp(String kodePetak, int rondeSekarang);
    
    public Double getKdt(String kodePetak, int rondeSekarang);
    
    public boolean cekDuplikat(String kodePetak, int rondeSekarang);
    
}
