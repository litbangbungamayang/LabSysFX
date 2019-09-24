/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import id.buma.labsysfx.controller.AnalisaKemasakanController;
import id.buma.labsysfx.model.HasilAnalisaHarianCS;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class LabCSDAOSQL implements LabCSDAO{

    @Override
    public HasilAnalisaHarianCS getDataCS(String tglAnalisa) {
        HasilAnalisaHarianCS hasil = null;
        try {
                Gson gson = new GsonBuilder().create();
                URL url = new URL("http://optanaman:optanaman@simpgbuma.ptpn7.com/index.php/apibuma/get_rend_cs/?tgl=" + tglAnalisa);
                URLConnection request = url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                if (!root.isJsonArray()){
                    hasil = gson.fromJson(root, HasilAnalisaHarianCS.class);
                }
        } catch (MalformedURLException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    @Override
    public Double getHablurEfektif(Double faktor, String tglTimbang) {
        Double hasil = 0.0;
        try {
                Gson gson = new GsonBuilder().create();
                URL url = new URL("http://optanaman:optanaman@simpgbuma.ptpn7.com/index.php/apibuma/get_hablur_koreksi/?tgl=" + tglTimbang + 
                        "&faktor=" + String.valueOf(faktor));
                URLConnection request = url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                if (!root.isJsonArray()){
                    HasilAnalisaHarianCS hasilCs = gson.fromJson(root, HasilAnalisaHarianCS.class);
                    hasil = hasilCs.getHablur_efektif();
                }
        } catch (MalformedURLException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    @Override
    public boolean setFaktorEfektif(Double faktor, String tglTimbang) {
        try {
                Gson gson = new GsonBuilder().create();
                URL url = new URL("http://optanaman:optanaman@simpgbuma.ptpn7.com/index.php/apibuma/insert_faktor/?tglTimbang=" + tglTimbang + 
                        "&faktor=" + String.valueOf(faktor));
                URLConnection request = url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                System.out.println(gson.fromJson(root, String.class));
                if (gson.fromJson(root, String.class).equals("true")) return true;
        } catch (MalformedURLException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean cekFaktorEfektif(String tglTimbang) {
        try {
                Gson gson = new GsonBuilder().create();
                URL url = new URL("http://optanaman:optanaman@simpgbuma.ptpn7.com/index.php/apibuma/cek_faktor/?tglTimbang=" + tglTimbang );
                URLConnection request = url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                System.out.println(gson.fromJson(root, String.class));
                if (gson.fromJson(root, Integer.class) == 0) return true;
        } catch (MalformedURLException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AnalisaKemasakanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
