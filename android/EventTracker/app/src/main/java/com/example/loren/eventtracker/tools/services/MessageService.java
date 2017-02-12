package com.example.loren.eventtracker.tools.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;

import com.example.loren.eventtracker.R;
import com.example.loren.eventtracker.activities.MainActivity;
import com.example.loren.eventtracker.utils.C;

import org.json.JSONObject;

import static android.support.v4.app.NotificationCompat.*;

public class MessageService extends IntentService {
    private static MessageService.MessageServiceHandler handler;

    public MessageService() {
        super("MessageService");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onHandleIntent(Intent intent) {
        handler = new MessageServiceHandler();
        Log.d("Service", "partito");
        //getnotification();
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(getString(R.string.notificationTitle))
                        .setContentText(getString(R.string.ask))
                        .addAction(R.drawable.ic_stat_name, getString(R.string.positive), null)
                        .addAction(R.drawable.ic_stat_name, getString(R.string.negative), null);
        int NOTIFICATION_ID = 12345;
        Intent targetIntent = new Intent(this, resultpage.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());
        while(true){
        }
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "distrutto");
    }

    public static MessageService.MessageServiceHandler getHandler() {
        return handler;
    }

    public class MessageServiceHandler extends Handler {

        MessageServiceHandler() {

        }

        public void handleMessage(Message msg) {

            Object obj = msg.obj;

            if (obj instanceof String) {
                String message = obj.toString();
                Log.d("RecivedMsg", message);

                // a seconda del messaggio ricevuto da arduino:
                switch (message) {
                    case C.PRESENCE_MSG:

                        break;
                }
            }

            if (obj instanceof JSONObject) {
                //TODO
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void getnotification(/*View view*/){
        Log.d("Service", "dentro");
        NotificationManager notificationmgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, null);
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
