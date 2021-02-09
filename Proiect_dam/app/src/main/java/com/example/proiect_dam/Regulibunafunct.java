package com.example.proiect_dam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "regula")
public class Regulibunafunct {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nume;
    public String continut;

    public Regulibunafunct(String nume, String continut) {
        this.nume = nume;
        this.continut = continut;
    }
}
