package freeskill.app.model.query;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import freeskill.app.controller.ChatActivity;
import freeskill.app.model.CurrentApp;
import freeskill.app.utils.Constants;

/**
 * Created by Sofiane-e on 18/02/2018.
 */

public class PutReportUser extends HttpsQuery{
    private static final PutReportUser ourInstance = new PutReportUser();
    private final RequestQueue queue;
    private ChatActivity chatActivity;
    public static PutReportUser getInstance() {
        return ourInstance;
    }

    private PutReportUser() {
        CurrentApp currentApp = CurrentApp.getInstance(null);
        queue = currentApp.getQueue();
    }

    public void request(ChatActivity chatActivity, int idUser) {
        this.chatActivity = chatActivity;
        String requestURI = Constants.API.ReportUser.URI + idUser ;
        JsonObjectRequest stringR = new CustomJsonObjectRequest(Constants.API.ReportUser.METHOD,
                requestURI,
                null, this, this);
        queue.add(stringR);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Error ReportUser", error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        chatActivity.notifyReportUser();
    }
}
