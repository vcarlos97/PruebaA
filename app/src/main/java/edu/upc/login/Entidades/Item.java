package edu.upc.login.Entidades;

import java.io.Serializable;

public class Item implements Serializable {
    private int idObjeto;
    private String nombre;
    private String descripcion;
    private int precio;
    private int imagenid;

    public Item (){}

    public Item(int idObjeto, String nombre, String descripcion, int precio, int imagenid) {
        this();
        this.idObjeto = idObjeto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenid = imagenid;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
