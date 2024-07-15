/*package com.example.centrocultural.entities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface AutorDao {
    @Insert
    void insert(Autor autor);
    @Update
    void update(Autor autor);
    @Delete
    void delete(Autor autor);
    @Query("SELECT * FROM autor")
    List<Autor> getAllAutores();
    @Query("SELECT * FROM autor WHERE id = :id")
    Autor getAutorById(int id);
}
*/