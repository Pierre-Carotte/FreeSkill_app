package freeskill.app.model.query;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.model.DataConnection;
import freeskill.app.utils.Constants;

/**
 * Created by Sofiane-e on 05/02/2018.
 */

public class CustomJsonObjectRequest extends JsonObjectRequest {


    public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT());
        return headers;
    }
}
