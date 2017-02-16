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
import com.example.loren.eventtracker.handler.MessageHandler;
import com.example.loren.eventtracker.tools.bt.BluetoothConnectionManager;
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
    //variabile che indica se l'app è in foreground o no
    private boolean foreground = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        //cambio del contesto dell'handler
        MessageHandler.getHandler().setActivityContext(this);

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
        foreground = true;
        //distruzione di qualche aventuale service
        stopService();
    }

    @Override
    public void onActivityResult (int reqID , int res , Intent data ){

        if (btAdapter != null) {
            if (btAdapter.isEnabled()) {
                targetDevice = BluetoothUtils.findPairedDevice(C.TARGET_BT_DEVICE_NAME, btAdapter);

                if (targetDevice != null) {
                    ((TextView) findViewById(R.id.btFoundFlagLabel)).setText("Target BT Device: Found " + targetDevice.getName());
                    connectToTargetBtDevice();
                }
            }
        }
    }

    //Se l'app viene messa in background (onPause od onStop) viene avviato il service
    @Override
    protected void onPause(){
        super.onPause();
        foreground = false;
        startService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        foreground = false;
        startService();
    }

    //Quando l'app viene rimessa in foreground il service termina
    @Override
    protected void onResume() {
        super.onResume();
        foreground = true;
        stopService();
    }

    //Alla chiusura dell'app il service termina
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
        BluetoothConnectionManager.getInstance().cancel();
        BluetoothConnectionManager.nullIstance();
    }

    public boolean isActivityOnForeground() {
        return this.foreground;
    }

    //creazione dialog per richiesta
    public void startAlarmDialog() {
        DialogFragment dialog = new AlarmDialogFragment();
        dialog.show(getFragmentManager().beginTransaction(), "dialog");
    }

    //avvio service
    public void startService() {
        startService(new Intent(this, MessageService.class));
    }

    //distruzione service
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
}
