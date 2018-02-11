package freeskill.app.model.chat;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sofiane-e on 05/02/2018.
 * this class contain a chat with two users
 * there are the id of users and message with a date
 */

public class Chat {
    protected int idUser;
    //protected Bitmap b = new Bitmap();
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

    public Message getLastMessage(){
        if(messages == null){
            return null;
        }
        Message lastMessage = null;
        for (Message message: messages){
            if(lastMessage == null){
                lastMessage = message;
            }
            //LastMessage is after message.getDate
            if(lastMessage.getDate().compareTo(message.getDate()) < 0){
                lastMessage = message;
            }
        }
        return lastMessage;
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
}
