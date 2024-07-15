/*package com.example.centrocultural.entities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface SalaDao {
    @Insert
    void insert(Sala sala);

    @Update
    void update(Sala sala);

    @Delete
    void delete(Sala sala);

    @Query("SELECT * FROM sala")
    List<Sala> getAllSalas();

    @Query("SELECT * FROM sala WHERE id = :id")
    Sala getSalaById(int id);
}
*/