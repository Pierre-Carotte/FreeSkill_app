package freeskill.app.model.query;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.signature.StringSignature;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import freeskill.app.FreeskillApplication;
import freeskill.app.controller.ProfileScreen;
import freeskill.app.model.DataConnection;
import freeskill.app.model.Profile;
import freeskill.app.utils.Constants;
import freeskill.app.utils.JWTUtils;

/**
 * Created by Olivier on 12/12/2017.
 */

public class CurrentProfileQuery extends HttpsQuery  {
    private ProfileScreen profileScreen;
    private Profile profile;

    public CurrentProfileQuery(ProfileScreen profileScreen, Profile profile) {
        this.profileScreen = profileScreen;
        this.profile = profile;
    }

    public void getCurrentProfile(RequestQueue queue) {
        //ImageRequestQuery imageRequestQuery = new ImageRequestQuery(this.profileScreen);
        //imageRequestQuery.getImage(accessToken, queue);
        imageRequest();

        //Set the URL for the request
        //String url = "https://freeskill.ddns.net/user/GetProfile";

        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.API.GetProfile.URI, null, this, this){
            //Add the accessToken in the headers of the request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-access-token", DataConnection.getInstance().getJWT());
                return headers;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONObject profile = response.getJSONObject("message").getJSONObject("profile");
            System.out.println(profile);
            this.profile.setFirstname(profile.getString("first_name"));
            this.profile.setLastname(profile.getString("last_name"));
            this.profile.setEmail(profile.getString("email"));
            this.profile.setDescription(profile.getString("description"));

            if(profile.getString("average_mark") != "null"){
                this.profile.setAverageMark(profile.getDouble("average_mark"));
                this.profileScreen.getRatingBar_stars().setRating
                        ((float)profile.getDouble("average_mark"));
            }else{
                this.profileScreen.getRatingBar_stars().setRating(0);
            }

            JSONArray tags_share = profile.getJSONArray("tags_share");
            for(int i = 0; i < tags_share.length(); i++){
                this.profile.setTagShare(tags_share.get(i).toString());
            }
            JSONArray tags_discover = profile.getJSONArray("tags_discover");
            for(int i = 0; i < tags_discover.length(); i++){
                this.profile.setTagDiscover(tags_discover.get(i).toString());
            }

            this.profileScreen.getTextview_username().setText(profile.getString("first_name"));

            String text_tags_share = "";
            for(int i = 0; i < tags_share.length(); i++){
                text_tags_share = text_tags_share.concat("#" + tags_share.get(i).toString() + " ");
            }
            this.profileScreen.getTextview_tags_share().setText(text_tags_share);
            String text_tags_discover = "";
            for(int i = 0; i < tags_discover.length(); i++){
                text_tags_discover = text_tags_discover.concat("#" + tags_discover.get(i).toString() + " ");
            }
            this.profileScreen.getTextview_tags_discover().setText(text_tags_discover);
            this.profileScreen.getTextview_description().setText(profile.getString("description"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    public void imageRequest(){

        GlideUrl glideUrl = new GlideUrl(Constants.API.GetImage.URI, new LazyHeaders.Builder()
                .addHeader(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT())
                .build());

        //TODO change signature
        Glide.with(FreeskillApplication.getContext()).load(glideUrl)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(this.profileScreen.getImageView());

    }
}
