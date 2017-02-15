package com.example.hari.notificationsample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.noti);



    }

    public void getnotification(View view){

        NotificationManager notificationmgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, resultpage.class);
        PendingIntent pintent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

     //   PendingIntent pintent = PendingIntent.getActivities(this,(int)System.currentTimeMillis(),intent, 0);


        Notification notif = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Hello Android Hari")
                .setContentText("Welcome to Notification Service")
                .setContentIntent(pintent)
                .build();


        notificationmgr.notify(0,notif);





    }


}
