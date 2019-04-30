package com.example.android.socialmedia.classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.android.socialmedia.classes.MyNewIntentService;

public class NotificationReciever extends BroadcastReceiver {

    public NotificationReciever() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, MyNewIntentService.class);
        context.startService(intent1);
    }
}