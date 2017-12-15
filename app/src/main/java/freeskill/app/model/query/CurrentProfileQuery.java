package freeskill.app.model.query;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Olivier on 12/12/2017.
 */

public class CurrentProfileQuery extends HttpsQuery {
    public CurrentProfileQuery() {

}

    public void getCurrentProfile(final String accessToken, RequestQueue queue) {
        //Set the URL for the request
        String url = "https://freeskill.ddns.net/user/GetProfile";

        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this){
            //Add the accessToken in the headers of the request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-access-token", accessToken);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONObject profile = response.getJSONObject("profile");
            System.out.println(profile);
            System.out.println(profile.getString("first_name"));
            System.out.println(profile.getString("last_name"));
            System.out.println(profile.getString("email"));
            System.out.println(profile.getString("description"));
            System.out.println(profile.getInt("average_mark"));

            JSONArray tags_share = profile.getJSONArray("tags_share");
            for(int i = 0; i < tags_share.length(); i++){
                System.out.println(tags_share.get(i));
            }
            JSONArray tags_discover = profile.getJSONArray("tags_discover");
            for(int i = 0; i < tags_discover.length(); i++){
                System.out.println(tags_discover.get(i));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
