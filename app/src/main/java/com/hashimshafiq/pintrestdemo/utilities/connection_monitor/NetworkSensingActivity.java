package com.hashimshafiq.pintrestdemo.utilities.connection_monitor;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.hashimshafiq.pintrestdemo.utilities.CONSTANTS;

public class NetworkSensingActivity extends AppCompatActivity implements ConnectionStateMonitor.OnNetworkAvailableCallbacks{

    private ConnectionStateMonitor connectionStateMonitor = null;
    private ViewGroup viewGroup = null;
    private AlertDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        super.onResume();
        if (viewGroup == null) viewGroup = findViewById(android.R.id.content);
        if (dialog == null){
            dialog = new AlertDialog.Builder(this).create();
        }


        if (connectionStateMonitor == null)
            connectionStateMonitor = new ConnectionStateMonitor(this, this);

        if(connectionStateMonitor!=null)
            connectionStateMonitor.enable();

        if (!connectionStateMonitor.hasNetworkConnection()) onNegative();
        else onPositive();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onPause() {
        if(dialog!=null)
            dialog.dismiss();
        dialog = null;
        //Unregister
        if(connectionStateMonitor!=null)
            connectionStateMonitor.disable();
        connectionStateMonitor = null;
        super.onPause();
    }

    public void onPositive() {
        runOnUiThread(() -> {
            if(dialog!=null && CONSTANTS.isConnectedAlready==0){
                CONSTANTS.isConnectedAlready = 1;
                new Handler().postDelayed(() -> dialog.dismiss(),1000);

            }
        });

    }

    @Override
    public void onNegative() {
        runOnUiThread(() -> {
            if(dialog!=null) {
                CONSTANTS.isConnectedAlready = 0;
                dialog.setTitle("No Internet");
                dialog.setMessage("Please check your internet connection");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
    }
}
