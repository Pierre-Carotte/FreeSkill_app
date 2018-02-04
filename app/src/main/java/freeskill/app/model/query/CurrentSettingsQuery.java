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
 * Created by Olivier on 22/01/2018.
 */

public class CurrentSettingsQuery extends HttpsQuery{
    private SettingsScreen settingsScreen;
    private Settings settings;

    public CurrentSettingsQuery(SettingsScreen settingsScreen, Settings settings) {
        this.settingsScreen = settingsScreen;
        this.settings = settings;
    }

    public void getCurrentSettings(final String accessToken, RequestQueue queue) {
        //Set the URL for the request
        //String url = "https://freeskill.ddns.net/user/GetProfile";

        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.API.GetProfile.URI,
                null, this, this){
            //Add the accessToken in the headers of the request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT());
                //headers.put("x-access-token", accessToken);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            System.out.println(response);
            JSONObject message = response.getJSONObject("message");
            JSONObject settings = message.getJSONObject("settings");
            this.settings.setPerimeter(settings.getInt("perimeter"));
            if(settings.getInt("notif_message") == 1){
                this.settings.setNotif_message(true);
            }else{
                this.settings.setNotif_message(false);
            };

            if(settings.getInt("notif_reminder") == 1){
                this.settings.setNotif_meeting_reminder(true);
            }else{
                this.settings.setNotif_meeting_reminder(false);
            };

            if(settings.getInt("notif_match") == 1){
                this.settings.setNotif_match(true);
            }else{
                this.settings.setNotif_match(false);
            };

            if(settings.getInt("notif_meeting") == 1){
                this.settings.setNotif_meeting(true);
            }else{
                this.settings.setNotif_meeting(false);
            };

            if(settings.getInt("notif_mark") == 1){
                this.settings.setNotif_notation(true);
            }else{
                this.settings.setNotif_notation(false);
            };

            this.settingsScreen.getSeekBarDistance().setProgress(this.settings.getPerimeter());
            this.settingsScreen.getDistanceMax().setText(String.valueOf(this.settings.getPerimeter()) + " " + "km");
            this.settingsScreen.getSwitch_new_match().setChecked(this.settings.isNotif_match());
            this.settingsScreen.getSwitch_new_message().setChecked(this.settings.isNotif_message());
            this.settingsScreen.getSwitch_new_meeting().setChecked(this.settings.isNotif_meeting());
            this.settingsScreen.getSwitch_meeting_reminder().setChecked(this.settings.isNotif_meeting_reminder());
            this.settingsScreen.getSwitch_mark().setChecked(this.settings.isNotif_notation());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
