package freeskill.app.model.chat;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sofiane-e on 17/02/2018.
 */

public class FactoryMessage {
    public static ArrayList<Message> buildMessages(final JSONArray messages) {

        ArrayList<Message> messagesList = new ArrayList<>();
        for (int i = 0; i < messages.length(); i++) {
            try {
                JSONObject message = (JSONObject) messages.get(i);
                Log.d("messageBuilder", message.toString());
                Message m = new Message(message);
                messagesList.add(m);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return messagesList;
    }
}
