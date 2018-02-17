package freeskill.app.model.query;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 14/02/2018.
 */

public class PostUserRegistration extends HttpsQuery {

    public void postUserRegistration(RequestQueue queue, String firstname, String lastname,
                                     String email, String password) {

        Map<String, String> putParam = new HashMap<>();
        putParam.put("firstname", String.valueOf(firstname));
        putParam.put("name", String.valueOf(lastname));
        putParam.put("email", String.valueOf(email));
        putParam.put("password", String.valueOf(password));

        System.out.println(new JSONObject(putParam));
        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                Constants.API.Register.URI, new JSONObject(putParam), this,
                this);

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
}
