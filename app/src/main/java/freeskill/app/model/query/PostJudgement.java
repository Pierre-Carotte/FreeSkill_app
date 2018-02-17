package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.DataConnection;
import freeskill.app.utils.Constants;

/**
 * Created by Florian on 02/02/2018.
 */

public class PostJudgement extends HttpsQuery{

    private Judgement judgement;
    private String accessToken;
    private RequestQueue queue;
    private SwipeScreen swipeScreen;
    private CurrentApp currentApp;

    public PostJudgement(String accessToken, RequestQueue queue, SwipeScreen swipeScreen)  {
        this.accessToken=accessToken;
        this.queue = queue;
        this.swipeScreen = swipeScreen;
        this.currentApp = CurrentApp.getInstance(null);
    }

    public void post(){
        String url = Constants.API.SetJudgement.URI + "judged=" + this.swipeScreen.al.get(0).getId()
                + "&meet="+ this.swipeScreen.meet;
        System.out.println(url);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.PUT, url,null,this,this){
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT());
                return headers;
            }
        };


        // Add the request to the RequestQueue.
        queue.add(postRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Error.Response", String.valueOf(error));
    }

    @Override
    public void onResponse(JSONObject response) {
        //Response
        Log.d("Response", response.toString());
        System.out.println(response);
        try {
            if (response.getInt("match") == 1) {
                this.swipeScreen.makeToast(this.swipeScreen, "MATCH");
            }
        }catch(Exception e){

        }
    }
}