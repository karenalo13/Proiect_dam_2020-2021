package com.example.proiect_dam;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sfat")
public class Sfaturi {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nume;
    public String continut;

    public Sfaturi(String nume, String continut) {
        this.nume = nume;
        this.continut = continut;
    }
}
