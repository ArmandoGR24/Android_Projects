package com.codigomaestro.tienda;

public class Products {
    private String nombre;
    private String descripcion;
    private String url;
    private int imagen;

    public Products(String nombre, String descripcion, String url, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrl() {
        return url;
    }

    public int getImagen() {
        return imagen;
    }
}