package freeskill.app.model;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import freeskill.app.R;
import freeskill.app.controller.SwipeScreen;

/**
 * Created by Florian on 18/12/2017.
 */

public class Judgement implements Response.Listener<JSONObject>, Response.ErrorListener{

    private String accessToken;
    private RequestQueue queue;
    private SwipeScreen swipeScreen;
    private ArrayList<Profile> profiles;

    private ArrayAdapter<String> adapter;
    private MyAppAdapter myAdapter;
    private Profile profile;


   public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    public MyAppAdapter getMyAdapter() {
        return myAdapter;
    }

    public Judgement(String accessToken, RequestQueue queue, SwipeScreen swipeScreen) {
        this.accessToken = accessToken;
        this.queue = queue;
        this.swipeScreen = swipeScreen;
        this.profiles = new ArrayList<Profile>();
        //this.adapter = new ArrayAdapter<>(this.swipeScreen, R.layout.item,R.id.firstName, this.swipeScreen.al);
        this.myAdapter = new MyAppAdapter(this.swipeScreen,this.profiles);
    }

    public void requestProfiles(final String accessToken){
        //this.profiles = new ArrayList<Profile>();
        this.profile = new Profile();
        String url = "https://freeskill.ddns.net/user/searchProfiles";
        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,this,this) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-access-token", accessToken);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        System.out.println("DA " + profiles.size());
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{
            //this.swipeScreen.progressBar.setVisibility(View.GONE);
            String success = response.getString("success");
            if(success.equals("true")){
                JSONArray allProfiles = response.getJSONArray("message");
                for(int i = 0; i<allProfiles.length();i++){
                    Profile p = new Profile();
                    p.setFirstname(allProfiles.getJSONObject(i).getString("first_name"));
                    p.setAverageMark(allProfiles.getJSONObject(i).getDouble("average_mark"));
                    p.setDescription(allProfiles.getJSONObject(i).getString("description"));
                    p.setTagDiscover(allProfiles.getJSONObject(i).getString("tags_discover"));
                    p.setTagShare(allProfiles.getJSONObject(i).getString("tags_share"));
                    p.setAssos(allProfiles.getJSONObject(i).getInt("is_assos"));
                    //A RAJOUTER
                    //id
                    //perimeter

                    System.out.println(p.getDescription());

                    profiles.add(p);
                }
                System.out.println("oki " + profiles.size());
                for(int i = 0;i<this.profiles.size();i++){
                    this.swipeScreen.al.add(this.profiles.get(i));
                    System.out.println("AL SIZE : "  + this.swipeScreen.al.size());
                    System.out.println("AL FN : "  + this.swipeScreen.al.get(i));

                }

                //this.adapter.notifyDataSetChanged();
                this.myAdapter.notifyDataSetChanged();



            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}

