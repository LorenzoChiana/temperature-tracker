package com.example.loren.eventtracker.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.loren.eventtracker.R;
import com.example.loren.eventtracker.tools.bt.BluetoothConnectionManager;
import com.example.loren.eventtracker.tools.bt.MsgTooBigException;
import com.example.loren.eventtracker.utils.C;

//activity creata dalla pressione della notifica
//contiene due bottoni con la scelta della risposta della domanda della notifica
public class AlarmActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        initUI();
    }

    private void initUI() {
        Button buttonYes = (Button) findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    Log.d("Message", "sent yes");
                    //invio di messaggio ad arduino tramite bluetooth
                    BluetoothConnectionManager.getInstance().sendMsg(C.POSITIVE_ALARM_RESPONSE);
                } catch (MsgTooBigException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        Button buttonNo = (Button) findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    Log.d("Message", "sent no");
                    //invio di messaggio ad arduino tramite bluetooth
                    BluetoothConnectionManager.getInstance().sendMsg(C.NEGATIVE_ALARM_RESPONSE);
                } catch (MsgTooBigException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }
}
