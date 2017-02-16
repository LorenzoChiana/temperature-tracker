package com.example.loren.eventtracker.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import com.example.loren.eventtracker.activities.MainActivity;
import com.example.loren.eventtracker.tools.bt.MsgTooBigException;
import com.example.loren.eventtracker.tools.services.MessageService;
import com.example.loren.eventtracker.utils.C;

public class MessageHandler extends Handler {

    private WeakReference<MainActivity> activityContext = null;
    private WeakReference<MessageService> serviceContext = null;

    //istanza dell'handler
    private static MessageHandler instance = new MessageHandler();

    public static MessageHandler getHandler() {
        return instance;
    }

    public void setActivityContext(MainActivity activity) {
        this.activityContext = new WeakReference<>(activity);
    }

    public void setServiceContext(MessageService service) {
        this.serviceContext = new WeakReference<>(service);
    }

    @Override
    public void handleMessage(Message msg) {
        Object obj = msg.obj;

        if (obj instanceof String) {
            String message = obj.toString();

            if (message.equals(C.PRESENCE_MSG)) {
                //a seconda del contesto apro una nuova activity o lancio una notifica
                if (activityContext.get().isActivityOnForeground()) {
                    activityContext.get().startAlarmDialog();
                } else {
                    serviceContext.get().showNotification();
                }
            }
        }
    }
}

