/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import id.buma.labsysfx.model.UpdateFile;
import id.buma.labsysfx.model.UserLab;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public interface AdminPageDAO {
    
    public UserLab getLogin(String username, String password);
    
    public UpdateFile getUpdatePath();
    
    
}
