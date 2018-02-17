package freeskill.app.model.chat;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import static freeskill.app.utils.Tools.parseDate;

/**
 * Created by Sofiane-e on 05/02/2018.
 * This class contain a list of chats
 */

public class ChatList {
    private ArrayList<Chat> chat = new ArrayList<>();
    private JSONArray chatL;


    public void build(JSONArray resJsonArray) {
        this.chatL = resJsonArray;
        this.chatL.length();

        for (int i = 0; i < this.chatL.length(); i++) {
            try {
                JSONObject res = (JSONObject) this.chatL.get(i);
                int id = res.getInt("id");
                String firstname = res.getString("first_name");
                String DateString = res.getString("matchTime");
                Date date = parseDate(DateString);
                JSONArray messages = res.getJSONArray("messageList");
                ArrayList<Message> mes = buildMessages(messages);
                Chat chat = new Chat(id, firstname, mes, date);
                this.chat.add(chat);
            } catch (JSONException e) {
                Log.e("build ChatList", "BAD Response");
            }
        }
    }

    private ArrayList<Message> buildMessages(final JSONArray messages) {

        ArrayList<Message> messagesList = new ArrayList<>();
        for (int i = 0; i < messages.length(); i++) {
            try {
                JSONObject message = (JSONObject) messages.get(i);
                Message m = new Message(message);
                messagesList.add(m);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return messagesList;
    }

    public Chat findChatByName(String name) {
        for (Chat res : chat) {
            if (res.getName().equals(name)) {
                return res;
            }
        }
        return null;
    }

    public ArrayList<Chat> getChat() {
        return chat;
    }
}
