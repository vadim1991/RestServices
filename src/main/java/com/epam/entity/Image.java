package com.epam.entity;

import java.io.Serializable;

/**
 * Created by Vadym_Vlasenko on 8/20/2015.
 */
public class Image implements Serializable {

    private String imageTitle;
    private String imageURL;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageTitle='" + imageTitle + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

}
