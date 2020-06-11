package edu.upc.login.Entidades;

import androidx.annotation.NonNull;

public class Token {

    public String token;
    public int monedas;

    public Token(){}

    public Token(String token, int monedas) {
        this();
        this.token = token;
        this.monedas = monedas;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", monedas=" + monedas +
                '}';
    }
}
