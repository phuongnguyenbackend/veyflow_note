package com.veydrys.first_project;

import org.springframework.data.annotation.Id;

public class LoginInfo {
    private String username;
    private String password;

    public String GetUsername(){
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
