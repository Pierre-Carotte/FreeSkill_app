package freeskill.app.model.query;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.Observable;

/**
 * Created by Olivier on 14/12/2017.
 */

public abstract class HttpsQuery implements Response.Listener<JSONObject>, Response.ErrorListener {
    protected String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }
}
