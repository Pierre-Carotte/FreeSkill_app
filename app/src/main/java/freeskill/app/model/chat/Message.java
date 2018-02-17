package freeskill.app.model.chat;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

import freeskill.app.model.DataConnection;

import static freeskill.app.utils.Tools.parseDate;

/**
 * Created by Sofiane-e on 05/02/2018.
 */

public class Message implements Serializable {
    protected int idMessage = -1;

    protected int idUser1 = -1; //Sender

    protected int idUser2 = -1;//receiver

    protected Date date;

    protected String message = "";

    protected boolean isRead = false;

    public Message(final int idMessage, final int idUser1, final int idUser2, final Date date,
                   final String message, final boolean isRead) {
        this.idMessage = idMessage;
        this.idUser1 = idUser1;
        this.idUser2 = idUser2;
        this.date = date;
        this.message = message;
        this.isRead = isRead;
    }

    public Message(JSONObject message) {
        try {
            this.idMessage = message.getInt("id");
            this.idUser1 = message.getInt("id_user1");
            this.idUser2 = message.getInt("id_user2");
            this.message = message.getString("message");
            this.date = parseDate(message.getString("dath_message"));
            int isReadInt = message.getInt("is_read");
            this.isRead = false;
            if (isReadInt == 1) {
                this.isRead = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public boolean isReceived() {
        if (idUser1 == -1 && idUser2 == -1) {
            return false;
        }
        int myId = DataConnection.getInstance().getIdUser();
        if (idUser2 == myId) {
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

    public int getIdMessage() {
        return idMessage;
    }

    public int getIdUser1() {
        return idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }


}
