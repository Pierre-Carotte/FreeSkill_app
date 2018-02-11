package freeskill.app.model.chat;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static freeskill.app.utils.Tools.parseDate;

/**
 * Created by Sofiane-e on 05/02/2018.
 * This class contain a list of chats
 */

public class ChatList {
    //private Map<Integer, Chat> chat;
    private ArrayList<Chat> chat = new ArrayList<>();
    private JSONArray chatL;
    public ChatList() {
        /*Message m = new Message(true, null, "ok");
        Log.d("message", m.message);*/
    }


    public void build(JSONArray resJsonArray){
        this.chatL = resJsonArray;
        this.chatL.length();
        for(int i = 0; i < this.chatL.length(); i++){
            try {
                JSONObject res = (JSONObject) this.chatL.get(i);
                int id = res.getInt("id");
                String firstname = res.getString("first_name");
                String DateString = res.getString("matchTime");
                Date date = parseDate(DateString);
                JSONArray messages = res.getJSONArray("messageList");
                ArrayList<Message> mes= buildMessages(messages);
                Log.d("chat", firstname + ": "+ messages);
                Chat chat = new Chat(id,firstname, mes, date);
                this.chat.add(chat);
            } catch (JSONException e) {
                Log.e("build ChatList", "BAD Response");
            }
        }
    }

    private ArrayList<Message> buildMessages(final JSONArray messages){
        ArrayList<Message> messagesList = new ArrayList<>();
        for(int i = 0; i < messages.length(); i++){
            try {
                JSONObject message = (JSONObject) messages.get(i);
                int idUser1 = message.getInt("id_user1");
                int idUser2 = message.getInt("id_user2");
                String mes = message.getString("message");
                //Log.d("message build", mes);
                int isReadInt = message.getInt("is_read");
                boolean isRead = false;
                if(isReadInt == 1){
                    isRead = true;
                }
                Date date = parseDate(message.getString("dath_message"));
                Message m = new Message(idUser1, idUser2, date, mes, isRead);
                messagesList.add(m);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return messagesList;
    }

    public ArrayList<Chat> getChat() {
        return chat;
    }
}
