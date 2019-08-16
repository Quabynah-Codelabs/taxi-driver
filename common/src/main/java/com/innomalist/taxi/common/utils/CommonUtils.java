package com.innomalist.taxi.common.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.innomalist.taxi.common.models.Driver;
import com.innomalist.taxi.common.models.Rider;

public class CommonUtils {
    public static Driver driver;
    public static Rider rider;
    public final static int MY_PERMISSIONS_REQUEST_STORAGE = 2;
    public static CountDownTimer currentTimer;
    public static LatLng currentLocation;

    public static boolean isInternetDisabled(AppCompatActivity activity) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm == null)
            return true;
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return !haveConnectedWifi && !haveConnectedMobile;
    }

    public static boolean isGPSEnabled (AppCompatActivity activity){
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}