package com.example.proiect_dam;

import java.util.Calendar;
import java.util.Date;

public class Item {
    private String titlu;
    private String descriere;
    private int pret;
    private String mImageUrl;
    private String data ;
    private String utilizator;
    private String castigator;
    private boolean castigat;
    private String nume_poza;

    public Item(){

    }

    public Item(String titlu, String descriere, int pret ,String mImageUrl, String c ,String utilizator,String castigator,boolean castigat,String nume_poza) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.pret = pret;
        this.mImageUrl=mImageUrl;
        this.data=c;
        this.utilizator=utilizator;
        this.castigator=castigator;
        this.castigat=castigat;
        this.nume_poza=nume_poza;

    }

    public Item(String titlu, String descriere, int pret ,String mImageUrl, String c ,String utilizator,String nume_poza) {
        this.titlu = titlu;
        this.descriere = descriere;
        this.pret = pret;
        this.mImageUrl=mImageUrl;
        this.data=c;
        this.utilizator=utilizator;

        this.nume_poza=nume_poza;


    }

    public String getNume_poza() {
        return nume_poza;
    }

    public void setNume_poza(String nume_poza) {
        this.nume_poza = nume_poza;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
    }

    public String getCastigator() {
        return castigator;
    }

    public void setCastigator(String castigator) {
        this.castigator = castigator;
    }

    public boolean isCastigat() {
        return castigat;
    }

    public void setCastigat(boolean castigat) {
        this.castigat = castigat;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("titlu=").append(titlu).append(' ');
        sb.append("\n descriere:\n").append(descriere).append(' ');
        sb.append("\n Pretul este de ").append(pret).append(' ');
        sb.append("lei");
        sb.append("\n Daca doriti sa licitati , contact: ").append(utilizator).append(' ');
        return sb.toString();
    }
}
