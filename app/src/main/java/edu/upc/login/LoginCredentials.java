package edu.upc.login;

public class LoginCredentials {

    private String nombre;
    private  String password;

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public LoginCredentials(){

    }

    public LoginCredentials(String nombre, String password){

        this.nombre=nombre;
        this.password=password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
