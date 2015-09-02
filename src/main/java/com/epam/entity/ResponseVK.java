package com.epam.entity;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 8/29/2015.
 */
public class ResponseVK implements Serializable {

    private String access_token;
    private String expires_in;
    private String user_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ResponseVK{" +
                "access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

}
