package com.example.loren.eventtracker.tools.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.loren.eventtracker.R;
import com.example.loren.eventtracker.activities.AlarmActivity;
import com.example.loren.eventtracker.handler.MessageHandler;
import com.example.loren.eventtracker.tools.bt.MsgTooBigException;
import com.example.loren.eventtracker.utils.C;

import org.json.JSONObject;


public class MessageService extends Service {

    //private static MessageServiceHandler handler;
    public MessageService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

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


    public void showNotification() throws MsgTooBigException {

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

   /* public static MessageServiceHandler getHandler() {
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
                        try {
                            sendNotification();
                        } catch (MsgTooBigException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            if (obj instanceof JSONObject) {
                //TODO
            }
        }
    }*/
}
