package edu.upc.login.Entidades;

import java.io.Serializable;

public class Partida implements Serializable {

    private int puntos;
    private String duracion;
    private int nivelMax;

    public Partida(){};

    public Partida(int puntos,String duracion,int nivelMax){
        this.puntos=puntos;
        this.duracion=duracion;
        this.nivelMax=nivelMax;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getNivelMax() {
        return nivelMax;
    }

    public void setNivelMax(int nivelMax) {
        this.nivelMax = nivelMax;
    }
}
