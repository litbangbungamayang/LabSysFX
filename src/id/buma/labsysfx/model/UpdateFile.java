/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.buma.labsysfx.model;

import org.joda.time.DateTime;

/**
 *
 * @author Bayu Anandavi Muhardika
 * 
 */

public class UpdateFile {
    
    private String fileName;
    private String version;
    private DateTime dateVersion;
    private String urgency;
    private String checksum;
    
    public UpdateFile(String fileName, String version, DateTime dateVersion, String urgency,
            String checksum){
        this.fileName = fileName;
        this.version = version;
        this.dateVersion = dateVersion;
        this.urgency = urgency;
        this.checksum = checksum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public DateTime getDateVersion() {
        return dateVersion;
    }

    public void setDateVersion(DateTime dateVersion) {
        this.dateVersion = dateVersion;
    }
    
}
