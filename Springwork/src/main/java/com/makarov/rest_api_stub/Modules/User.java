package com.makarov.rest_api_stub.Modules;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class User {
    @NotNull(message = "Login cannot be null")
    @NotEmpty(message = "Login cannot be empty")
    private String login;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String data;

    public User () {}
    public User (String login, String password) {
        this.login = login;
        this.password = password;
        this.data = String.valueOf(LocalDateTime.now());
    }
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
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}