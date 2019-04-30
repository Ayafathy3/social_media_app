package com.example.android.socialmedia.classes;


import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class ChatId {


    @Exclude
    public String BlogPostId;
    public <T extends ChatId> T withId(@NonNull final String id) {
        this.BlogPostId = id;
        return (T) this;
    }


}
