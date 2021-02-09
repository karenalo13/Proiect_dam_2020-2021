package com.example.proiect_dam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SfaturiDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSfat(Sfaturi sf);

    @Delete
    public void deleteSfat(Sfaturi sf);

    @Query("SELECT * FROM SFAT")
    List<Sfaturi> getAll();

}
