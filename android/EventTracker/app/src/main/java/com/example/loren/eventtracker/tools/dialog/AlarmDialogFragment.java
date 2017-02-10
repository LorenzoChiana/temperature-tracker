package com.example.loren.eventtracker.tools.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.example.loren.eventtracker.R;
import com.example.loren.eventtracker.tools.bt.BluetoothConnectionManager;
import com.example.loren.eventtracker.tools.bt.MsgTooBigException;
import com.example.loren.eventtracker.utils.C;

public class AlarmDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.ask)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("Dialog", "si");
                        try {
                            BluetoothConnectionManager.getInstance().sendMsg(C.POSITIVE_ALARM_RESPONSE);
                        } catch (MsgTooBigException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("Dialog", "no");
                        try {
                            BluetoothConnectionManager.getInstance().sendMsg(C.NEGATIVE_ALARM_RESPONSE);
                        } catch (MsgTooBigException e) {
                            e.printStackTrace();
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}