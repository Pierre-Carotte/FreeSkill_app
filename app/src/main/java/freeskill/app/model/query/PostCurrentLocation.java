package freeskill.app.model.query;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.controller.SettingsScreen;
import freeskill.app.model.DataConnection;
import freeskill.app.model.Settings;
import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 12/02/2018.
 */

public class PostCurrentLocation extends HttpsQuery {
    private double latitude;
    private double longitude;

    public PostCurrentLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void getCurrentLocation(RequestQueue queue) {
        //Set the URL for the request
        String url = "https://freeskill.ddns.net/user/setLocation";

        Map<String, String> putParam= new HashMap<String, String>();
        putParam.put("lat", String.valueOf(latitude));
        putParam.put("lon", String.valueOf(longitude));

        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                new JSONObject(putParam), this, this) {
            //Add the accessToken in the headers of the request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT());
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("Error: " + error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println("Response: " + response);
    }
}
