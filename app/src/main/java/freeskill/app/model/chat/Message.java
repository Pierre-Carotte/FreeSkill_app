package freeskill.app.model.chat;

import android.util.Log;

import java.util.Date;

import freeskill.app.model.DataConnection;

/**
 * Created by Sofiane-e on 05/02/2018.
 */

public class Message {
    protected int idUser1 = -1; //Sender
    protected int idUser2 = -1;//receiver
    protected Date date;
    protected String message = "";
    protected boolean isRead = false;

    public Message(final int idUser1, final int idUser2, final Date date,
                   final String message, final boolean isRead) {
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.date = date;
        this.message = message;
        this.isRead = isRead;
    }

    public boolean isReceived(){
        if(idUser1 == -1 && idUser2 == -1){
            return false;
        }
        int myId = DataConnection.getInstance().getIdUser();
        if(idUser2 == myId){
            return true;
        }
        return false;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
