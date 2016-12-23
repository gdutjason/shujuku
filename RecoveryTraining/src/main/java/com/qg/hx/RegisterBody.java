package com.qg.hx;

public class RegisterBody {
    public String username;
    public String password = "qgmobile";

    public RegisterBody(String phone, String type) {
        this.username = type + phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
