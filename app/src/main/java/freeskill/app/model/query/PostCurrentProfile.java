package freeskill.app.model.query;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.FreeskillApplication;
import freeskill.app.R;
import freeskill.app.model.DataConnection;
import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 14/02/2018.
 */

public class PostCurrentProfile extends HttpsQuery {

    public void postCurrentProfileImage(RequestQueue queue) {
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        System.out.println(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        System.out.println(response);
    }
}
