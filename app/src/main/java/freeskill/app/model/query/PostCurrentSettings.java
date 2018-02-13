package freeskill.app.model.query;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.model.DataConnection;
import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 05/02/2018.
 */

public class PostCurrentSettings extends HttpsQuery {

    public void postCurrentSettings(RequestQueue queue, int perimeter, boolean notif_match,
                                    boolean notif_message, boolean notif_meeting,
                                    boolean notif_reminder, boolean notif_mark){

        Map<String, String> putParam= new HashMap<String, String>();
        putParam.put("perimeter", String.valueOf(perimeter));
        putParam.put("notif_match", String.valueOf(booleanToInt(notif_match)));
        putParam.put("notif_message", String.valueOf(booleanToInt(notif_message)));
        putParam.put("notif_meeting", String.valueOf(booleanToInt(notif_meeting)));
        putParam.put("notif_reminder", String.valueOf(booleanToInt(notif_reminder)));
        putParam.put("notif_mark", String.valueOf(booleanToInt(notif_mark)));

        System.out.println(new JSONObject(putParam));
        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT,
                Constants.API.SetProfile.URI
                + "profileUpdate=" + new JSONObject(putParam), null, this,
                this){
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
        System.out.println(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println(response);
    }

    public static int booleanToInt(boolean value) {
        // Convert true to 1 and false to 0.
        return value ? 1 : 0;
    }
}
