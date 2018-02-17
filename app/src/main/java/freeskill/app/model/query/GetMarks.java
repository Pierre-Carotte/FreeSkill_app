package freeskill.app.model.query;

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

import freeskill.app.controller.ListMarksScreen;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.DataConnection;
import freeskill.app.model.Mark;
import freeskill.app.model.Profile;
import freeskill.app.model.adapters.MarksAdapter;
import freeskill.app.utils.Constants;

/**
 * Created by Florian on 12/02/2018.
 */

public class GetMarks implements Response.Listener<JSONObject>, Response.ErrorListener {

    private RequestQueue queue;
    private String accessToken;
    private ListMarksScreen listMarksScreen;
    private CurrentApp currentApp;
    private ArrayList<Mark> markArrayList;
    private Judgement judgement;
    private Profile profile;
    private MarksAdapter adapter;

    public MarksAdapter getAdapter() {
        return adapter;
    }

    public GetMarks(String accessToken, RequestQueue queue, ListMarksScreen listMarksScreen) {

        this.accessToken = accessToken;
        this.queue = queue;
        this.listMarksScreen = listMarksScreen;
        //this.myAdapter = new MyAppAdapter(this.swipeScreen,-1,this.profiles);
        this.markArrayList = new ArrayList<>();
        this.adapter = new MarksAdapter(this.listMarksScreen, markArrayList);
        this.currentApp = CurrentApp.getInstance(null);
    }

    public void getMarks(String accessToken) {
        String url = "";
        if (this.listMarksScreen.getIdProfile() != 0) {
            url = Constants.API.GetMarks.URI + this.listMarksScreen.getIdProfile();
        } else {
            url = Constants.API.GetMarks.URI;
        }
        System.out.println("url :: " + url);
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
            String success = response.getString("success");
            if (success.equals("true")) {
                JSONArray allMarks = response.getJSONArray("message");
                for (int i = 0; i < allMarks.length(); i++) {
                    Mark mark = new Mark();
                    JSONArray jsonArrayMarks = allMarks.getJSONObject(i).getJSONArray("marks");
                    mark.setTag(allMarks.getJSONObject(i).getString("tag_name"));
                    mark.setMark_average((float) allMarks.getJSONObject(i).getDouble("average_mark"));
                    //TODO : OTHER PARAMETER
                    for (int j = 0; j < jsonArrayMarks.length(); j++) {
                        mark.setMark(jsonArrayMarks.getJSONObject(j).getInt("mark"));
                        //TODO : Wait merge for put date
                        //mark.setDate(jsonArrayMarks.getJSONObject(j).getString(""));
                        System.out.println("mark :: " + mark.getMark());
                    }
                    mark.setNbMark(jsonArrayMarks.length());
                    System.out.println("nb_MARK :: " + jsonArrayMarks.length());

                    markArrayList.add(mark);
                }
                this.adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
