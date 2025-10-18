package com.example.alanmoviesapp;

import java.io.Serializable;

public class Pelicula implements Serializable {
    private String nombre;
    private boolean vista;

    // Constructor
    public Pelicula(String nombre, boolean vista) {
        this.nombre = nombre;
        this.vista = vista;
    }

    // Getters y Setters (necesarios para acceder a los datos)
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isVista() { return vista; }
    public void setVista(boolean vista) { this.vista = vista; }
}