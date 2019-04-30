package com.example.android.socialmedia.classes;

/**
 * Created by Dell on 3/28/2018.
 */

public class Users {
    private String username;
    private String userImage;
    private String userField;
    private String userId;


    public Users(String username, String userImage, String userField, String userId) {
        this.username = username;
        this.userImage = userImage;
        this.userField = userField;
        this.userId = userId;

    }
public Users(){

}
    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserField() {
        return userField;
    }

    public void setUserField(String userField) {
        this.userField = userField;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Users(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
