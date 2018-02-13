package freeskill.app;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.InputStream;

import freeskill.app.controller.HomepageScreen;
import freeskill.app.utils.HttpsTrustManager;

/**
 * Created by Sofiane-e on 02/02/2018.
 */

public class FreeskillApplication extends Application {

    private static Context sContext;

    public void onCreate(){
        super.onCreate();
        //Add CA for let's encrypt and HTTPS
        InputStream caInput=getResources().openRawResource(R.raw.letsencryptauthorityx3);
        HttpsTrustManager https = new HttpsTrustManager(caInput);
        https.allowMySSL();
        // Keep a reference to the application context
        sContext = getApplicationContext();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }
}
