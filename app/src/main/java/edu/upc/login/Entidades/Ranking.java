package edu.upc.login.Entidades;

import java.io.Serializable;

public class Ranking implements Serializable {

    private String username;
    private String puntos;

    public Ranking(){}

    public Ranking ( String username, String puntos){
        this();
        this.username=username;
        this.puntos=puntos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
