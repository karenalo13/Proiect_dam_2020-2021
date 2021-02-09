package com.example.proiect_dam;

import java.io.Serializable;

public class Membru implements Serializable {

    private String nume;
    private String prenume;
    private String parola;
    private String email;

    public Membru(String nume, String prenume, String parola, String email) {
        this.nume = nume;
        this.prenume = prenume;
        this.parola = parola;
        this.email = email;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Membru{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", parola='" + parola + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
