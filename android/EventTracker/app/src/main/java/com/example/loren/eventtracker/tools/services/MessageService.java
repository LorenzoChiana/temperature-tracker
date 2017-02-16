package com.example.loren.eventtracker.tools.services;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.example.loren.eventtracker.R;
import com.example.loren.eventtracker.activities.AlarmActivity;
import com.example.loren.eventtracker.handler.MessageHandler;


public class MessageService extends Service {
    public MessageService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //cambio del contesto dell'handler
        MessageHandler.getHandler().setServiceContext(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //generazione della notifica
    public void showNotification() {

        final int NOTIFICATION_ID = 1234;
        PendingIntent  tapPendingIntent = PendingIntent.getActivity(this , 0,
                new  Intent(this , AlarmActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new  NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(getString(R.string.notificationTitle))
                .setContentText(getString(R.string.notificationBody))
                .setAutoCancel(true)
                .setContentIntent(tapPendingIntent)
                .build();
        NotificationManager  notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID , notification);

    }
}
