package com.example.android.socialmedia.classes;


public class Data {
    private String url;
    private int image;
    private String address, describtion;

    public Data(String url, int image, String address, String describtion) {
        this.url = url;
        this.image = image;
        this.describtion = describtion;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }
}
