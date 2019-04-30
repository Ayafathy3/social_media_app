package com.example.android.socialmedia.classes;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import com.example.android.socialmedia.Activity.New;
import com.example.android.socialmedia.R;

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;

    public MyNewIntentService() {

        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent( Intent intent ) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("your target");
        builder.setContentText("to achieve your target please click on notification ");
        builder.setSmallIcon(R.drawable.picture);
        Intent notifyIntent = new Intent(this, New.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //to be able to launch your activity from the notification
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}
