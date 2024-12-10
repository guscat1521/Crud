package com.example.interfaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cancion {
    private int id; // Atributo para almacenar el ID de la canción
    private final StringProperty titulo;
    private final StringProperty autor;

    // Constructor que incluye el ID
    public Cancion(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
    }

    // Constructor sin ID (para nuevas canciones que aún no están en la base de datos)
    public Cancion(String titulo, String autor) {
        this(0, titulo, autor); // Por defecto, el ID será 0
    }

    // Métodos getter y setter para el ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Métodos getter y setter para el título
    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    // Métodos getter y setter para el autor
    public String getAutor() {
        return autor.get();
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public StringProperty autorProperty() {
        return autor;
    }
}

