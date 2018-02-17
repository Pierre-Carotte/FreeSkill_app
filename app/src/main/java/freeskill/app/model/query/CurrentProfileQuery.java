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

import freeskill.app.controller.ProfileScreen;
import freeskill.app.model.DataConnection;
import freeskill.app.model.Profile;
import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 12/12/2017.
 */

public class CurrentProfileQuery extends HttpsQuery {
    private ProfileScreen profileScreen;
    private Profile profile;

    public CurrentProfileQuery(ProfileScreen profileScreen, Profile profile) {
        this.profileScreen = profileScreen;
        this.profile = profile;
    }

    public void getCurrentProfile(final String accessToken, RequestQueue queue) {
        ImageRequestQuery imageRequestQuery = new ImageRequestQuery(this.profileScreen);
        imageRequestQuery.getImage(accessToken, queue);
        //Set the URL for the request
        //String url = "https://freeskill.ddns.net/user/GetProfile";
        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.API.GetProfile.URI, null, this, this) {
            //Add the accessToken in the headers of the request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-access-token", DataConnection.getInstance().getJWT());
                //headers.put("x-access-token", accessToken);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONObject message = response.getJSONObject("message");
            JSONObject profile = message.getJSONObject("profile");
            System.out.println(profile);
            System.out.println(profile.getString("first_name"));
            this.profile.setFirstname(profile.getString("first_name"));
            System.out.println(profile.getString("last_name"));
            this.profile.setLastname(profile.getString("last_name"));
            System.out.println(profile.getString("email"));
            this.profile.setEmail(profile.getString("email"));
            System.out.println(profile.getString("description"));
            this.profile.setDescription(profile.getString("description"));
            System.out.println(profile.getInt("average_mark"));
            this.profile.setAverageMark(profile.getInt("average_mark"));

            JSONArray tags_share = profile.getJSONArray("tags_share");
            for (int i = 0; i < tags_share.length(); i++) {
                System.out.println(tags_share.get(i));
                this.profile.setTagShare(tags_share.get(i).toString());
            }
            JSONArray tags_discover = profile.getJSONArray("tags_discover");
            for (int i = 0; i < tags_discover.length(); i++) {
                System.out.println(tags_discover.get(i));
                this.profile.setTagDiscover(tags_discover.get(i).toString());
            }

            this.profileScreen.getTextview_username().setText(profile.getString("first_name"));
            this.profileScreen.getRatingBar_stars().setRating(profile.getInt("average_mark"));
            String text_tags_share = "";
            for (int i = 0; i < tags_share.length(); i++) {
                text_tags_share = text_tags_share.concat("#" + tags_share.get(i).toString() + " ");
            }
            this.profileScreen.getTextview_tags_share().setText(text_tags_share);
            String text_tags_discover = "";
            for (int i = 0; i < tags_discover.length(); i++) {
                text_tags_discover = text_tags_discover.concat("#" + tags_discover.get(i).toString() + " ");
            }
            this.profileScreen.getTextview_tags_discover().setText(text_tags_discover);
            this.profileScreen.getTextview_description().setText(profile.getString("description"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
