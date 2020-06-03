package edu.upc.login;

public class Ranking {

    public String username;
    public  int puntos;


    public Ranking (String username, int puntos){
        this.username=username;
        this.puntos=puntos;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }


}
