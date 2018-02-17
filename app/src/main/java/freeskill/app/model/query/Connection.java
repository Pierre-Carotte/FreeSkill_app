package freeskill.app.model.query;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import freeskill.app.controller.HomepageScreen;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.DataConnection;
import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 12/12/2017.
 */

public class Connection extends HttpsQuery {
    HomepageScreen homepageScreen;

    public Connection(HomepageScreen homepageScreen) {
        this.homepageScreen = homepageScreen;
    }

    public void getConnection(final String email, final String password, RequestQueue queue) {
        String url = Constants.API.Connection.URI + "?" + Constants.API.Connection.param1 +
                "=" + email + "&"
                + Constants.API.Connection.param2 + "=" + password;

        JsonObjectRequest JsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, this, this);

        // Add the request to the RequestQueue.
        queue.add(JsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            String success = response.getString("success");
            String message = response.getString("message");
            if (success.equals("true")) {
                CurrentApp.getInstance(null).setAccessToken(message);
                //TODO del line bellow

                //Save JWT for persistent connection
                DataConnection dc = DataConnection.getInstance();
                dc.setJWT(message);
                this.homepageScreen.getIntentSwipeScreen().putExtra(this.homepageScreen.EXTRA_TOKEN,
                        message);
                this.homepageScreen.startActivity(this.homepageScreen.getIntentSwipeScreen());
            } else {
                this.setAccessToken(message);
                System.out.println(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }
}
