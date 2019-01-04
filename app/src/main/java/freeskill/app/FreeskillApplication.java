package freeskill.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.io.InputStream;

import freeskill.app.utils.HttpsTrustManager;
import freeskill.app.model.query.SetFCMID;
/**
 * Created by Sofiane-e on 02/02/2018.
 */

public class FreeskillApplication extends Application {

    private static Context sContext;

    public void onCreate() {
        super.onCreate();
        //Add CA for let's encrypt and HTTPS
        InputStream caInput = getResources().openRawResource(R.raw.letsencryptauthorityx3);
        HttpsTrustManager https = new HttpsTrustManager(caInput);
        https.allowMySSL();
        //Log.e("TAG", "token " + FirebaseInstanceId.getInstance().getToken());

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();
                SetFCMID.getInstance().request(token);
                Log.e("TAG", "token goodddd" + token);
                // send it to server
            }
        });

        // Keep a reference to the application context
        sContext = getApplicationContext();
    }

    // Used to access Context anywhere within the app
    public static Context getContext() {
        return sContext;
    }
}
