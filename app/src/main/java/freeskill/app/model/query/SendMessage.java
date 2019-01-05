package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import freeskill.app.controller.fragments.ChatFragment;
import freeskill.app.model.CurrentApp;
import freeskill.app.utils.Constants;

/**
 * Created by Sofiane-e on 15/02/2018.
 */

public class SendMessage extends HttpsQuery {
    private static final SendMessage ourInstance = new SendMessage();
    private RequestQueue queue;
    private ChatFragment chatFragment;

    public static SendMessage getInstance() {
        return ourInstance;
    }


    private SendMessage() {
        CurrentApp currentApp = CurrentApp.getInstance(null);
        queue = currentApp.getQueue();
    }

    public void sendMessage(ChatFragment chatFragment, int idUser, String message) {
        this.chatFragment = chatFragment;
        Map<String, String> putParam = new HashMap<>();
        putParam.put(Constants.API.SendMessage.message, message);
        putParam.put(Constants.API.SendMessage.interlocutor, String.valueOf(idUser));

        /*try {
            putParam.put(Constants.API.SendMessage.message, URLEncoder.encode("Frick&Frack", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        JSONObject jo = new JSONObject(putParam);
        Log.d("sendMessage", String.valueOf(idUser) + message);
        JsonObjectRequest stringR = new CustomJsonObjectRequest(Request.Method.PUT,
                Constants.API.SendMessage.URI,
                jo, this, this);
        System.out.print(message);
        System.out.print(queue.add(stringR));
        //queue.add(stringR);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("sendMessage", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("sendMessage", response.toString());
        try {
            Boolean success = response.getBoolean("success");
            if (!success) {
                /**
                 * TODO
                 * TOAST sendMessage success false
                 */
                Log.e("sendmeassage", "success false");
                return;
            }
            JSONArray messageTab = response.getJSONArray("message");

            if (messageTab.length() == 1) {
                JSONObject message = (JSONObject) messageTab.get(0);
                chatFragment.notifyNewMessage(message);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}