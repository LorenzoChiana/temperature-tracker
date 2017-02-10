package com.example.loren.eventtracker.tools.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MessageService extends IntentService {

    public MessageService() {
        super("MessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service", "partito");
        while(true){
        }
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "distrutto");
    }
}
