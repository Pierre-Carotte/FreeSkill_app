package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import freeskill.app.model.CurrentApp;
import freeskill.app.utils.Constants;

/**
 * Created by Sofiane-e on 15/02/2018.
 */

public class SetMessageIsRead extends HttpsQuery {
    private static final SetMessageIsRead ourInstance = new SetMessageIsRead();
    private final RequestQueue queue;

    public static SetMessageIsRead getInstance() {
        return ourInstance;
    }

    private SetMessageIsRead() {
        CurrentApp currentApp = CurrentApp.getInstance(null);
        queue = currentApp.getQueue();
    }

    public void request(int idUser, int idMsg) {
        String requestURI = Constants.API.SetMessageIsRead.URI + Constants.API.SetMessageIsRead.interlocutor
                + "=" + idUser + "&" + Constants.API.SetMessageIsRead.idmsg + "=" + idMsg;
        JsonObjectRequest stringR = new CustomJsonObjectRequest(Request.Method.POST,
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
