package com.example.loren.eventtracker.tools.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.loren.eventtracker.R;
import com.example.loren.eventtracker.activities.AlarmActivity;
import com.example.loren.eventtracker.tools.bt.BluetoothConnectionManager;
import com.example.loren.eventtracker.tools.bt.MsgTooBigException;
import com.example.loren.eventtracker.utils.C;

import org.json.JSONObject;


public class MessageService extends IntentService {

    private static MessageServiceHandler handler;
    public MessageService() {
        super("MessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service", "partito");
        handler = new MessageServiceHandler();
        try {
            sendNotification();
        } catch (MsgTooBigException e) {
            e.printStackTrace();
        }

        while(true){
        }
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "distrutto");
    }


    public void sendNotification() throws MsgTooBigException {
        /*NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle(getString(R.string.notificationTitle))
                        .setContentText(getString(R.string.ask))
                        .addAction(R.drawable.ic_stat_name, getString(R.string.positive), PendingIntent.getService(this, (int) System.currentTimeMillis(), new Intent(this, SendMessage.class), 0))
                        .addAction(R.drawable.ic_stat_name, getString(R.string.negative), null);
        final int NOTIFICATION_ID = 12345;*/
        /*Intent targetIntent = new Intent(this, null);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);*/
        //BluetoothConnectionManager.getInstance().sendMsg(C.POSITIVE_ALARM_RESPONSE)
        /*NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(NOTIFICATION_ID, builder.build());*/
        //Yes intent

        final int NOTIFICATION_ID = 1234;
        PendingIntent  tapPendingIntent = PendingIntent.getActivity(this , 0,
                new  Intent(this , AlarmActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new  NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("File  Downloaded")
                .setContentText("The  requested  file  has  been  downloaded")
                /*.addAction(R.drawable.ic_stat_name, getString(R.string.positive), PendingIntent.getService(this, (int) System.currentTimeMillis(), new Intent(this, SendMessage.class), 0))
                .addAction(R.drawable.ic_stat_name, getString(R.string.negative), null)*/
                .setAutoCancel(true)
                .setContentIntent(tapPendingIntent)
                .build();
        NotificationManager  notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID , notification);

    }

    public static MessageServiceHandler getHandler() {
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
}
