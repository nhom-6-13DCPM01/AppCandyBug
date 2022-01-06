package com.example.appcandybug.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.Network;

public class CheckConnection {
    public static boolean haveNetworkConnection(Context context){
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobie = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for (NetworkInfo ni : networkInfos){
            if(ni.getTypeName().equalsIgnoreCase("WIFI"))
                if(ni.isConnected())
                    haveConnectedWifi = true;
                if(ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if(ni.isConnected())
                        haveConnectedMobie = true;
        }
        return haveConnectedWifi || haveConnectedMobie;
    }

    public static void ShowToast_Short(Context context,String thongbao){
        Toast.makeText(context,thongbao,Toast.LENGTH_SHORT).show();
    }
}
