package com.example.loren.eventtracker.tools.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.loren.eventtracker.tools.bt.BluetoothConnectionManager;
import com.example.loren.eventtracker.tools.bt.MsgTooBigException;
import com.example.loren.eventtracker.utils.C;

import org.json.JSONObject;


public class SendMessage extends IntentService {
    private String bt_name;

    public SendMessage(String name) {
        super(name);
        this.bt_name = name;
    }

    public String getMessage() {
        return this.bt_name;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Service", "ciao");
    }


}
