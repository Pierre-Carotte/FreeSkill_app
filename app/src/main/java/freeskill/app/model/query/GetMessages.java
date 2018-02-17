package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import freeskill.app.controller.fragments.ChatFragment;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.chat.FactoryMessage;
import freeskill.app.model.chat.Message;
import freeskill.app.utils.Constants;

/**
 * Created by Sofiane-e on 17/02/2018.
 */

public class GetMessages extends HttpsQuery{
    private static final GetMessages ourInstance = new GetMessages();
    private final RequestQueue queue;
    ChatFragment chatFragment;

    public static GetMessages getInstance() {
        return ourInstance;
    }

    private GetMessages() {
        CurrentApp currentApp = CurrentApp.getInstance(null);
        queue = currentApp.getQueue();
    }

    public void request(ChatFragment chatFragment, int idUser) {
        this.chatFragment = chatFragment;
        String requestURI = Constants.API.GetMessages.URI + Constants.API.GetMessages.interlocutor
                + "=" + idUser;
        JsonObjectRequest stringR = new CustomJsonObjectRequest(Constants.API.GetMessages.METHOD,
                requestURI,
                null, this, this);
        queue.add(stringR);
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
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
            Log.d("getMessagges", messageTab.toString());
            if (messageTab.length() == 1) {
                //Chat chat = new Chat((JSONObject) messageTab.get(0));
                chatFragment.notifyNewChat((JSONObject) messageTab.get(0));
               // chatFragment.getChat().getMessages().addAll(messages);
                //chatFragment.getmMessageAdapter().notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
