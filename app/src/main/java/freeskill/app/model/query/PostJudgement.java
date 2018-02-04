package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.CurrentApp;

/**
 * Created by Florian on 02/02/2018.
 */

public class PostJudgement implements Response.Listener<String>, Response.ErrorListener{

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

    public void post(final String accessToken){
        String url = "https://freeskill.ddns.net/user/SetJudgement?"+ "judged=" + this.swipeScreen.al.get(0).getId()
                + "&meet="+ this.swipeScreen.meet;
        System.out.println(url);
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url,this,this){
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-access-token", accessToken);
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
    public void onResponse(String response) {
        //Response
        Log.d("Response", response);
    }


}