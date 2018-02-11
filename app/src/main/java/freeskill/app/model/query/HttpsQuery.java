package freeskill.app.model.query;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import freeskill.app.model.DataConnection;
import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 14/12/2017.
 */

public abstract class HttpsQuery implements Response.Listener<JSONObject>, Response.ErrorListener {
    protected String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken =DataConnection.getInstance().getJWT();
        //this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }
}
