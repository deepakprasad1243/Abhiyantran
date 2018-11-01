package com.example.joydeephalder.abhiyantran.Modal;

public class User {

    private String name;
    private String email;
    private String mobile;
    private String pin;


    public User(String name, String email, String mobile, String pin) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }


    public String getMobile() {
        return mobile;
    }

    public String getpin() {
        return pin;
    }

}
