package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.model.CurrentApp;
import freeskill.app.utils.Constants;

public class SetFCMID extends HttpsQuery {
    private static final SetFCMID ourInstance = new SetFCMID();
    private final RequestQueue queue;

    public static SetFCMID getInstance() {
        return ourInstance;
    }

    private SetFCMID() {
        CurrentApp currentApp = CurrentApp.getInstance(null);
        queue = currentApp.getQueue();
    }

    public void request(String idFCM) {
        String requestURI = Constants.API.SetFCMID.URI + Constants.API.SetFCMID.fcm;
        Map<String, String> putParam = new HashMap<>();
        putParam.put("fcm", String.valueOf(idFCM));
        JsonObjectRequest stringR = new CustomJsonObjectRequest(Constants.API.SetFCMID.METHOD,
                requestURI,
                new JSONObject(putParam),this, this);
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
