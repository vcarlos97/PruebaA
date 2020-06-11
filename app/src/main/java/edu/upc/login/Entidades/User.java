package edu.upc.login.Entidades;

public class User {

    public String idUser;
    public String nombre;
    public String mail;
    public String password;
    public String status;

    public User(){}

    public User(String id, String nombre, String mail, String password, String status) {
        this();
        this.idUser = id;
        this.nombre = nombre;
        this.mail = mail;
        this.password = password;
        this.status = status;
    }


    public String getId() {
        return idUser;
    }

    public void setId(String id) {
        this.idUser = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id="+idUser+", name=" + nombre + ", mail=" + mail +"]";
    }

}