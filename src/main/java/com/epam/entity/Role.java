package com.epam.entity;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 8/30/2015.
 */
public class Role implements Serializable {

    private String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }

}
