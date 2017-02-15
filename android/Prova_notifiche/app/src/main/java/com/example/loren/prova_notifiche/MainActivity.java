package com.example.loren.prova_notifiche;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // prepare intent which is triggered if the
// notification is selected

    Intent intent = new Intent(this, NotificationReceiver.class);
    // use System.currentTimeMillis() to have a unique ID for the pending intent
    PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

    // build notification
// the addAction re-use the same intent to keep the example short
    Notification n  = new Notification.Builder(this)
            .setContentTitle("New mail from " + "test@gmail.com")
            .setContentText("Subject")
            .setSmallIcon(R.drawable.icon)
            .setContentIntent(pIntent)
            .setAutoCancel(true)
            .addAction(R.drawable.icon, "Call", pIntent)
            .addAction(R.drawable.icon, "More", pIntent)
            .addAction(R.drawable.icon, "And more", pIntent).build();


    NotificationManager notificationManager =
            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    notificationManager.notify(0, n);
}
