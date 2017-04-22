package com.hathome.adapter;

/**
 * Created by Acer on 17/4/2560.
 */
public class User {
    private String email;
    private String address;
    private Integer id;

    public User() {
    }

    public User(Integer id, String email, String address) {
        this.email = email;
        this.address = address;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
