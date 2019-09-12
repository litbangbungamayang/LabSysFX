/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.model;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class HasilAnalisaHarianCS {
    
    public Double hablur_analisa;
    public Double ton_tebu;
    public Double rend;
    public Double hablur_efektif;

    public Double getHablur_analisa() {
        return hablur_analisa;
    }

    public void setHablur_analisa(Double hablur_analisa) {
        this.hablur_analisa = hablur_analisa;
    }

    public Double getTon_tebu() {
        return ton_tebu;
    }

    public void setTon_tebu(Double ton_tebu) {
        this.ton_tebu = ton_tebu;
    }

    public Double getRend() {
        return rend;
    }

    public void setRend(Double rend) {
        this.rend = rend;
    }
    
    @Override
    public String toString(){
        return String.valueOf(rend);
    }

    public Double getHablur_efektif() {
        return hablur_efektif;
    }

    public void setHablur_efektif(Double hablur_efektif) {
        this.hablur_efektif = hablur_efektif;
    }
    
}
