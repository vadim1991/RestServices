package com.epam.entity;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 9/17/2015.
 */
public class VKProfileBean implements Serializable {

    private String uid;
    private String first_name;
    private String last_name;
    private int sex;
    private String nickname;
    private String screen_name;
    private String bdate;
    private int city;
    private int country;
    private int timezone;
    private String photo;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VKProfileBean that = (VKProfileBean) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        return !(screen_name != null ? !screen_name.equals(that.screen_name) : that.screen_name != null);

    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (screen_name != null ? screen_name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VKProfileBean{" +
                "uid='" + uid + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", sex=" + sex +
                ", nickname='" + nickname + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", bdate='" + bdate + '\'' +
                ", city=" + city +
                ", country=" + country +
                ", timezone=" + timezone +
                ", photo='" + photo + '\'' +
                '}';
    }

}
