package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import freeskill.app.controller.fragments.ChatFragment;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.chat.ChatList;
import freeskill.app.utils.Constants;

/**
 * Created by Sofiane-e on 17/02/2018.
 */

public class GetMessages extends HttpsQuery {
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
            JSONArray chatlistJson = response.getJSONArray("message");
            if (chatlistJson.length() > 0) {
                ChatList chatList = new ChatList();
                chatList.build(chatlistJson);
                Log.d("name", chatFragment.getChat().getName());
                Chat chat = chatList.findChatByName(chatFragment.getChat().getName());
                if (chat != null) {
                    chatFragment.notifyNewChat(chat);
                }
                //Log.d("getMessagges", messageTab.toString());
                //chatFragment.notifyNewChat((JSONObject) messageTab.get(0));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
