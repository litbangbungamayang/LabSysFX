/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Bayu Anandavi Muhardika
 */
public class FisikTebu {
    
    private final DoubleProperty panjang;
    private final IntegerProperty ruas;
    private final DoubleProperty diameter;
    
    public FisikTebu(){
        this(0.00, 0, 0.00);
    }
    
    public FisikTebu(Double panjang, int ruas, Double diameter){
        this.panjang = new SimpleDoubleProperty(panjang);
        this.ruas = new SimpleIntegerProperty(ruas);
        this.diameter = new SimpleDoubleProperty(diameter);
    }

    public Double getPanjang(){
        return panjang.get();
    }
    
    public DoubleProperty getPanjangProperty(){
        return panjang;
    }
    
    public void setPanjang(Double panjang){
        this.panjang.set(panjang);
    }
    
    public int getRuas(){
        return ruas.get();
    }
    
    public IntegerProperty getRuasProperty(){
        return ruas;
    }
    
    public void setRuas(int ruas){
        this.ruas.set(ruas);
    }
    
    public Double getDiameter(){
        return diameter.get();
    }
    
    public DoubleProperty getDiameterProperty(){
        return diameter;
    }
    
    public void setDiameter(Double diameter){
        this.diameter.set(diameter);
    }
    
}
