package com.example.proiect_dam;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.ArrayList;

@Database(entities = {Sfaturi.class,Regulibunafunct.class},version = 1)
public abstract class ReguliDB extends RoomDatabase {
    public abstract RegulaDAO regulibunafunct();
    public abstract SfaturiDAO sfaturi();

}
