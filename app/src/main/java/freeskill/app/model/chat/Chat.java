package freeskill.app.model.chat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import freeskill.app.model.query.SetMessageIsRead;

import static freeskill.app.utils.Tools.parseDate;

/**
 * Created by Sofiane-e on 05/02/2018.
 * this class contain a chat with two users
 * there are the id of users and message with a date
 */

public class Chat implements Serializable {

    protected int idUser;
    protected Date matchDate;
    protected String name;
    protected ArrayList<Message> messages = new ArrayList<>();

    public Chat(int idUser, String name, ArrayList<Message> messages, Date matchDate) {
        this.idUser = idUser;
        this.name = name;
        this.messages = messages;
        this.matchDate = matchDate;
        //this.messages.addAll(messages);
    }

    public Chat(JSONObject res) {
        try {
            this.idUser = res.getInt("id");
            this.name = res.getString("first_name");
            String DateString = res.getString("matchTime");
            this.matchDate = parseDate(DateString);
            JSONArray messages = res.getJSONArray("messageList");
            this.messages = buildMessages(messages);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Message getLastMessage() {
        if (messages == null) {
            return null;
        }
        Message lastMessage = null;
        for (Message message : messages) {
            if (lastMessage == null) {
                lastMessage = message;
            }
            //LastMessage is after message.getDate
            if (lastMessage.getDate().compareTo(message.getDate()) < 0) {
                lastMessage = message;
            }
        }
        return lastMessage;
    }

    public void setMessageIsRead() {
        for (Message message : messages) {
            if (!message.isRead && message.isReceived()) {
                SetMessageIsRead sir = SetMessageIsRead.getInstance();
                sir.request(message.getIdUser1(), message.getIdMessage());
                message.setRead(true);
            }

        }
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
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
}
