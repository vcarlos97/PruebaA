package edu.upc.login.Entidades;

import java.sql.Date;

public class Foro {

    public int idComment;
    public String nombre;
    public String comentario;
    public Date fecha;

    public Foro(){}

    public Foro(int idComment, String nombre, String comentario, Date fecha) {
        this();
        this.idComment = idComment;
        this.nombre = nombre;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Foro{" +
                "idComment=" + idComment +
                ", username='" + nombre + '\'' +
                ", comentario='" + comentario + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
