package com.example.model;

public class Usuario {

    private int id;
    private String login;
    private String password;

    // Getters and setters
    
    @Override
    public String toString() {
        return "Usuario@" + Integer.toHexString(this.hashCode());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
