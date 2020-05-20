package edu.upc.login;

public class RegisterCredentials {

    private String nombre;
    private String mail;
    private String password;

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public RegisterCredentials(){}

    public RegisterCredentials(String nombre, String mail, String password) {
        this();
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) { this.mail = mail; }

    public void setPassword(String password) {
        this.password = password;
    }





}
