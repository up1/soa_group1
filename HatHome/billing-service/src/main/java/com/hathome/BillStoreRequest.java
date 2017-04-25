package com.hathome;

/**
 * Created by Acer on 23/4/2560.
 */
public class BillStoreRequest {
    private long user_id;
    private String username;
    private String address;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
