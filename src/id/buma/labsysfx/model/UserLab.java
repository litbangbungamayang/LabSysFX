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

public class UserLab {
    
    private String namaUser;
    private String loginName;
    private String loginPass;
    private String bagian;
    private String role;
    private int idUser;
    
    public UserLab(int idUser, String namaUser, String loginName, String loginPass, 
            String bagian, String role){
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.loginName = loginName;
        this.loginPass = loginPass;
        this.bagian = bagian;
        this.role = role;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPass() {
        return loginPass;
    }

    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
