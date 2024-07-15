/*package com.example.centrocultural.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "pintura",
        foreignKeys = {
                @ForeignKey(entity = Autor.class,
                        parentColumns = "id",
                        childColumns = "autorId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Sala.class,
                        parentColumns = "id",
                        childColumns = "salaId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index(value = "autorId"),
                @Index(value = "salaId")
        })
public class Pintura {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "titulo")
    public String titulo;

    @ColumnInfo(name = "autorId")
    public int autorId;

    @ColumnInfo(name = "salaId")
    public int salaId;

    @ColumnInfo(name = "tecnica")
    public String tecnica;

    @ColumnInfo(name = "categoria")
    public String categoria;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "anio")
    public String anio;

    @ColumnInfo(name = "enlace")
    public String enlace;

    //Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getAutorId() { return autorId; }
    public void setAutorId(int autorId) { this.autorId = autorId; }
    public int getSalaId() { return salaId; }
    public void setSalaId(int salaId) { this.salaId = salaId; }
    public String getTecnica() { return tecnica; }
    public void setTecnica(String tecnica) { this.tecnica = tecnica; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getAnio() { return anio; }
    public void setAnio(String anio) { this.anio = anio; }
    public String getEnlace() { return enlace; }
    public void setEnlace(String enlace) { this.enlace = enlace; }
}*/
