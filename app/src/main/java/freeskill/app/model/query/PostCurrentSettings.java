package freeskill.app.model.query;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.model.CurrentApp;
import freeskill.app.model.DataConnection;
import freeskill.app.model.Settings;
import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 05/02/2018.
 */

public class PostCurrentSettings extends HttpsQuery {

    /*public void postCurrentSettings(RequestQueue queue, int perimeter, boolean notif_match,
                                    boolean notif_message, boolean notif_meeting,
                                    boolean notif_reminder, boolean notif_mark){*/


    public void postCurrentSettings(RequestQueue queue, String field, String value){

        Map<String, String> putParam= new HashMap<String, String>();
        switch(field){
            case Constants.JSONparameters.PERIMETER:
                putParam.put(Constants.JSONparameters.PERIMETER, value);
                break;
            case Constants.JSONparameters.NOTIF_MATCH:
                putParam.put(Constants.JSONparameters.NOTIF_MATCH, value);
                break;
            case Constants.JSONparameters.NOTIF_MESSAGE:
                putParam.put(Constants.JSONparameters.NOTIF_MESSAGE, value);
                break;
            case Constants.JSONparameters.NOTIF_MEETING:
                putParam.put(Constants.JSONparameters.NOTIF_MEETING, value);
                break;
            case Constants.JSONparameters.NOTIF_REMINDER:
                putParam.put(Constants.JSONparameters.NOTIF_REMINDER, value);
                break;
            case Constants.JSONparameters.NOTIF_MARK:
                putParam.put(Constants.JSONparameters.NOTIF_MARK, value);
                break;
            case Constants.JSONparameters.ID_FCM:
                putParam.put(Constants.JSONparameters.ID_FCM, value);
                break;
        }

        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT,
                Constants.API.SetProfile.URI
                + "profileUpdate=" + new JSONObject(putParam), null, this,
                this){
            //Add the accessToken in the headers of the request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(Constants.General.KEY_ACCESS_TOKEN,
                        DataConnection.getInstance().getJWT());
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println("Erreur Volley " + error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println(response);
    }
}
