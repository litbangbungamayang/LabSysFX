/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.model.PetakKebun;
import javafx.collections.ObservableList;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public interface PetakKebunDAO {
    
    public ObservableList<PetakKebun> getAllPetak();
    
}
