package com.makarov.rest_api_stub.Modules;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class User {
    @NotNull(message = "Login cannot be null")
    @NotEmpty(message = "Login cannot be empty")
    private String login;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String email;
    private Timestamp date;

    public User () {}
    public User(String login, String password, String email, Timestamp date) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
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
    public String getEmail()  {

        return email;
    }
    public void setEmail(String email)  {

        this.email = email;
    }

    public Timestamp getDate()  {

        return date;
    }
    public void setDate(Timestamp date)  {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"login\": \"" + login + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"date\": \"" + date + "\"\n" +
                "}";
    }
}