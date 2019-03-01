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
public class PetakKebun {
   
    private String kodePetak;
    private String noKontrak;
    private String namaKebun;
    private String noPetak;
    private Double luasPetak;
    private String masaTanam;
    private String varietas;
    private String kategori;
    private String rayon;
    
    public PetakKebun(String kodePetak, String noKontrak, String namaKebun, 
            String noPetak, Double luasPetak, String masaTanam, String varietas, 
            String kategori, String rayon){
        this.kodePetak = kodePetak;
        this.noKontrak = noKontrak;
        this.namaKebun = namaKebun;
        this.noPetak = noPetak;
        this.luasPetak = luasPetak;
        this.masaTanam = masaTanam;
        this.varietas = varietas;
        this.kategori = kategori;
        this.rayon = rayon;
    }

    public String getKodePetak() {
        return kodePetak;
    }

    public void setKodePetak(String kodePetak) {
        this.kodePetak = kodePetak;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public String getNamaKebun() {
        return namaKebun;
    }

    public void setNamaKebun(String namaKebun) {
        this.namaKebun = namaKebun;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public Double getLuasPetak() {
        return luasPetak;
    }

    public void setLuasPetak(Double luasPetak) {
        this.luasPetak = luasPetak;
    }

    public String getMasaTanam() {
        return masaTanam;
    }

    public void setMasaTanam(String masaTanam) {
        this.masaTanam = masaTanam;
    }

    public String getVarietas() {
        return varietas;
    }

    public void setVarietas(String varietas) {
        this.varietas = varietas;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }
    
    
    
}
