package com.epam.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
@Document
public class User implements Serializable {

    @Id
    private String id;
    private String userName;
    private int age;
    private Set<Role> roles;
    private GoogleProfileBean googleProfileBean;
    private VKUserBean vkUserBean;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public GoogleProfileBean getGoogleProfileBean() {
        return googleProfileBean;
    }

    public void setGoogleProfileBean(GoogleProfileBean googleProfileBean) {
        this.googleProfileBean = googleProfileBean;
    }

    public VKUserBean getVkUserBean() {
        return vkUserBean;
    }

    public void setVkUserBean(VKUserBean vkUserBean) {
        this.vkUserBean = vkUserBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        return !(userName != null ? !userName.equals(user.userName) : user.userName != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                ", googleProfileBean=" + googleProfileBean +
                ", vkUserBean=" + vkUserBean +
                '}';
    }

}
