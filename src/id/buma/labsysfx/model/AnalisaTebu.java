/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.model;

import java.sql.Date;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class AnalisaTebu {
    
    private String kodePetak;
    private int idUser;
    private int jenisAnalisa;
    private int ronde;
    private int noSampel;
    private Date tglAnalisa;
    private Date tglPosting;
    private Double beratTebuAtas;
    private Double beratTebuTengah;
    private Double beratTebuBawah;
    private Double beratNiraAtas;
    private Double beratNiraTengah;
    private Double beratNiraBawah;
    private Double penggerek;
    private Double brixAtas;
    private Double brixTengah;
    private Double brixBawah;
    private Double brixCampur;
    private Double polAtas;
    private Double polTengah;
    private Double polBawah;
    private Double polCampur;
    private Double brixBacaAtas;
    private Double brixBacaTengah;
    private Double brixBacaBawah;
    private Double brixBacaCampur;
    private Double polBacaAtas;
    private Double polBacaTengah;
    private Double polBacaBawah;
    private Double polBacaCampur;
    private Double faktorPerah;
    private Double hkAtas;
    private Double hkTengah;
    private Double hkBawah;
    private Double hkCampur;
    private Double nnAtas;
    private Double nnTengah;
    private Double nnBawah;
    private Double nnCampur;
    private Double rendAtas;
    private Double rendTengah;
    private Double rendBawah;
    private Double rendCampur;
    private Double fk;
    private Double kp;
    private Double kdt;

    public AnalisaTebu(String kodePetak, int idUser, int jenisAnalisa, int ronde, int noSampel, java.sql.Date tglAnalisa, java.sql.Date tglPosting,
            Double beratTebuAtas, Double beratTebuTengah, Double beratTebuBawah,
            Double beratNiraAtas, Double beratNiraTengah, Double beratNiraBawah,
            Double penggerek,
            Double brixBacaAtas, Double brixBacaTengah, Double brixBacaBawah, Double brixBacaCampur,
            Double brixAtas, Double brixTengah, Double brixBawah, Double brixCampur,
            Double polBacaAtas, Double polBacaTengah, Double polBacaBawah, Double polBacaCampur,
            Double polAtas, Double polTengah, Double polBawah, Double polCampur,
            Double faktorPerah,
            Double hkAtas, Double hkTengah, Double hkBawah, Double hkCampur,
            Double nnAtas, Double nnTengah, Double nnBawah, Double nnCampur,
            Double rendAtas, Double rendTengah, Double rendBawah, Double rendCampur,
            Double fk, Double kp, Double kdt){
        this.kodePetak = kodePetak;
        this.idUser = idUser;
        this.jenisAnalisa = jenisAnalisa;
        this.ronde = ronde;
        this.noSampel = noSampel;
        this.tglAnalisa = tglAnalisa;
        this.tglPosting = tglPosting;
        this.beratTebuAtas = beratTebuAtas;
        this.beratTebuTengah = beratTebuTengah;
        this.beratTebuBawah = beratTebuBawah;
        this.beratNiraAtas = beratNiraAtas;
        this.beratNiraTengah = beratNiraTengah;
        this.beratNiraBawah = beratNiraBawah;
        this.penggerek = penggerek;
        this.brixBacaAtas = brixBacaAtas;
        this.brixBacaTengah = brixBacaTengah;
        this.brixBacaBawah = brixBacaBawah;
        this.brixBacaCampur = brixBacaCampur;
        this.brixAtas = brixAtas;
        this.brixTengah = brixTengah;
        this.brixBawah = brixBawah;
        this.brixCampur = brixCampur;
        this.polBacaAtas = polBacaAtas;
        this.polBacaTengah = polBacaTengah;
        this.polBacaBawah = polBacaBawah;
        this.polBacaCampur = polBacaCampur;
        this.polAtas = polAtas;
        this.polTengah = polTengah;
        this.polBawah = polBawah;
        this.polCampur = polCampur;
        this.faktorPerah = faktorPerah;
        this.hkAtas = hkAtas;
        this.hkTengah = hkTengah;
        this.hkBawah = hkBawah;
        this.hkCampur = hkCampur;
        this.nnAtas = nnAtas;
        this.nnTengah = nnTengah;
        this.nnBawah = nnBawah;
        this.nnCampur = nnCampur;
        this.rendAtas = rendAtas;
        this.rendTengah = rendTengah;
        this.rendBawah = rendBawah;
        this.rendCampur = rendCampur;
        this.fk = fk;
        this.kp = kp;
        this.kdt = kdt;
    }
    
    public String getKodePetak() {
        return kodePetak;
    }

    public void setKodePetak(String kodePetak) {
        this.kodePetak = kodePetak;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getJenisAnalisa() {
        return jenisAnalisa;
    }

    public void setJenisAnalisa(int jenisAnalisa) {
        this.jenisAnalisa = jenisAnalisa;
    }

    public int getRonde() {
        return ronde;
    }

    public void setRonde(int ronde) {
        this.ronde = ronde;
    }

    public int getNoSampel() {
        return noSampel;
    }

    public void setNoSampel(int noSampel) {
        this.noSampel = noSampel;
    }

    public Date getTglAnalisa() {
        return tglAnalisa;
    }

    public void setTglAnalisa(Date tglAnalisa) {
        this.tglAnalisa = tglAnalisa;
    }

    public Date getTglPosting() {
        return tglPosting;
    }

    public void setTglPosting(Date tglPosting) {
        this.tglPosting = tglPosting;
    }

    public Double getBeratTebuAtas() {
        return beratTebuAtas;
    }

    public void setBeratTebuAtas(Double beratTebuAtas) {
        this.beratTebuAtas = beratTebuAtas;
    }

    public Double getBeratTebuTengah() {
        return beratTebuTengah;
    }

    public void setBeratTebuTengah(Double beratTebuTengah) {
        this.beratTebuTengah = beratTebuTengah;
    }

    public Double getBeratTebuBawah() {
        return beratTebuBawah;
    }

    public void setBeratTebuBawah(Double beratTebuBawah) {
        this.beratTebuBawah = beratTebuBawah;
    }

    public Double getBeratNiraAtas() {
        return beratNiraAtas;
    }

    public void setBeratNiraAtas(Double beratNiraAtas) {
        this.beratNiraAtas = beratNiraAtas;
    }

    public Double getBeratNiraTengah() {
        return beratNiraTengah;
    }

    public void setBeratNiraTengah(Double beratNiraTengah) {
        this.beratNiraTengah = beratNiraTengah;
    }

    public Double getBeratNiraBawah() {
        return beratNiraBawah;
    }

    public void setBeratNiraBawah(Double beratNiraBawah) {
        this.beratNiraBawah = beratNiraBawah;
    }

    public Double getPenggerek() {
        return penggerek;
    }

    public void setPenggerek(Double penggerek) {
        this.penggerek = penggerek;
    }

    public Double getBrixAtas() {
        return brixAtas;
    }

    public void setBrixAtas(Double brixAtas) {
        this.brixAtas = brixAtas;
    }

    public Double getBrixTengah() {
        return brixTengah;
    }

    public void setBrixTengah(Double brixTengah) {
        this.brixTengah = brixTengah;
    }

    public Double getBrixBawah() {
        return brixBawah;
    }

    public void setBrixBawah(Double brixBawah) {
        this.brixBawah = brixBawah;
    }

    public Double getBrixCampur() {
        return brixCampur;
    }

    public void setBrixCampur(Double brixCampur) {
        this.brixCampur = brixCampur;
    }

    public Double getPolAtas() {
        return polAtas;
    }

    public void setPolAtas(Double polAtas) {
        this.polAtas = polAtas;
    }

    public Double getPolTengah() {
        return polTengah;
    }

    public void setPolTengah(Double polTengah) {
        this.polTengah = polTengah;
    }

    public Double getPolBawah() {
        return polBawah;
    }

    public void setPolBawah(Double polBawah) {
        this.polBawah = polBawah;
    }

    public Double getPolCampur() {
        return polCampur;
    }

    public void setPolCampur(Double polCampur) {
        this.polCampur = polCampur;
    }

    public Double getBrixBacaAtas() {
        return brixBacaAtas;
    }

    public void setBrixBacaAtas(Double brixBacaAtas) {
        this.brixBacaAtas = brixBacaAtas;
    }

    public Double getBrixBacaTengah() {
        return brixBacaTengah;
    }

    public void setBrixBacaTengah(Double brixBacaTengah) {
        this.brixBacaTengah = brixBacaTengah;
    }

    public Double getBrixBacaBawah() {
        return brixBacaBawah;
    }

    public void setBrixBacaBawah(Double brixBacaBawah) {
        this.brixBacaBawah = brixBacaBawah;
    }

    public Double getBrixBacaCampur() {
        return brixBacaCampur;
    }

    public void setBrixBacaCampur(Double brixBacaCampur) {
        this.brixBacaCampur = brixBacaCampur;
    }

    public Double getPolBacaAtas() {
        return polBacaAtas;
    }

    public void setPolBacaAtas(Double polBacaAtas) {
        this.polBacaAtas = polBacaAtas;
    }

    public Double getPolBacaTengah() {
        return polBacaTengah;
    }

    public void setPolBacaTengah(Double polBacaTengah) {
        this.polBacaTengah = polBacaTengah;
    }

    public Double getPolBacaBawah() {
        return polBacaBawah;
    }

    public void setPolBacaBawah(Double polBacaBawah) {
        this.polBacaBawah = polBacaBawah;
    }

    public Double getPolBacaCampur() {
        return polBacaCampur;
    }

    public void setPolBacaCampur(Double polBacaCampur) {
        this.polBacaCampur = polBacaCampur;
    }

    public Double getFaktorPerah() {
        return faktorPerah;
    }

    public void setFaktorPerah(Double faktorPerah) {
        this.faktorPerah = faktorPerah;
    }

    public Double getHkAtas() {
        return hkAtas;
    }

    public void setHkAtas(Double hkAtas) {
        this.hkAtas = hkAtas;
    }

    public Double getHkTengah() {
        return hkTengah;
    }

    public void setHkTengah(Double hkTengah) {
        this.hkTengah = hkTengah;
    }

    public Double getHkBawah() {
        return hkBawah;
    }

    public void setHkBawah(Double hkBawah) {
        this.hkBawah = hkBawah;
    }

    public Double getHkCampur() {
        return hkCampur;
    }

    public void setHkCampur(Double hkCampur) {
        this.hkCampur = hkCampur;
    }

    public Double getNnAtas() {
        return nnAtas;
    }

    public void setNnAtas(Double nnAtas) {
        this.nnAtas = nnAtas;
    }

    public Double getNnTengah() {
        return nnTengah;
    }

    public void setNnTengah(Double nnTengah) {
        this.nnTengah = nnTengah;
    }

    public Double getNnBawah() {
        return nnBawah;
    }

    public void setNnBawah(Double nnBawah) {
        this.nnBawah = nnBawah;
    }

    public Double getNnCampur() {
        return nnCampur;
    }

    public void setNnCampur(Double nnCampur) {
        this.nnCampur = nnCampur;
    }

    public Double getRendAtas() {
        return rendAtas;
    }

    public void setRendAtas(Double rendAtas) {
        this.rendAtas = rendAtas;
    }

    public Double getRendTengah() {
        return rendTengah;
    }

    public void setRendTengah(Double rendTengah) {
        this.rendTengah = rendTengah;
    }

    public Double getRendBawah() {
        return rendBawah;
    }

    public void setRendBawah(Double rendBawah) {
        this.rendBawah = rendBawah;
    }

    public Double getRendCampur() {
        return rendCampur;
    }

    public void setRendCampur(Double rendCampur) {
        this.rendCampur = rendCampur;
    }

    public Double getFk() {
        return fk;
    }

    public void setFk(Double fk) {
        this.fk = fk;
    }

    public Double getKp() {
        return kp;
    }

    public void setKp(Double kp) {
        this.kp = kp;
    }

    public Double getKdt() {
        return kdt;
    }

    public void setKdt(Double kdt) {
        this.kdt = kdt;
    }
    
    
    
}
