package freeskill.app.model.query;

import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.DataConnection;
import freeskill.app.model.adapters.MyAppAdapter;
import freeskill.app.model.Profile;
import freeskill.app.model.adapters.MyAppAdapter;
import freeskill.app.utils.Constants;

/**
 * Created by Florian on 18/12/2017.
 */

public class Judgement implements Response.Listener<JSONObject>, Response.ErrorListener {

    private String accessToken;
    private RequestQueue queue;
    private SwipeScreen swipeScreen;
    private ArrayList<Profile> profiles;
    private ArrayAdapter<Bitmap> adapter;
    private MyAppAdapter myAdapter;
    private Profile profile;
    private List<Integer> idList;
    private CurrentApp currentApp;
    private HashMap<Integer, Bitmap> images;

    public HashMap<Integer, Bitmap> getImages() {
        return images;
    }

    public ArrayAdapter<Bitmap> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<Bitmap> adapter) {
        this.adapter = adapter;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public MyAppAdapter getMyAdapter() {
        return myAdapter;
    }


    public Judgement(String accessToken, RequestQueue queue, SwipeScreen swipeScreen) {
        this.accessToken = accessToken;
        this.queue = queue;
        this.swipeScreen = swipeScreen;
        this.profiles = new ArrayList<Profile>();
        this.imageRequestProfilesQuery = new ImageRequestProfilesQuery(this,
                new ImageRequestProfilesQuery.OnImageLoaded() {

                    private Judgement judgement;
                    private MyAppAdapter myAppAdapter;

                    @Override
                    public void onSuccess(Bitmap bitmap) {
               /* for(int i = 0; i<this.judgement.profiles.size();i++){
                    this.judgement.profiles.get(i).setPicture(bitmap);
                }*/
                    }
                });
        this.myAdapter = new MyAppAdapter(this.swipeScreen, -1, this.profiles, this.getImages());
        this.currentApp = CurrentApp.getInstance(null);
    }

    public void requestProfiles(final String accessToken) {
        this.profile = new Profile();
        String url = "https://freeskill.ddns.net/user/SearchProfiles";
        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this) {
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
        try {
            //this.swipeScreen.progressBar.setVisibility(View.GONE);
            String success = response.getString("success");
            if (success.equals("true")) {
                JSONArray allProfiles = response.getJSONArray("message");
                for (int i = 0; i < allProfiles.length(); i++) {
                    Profile p = new Profile();
                    JSONArray tags_discover = allProfiles.getJSONObject(i).getJSONArray("tags_discover");
                    JSONArray tags_share = allProfiles.getJSONObject(i).getJSONArray("tags_share");

                    p.setFirstname(allProfiles.getJSONObject(i).getString("first_name"));
                    if(allProfiles.getJSONObject(i).getString("average_mark") != "null"){
                        p.setAverageMark(allProfiles.getJSONObject(i).getDouble("average_mark"));
                    }else{
                        p.setAverageMark(0);
                    }
                    p.setDescription(allProfiles.getJSONObject(i).getString("description"));
                    for (int j = 0; j < tags_discover.length(); j++) {
                        p.setTagDiscover(tags_discover.get(j).toString());
                        System.out.println(tags_discover.get(j).toString());
                    }
                    for (int j = 0; j < tags_share.length(); j++) {
                        p.setTagShare(tags_share.get(j).toString());
                    }
                    p.setAssos(allProfiles.getJSONObject(i).getInt("is_assos"));
                    p.setId(allProfiles.getJSONObject(i).getInt("id"));
                    p.setPerimeter(allProfiles.getJSONObject(i).getInt("distance"));

                    profiles.add(p);
                }
                for (int i = 0; i < this.profiles.size(); i++) {
                    this.swipeScreen.al.add(this.profiles.get(i));
                    this.imageRequestProfilesQuery.getImage(this.currentApp.getAccessToken(),
                            this.currentApp.getQueue(), this.profiles.get(i).getId());
                }
                this.myAdapter.notifyDataSetChanged();
                //this.adapter.notifyDataSetChanged();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

