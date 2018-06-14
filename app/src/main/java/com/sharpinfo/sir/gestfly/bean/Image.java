package com.sharpinfo.sir.gestfly.bean;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("ID")
    private int id;

    @SerializedName("title")
    private String Title;

    @SerializedName("image")
    private String Image;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }

    public Image() {
    }

    public Image(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}
