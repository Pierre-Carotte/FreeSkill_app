package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import freeskill.app.controller.interfaces.ChatChangeListener;
import freeskill.app.model.chat.ChatList;
import freeskill.app.utils.Constants;

/**
 * Created by Sofiane-e on 05/02/2018.
 */

public class GetMatches extends HttpsQuery {
    //private ChatList c;
    private ChatChangeListener mListener;

    public GetMatches(ChatChangeListener mListener) {
        this.mListener = mListener;
    }

    public void getChatList(RequestQueue queue) throws AuthFailureError {
        // Request a JSON response from the provided URL.
        JsonObjectRequest stringR = new CustomJsonObjectRequest(Constants.API.GetChatList.METHOD,
                Constants.API.GetChatList.URI,
                null, this, this);
        // Add the request to the RequestQueue.
        queue.add(stringR);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Error GetMatches", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray resJsonArray = response.getJSONArray("message");
            ChatList chatList = new ChatList();
            chatList.build(resJsonArray);
            mListener.onChatRetrieved(chatList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
