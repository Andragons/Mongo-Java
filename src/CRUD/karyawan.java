/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Rama
 */
public class karyawan {
    public String username = "admin";
    public String password = "21232f297a57a5a743894a0e4a801fc3";
    private Object id;
    private int nik;
    private String nama;
    private String jk;
    private int usia;
    private String alamat; 
    private int gaji; 
    
    public String enkripsi(String data) throws NoSuchAlgorithmException{
        String hashed = "";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data.getBytes());
        byte[] byteData = md5.digest();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<byteData.length; i++){
            sb.append(Integer.toString((byteData[i] & 0xff)+ 0x100,16).substring(1)) ;
        }
        hashed = sb.toString();
        
        return hashed;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
    
    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public int getGaji() {
        return gaji;
    }

    public void setGaji(int gaji) {
        this.gaji = gaji;
    }
     
}
