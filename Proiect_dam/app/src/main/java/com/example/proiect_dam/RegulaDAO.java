package com.example.proiect_dam;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RegulaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRegula(Regulibunafunct reg);

    @Delete
    public void deleteRegula(Regulibunafunct reg);

    @Query("SELECT * FROM regula")
    List<Regulibunafunct> getAll();


}
