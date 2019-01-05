package freeskill.app.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class NotificationsService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        //Log.e("zezdedzzezeezezzeez",token);
        // sending token to server here
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            // 1 - Get message sent by Firebase
            String message = remoteMessage.getNotification().getBody();

            //String data = remoteMessage.getData().get("score");
            //2 - Show message in console
            //Log.e("TAG",data + message);
            Log.e("TAG","Message envoyé :" + message);
            Log.e("TAG", "token " + FirebaseInstanceId.getInstance().getInstanceId());

        }
    }
}