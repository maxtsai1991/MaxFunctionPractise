package com.example.demo.jsonpersoncat.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class UpdateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final String msg="intent:"+intent+" action:"+intent.getAction();
        Log.d("DEBUG",msg);
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
