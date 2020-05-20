package edu.upc.login;

public class RegisterCredentials {

    public String nombre;
    public String mail;
    public String password;
    public String confirmPassword;

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterCredentials(){}

    public RegisterCredentials(String nombre, String mail, String password,String confirmPassword) {
        this();
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
        this.confirmPassword=confirmPassword;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) { this.mail = mail; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword; }
}
