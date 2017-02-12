package com.example.loren.eventtracker.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.loren.eventtracker.R;
import com.example.loren.eventtracker.tools.bt.BluetoothConnectionTask;
import com.example.loren.eventtracker.tools.bt.BluetoothUtils;
import com.example.loren.eventtracker.tools.services.MessageService;
import com.example.loren.eventtracker.utils.C;
import com.example.loren.eventtracker.tools.dialog.AlarmDialogFragment;

import org.json.JSONObject;

import java.util.UUID;

public class MainActivity extends Activity {

    private BluetoothAdapter btAdapter;
    private BluetoothDevice targetDevice;
    private static MainActivityHandler uiHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        uiHandler = new MainActivityHandler();

    }

    @Override
    protected void onStart() {
        super.onStart();

        btAdapter = BluetoothAdapter.getDefaultAdapter();
        // Permessi per l'uso del bluetooth
        if (btAdapter != null) {
            if (btAdapter.isEnabled()) {
                targetDevice = BluetoothUtils.findPairedDevice(C.TARGET_BT_DEVICE_NAME, btAdapter);

                if (targetDevice != null) {
                    ((TextView) findViewById(R.id.btFoundFlagLabel)).setText("Target BT Device: Found " + targetDevice.getName());
                    connectToTargetBtDevice();
                }
            } else {
                startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), C.ENABLE_BT_REQUEST);
            }
        } else {
            showBluetoothUnavailableAlert();
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        startService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }


    public void startService() {
        startService(new Intent(this, MessageService.class));
    }

    public void stopService() {
        stopService(new Intent(this, MessageService.class));
    }

    private void connectToTargetBtDevice() {
        UUID uuid = UUID.fromString(C.TARGET_BT_DEVICE_UUID);

        BluetoothConnectionTask task = new BluetoothConnectionTask(this, targetDevice, uuid);
        task.execute();
    }

    private void showBluetoothUnavailableAlert() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.btUnavailableAlertTitle))
                .setMessage(getString(R.string.btUnavailableAlertMessage))
                .setCancelable(false)
                .setNeutralButton(getString(R.string.btUnavailableAlertBtnText), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        MainActivity.this.finish();
                    }
                })
                .create();

        dialog.show();
    }

    public static MainActivityHandler getHandler() {
        return uiHandler;
    }

    public class MainActivityHandler extends Handler {

        MainActivityHandler() {

        }

        public void handleMessage(Message msg) {

            Object obj = msg.obj;

            if (obj instanceof String) {
                String message = obj.toString();
                Log.d("RecivedMsg", message);

                // a seconda del messaggio ricevuto da arduino:
                switch (message) {
                    case C.PRESENCE_MSG:
                        DialogFragment dialog = new AlarmDialogFragment();
                        dialog.show(getFragmentManager().beginTransaction(), "dialog");
                        break;
                }
            }

            if (obj instanceof JSONObject) {
                //TODO
            }
        }
    }
}
