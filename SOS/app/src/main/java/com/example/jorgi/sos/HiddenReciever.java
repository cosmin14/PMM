package com.example.jorgi.sos;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jorgi on 02/03/2016.
 */
public class HiddenReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Extract phone number reformatted by previous receivers
        String phoneNumber = getResultData();
        if (phoneNumber == null) {
            // No reformatted number, use the original
            phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }

        if(phoneNumber.equals("53630662")){ // DialedNumber checking.
            // My app will bring up, so cancel the broadcast
            setResultData(null);

            // Start my app
            Intent i=new Intent(context,MainActivity.class);
            i.putExtra("extra_phone", phoneNumber);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

    }


}