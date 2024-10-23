package com.makarov.rest_api_stub;

public class UserCredentials {
    private String login;
    private String password;

    //метод, который возвращает login
    public String getLogin() {
        return login;
    }
    //метод, который устанавливает значения login
    public void setLogin(String login) {
        this.login = login;
    }

    //метод, который возвращает password
    public String getPassword() {
        return password;
    }
    //метод, который устанавливает значения password
    public void setPassword(String password) {
        this.password = password;
    }
}

