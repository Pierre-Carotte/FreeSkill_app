package freeskill.app.model.query;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.Profile;
import freeskill.app.model.adapters.MyAppAdapter;

/**
 * Created by Florian on 23/01/2018.
 */

public class ImageRequestProfilesQuery implements Response.Listener<Bitmap>, Response.ErrorListener {

    private SwipeScreen swipeScreen;
    private Map map;
    private Profile profile;
    private Judgement judgement;
    private CurrentApp currentApp;
    private MyAppAdapter myAppAdapter;
    private int id;
    private OnImageLoaded onImageLoaded;

    public ImageRequestProfilesQuery(Judgement judgement, OnImageLoaded onImageLoaded) {
        this.judgement = judgement;
        this.onImageLoaded = onImageLoaded;
    }

    public void getImage(final String accessToken, RequestQueue queue, int id) {
        this.id = id;
        String url = "https://freeskill.ddns.net/user/GetImage/" + this.id;
        ImageRequest imageRequest = new ImageRequest(url, this, 0, 0, ImageView.ScaleType.CENTER_CROP, null, this) {
            //Add the accessToken in the headers of the request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("x-access-token", accessToken);
                return headers;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(imageRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
    }

    @Override
    public void onResponse(Bitmap response) {
        //this.judgement.getMyAdapter().getImages().put(this.id,response);
        this.onImageLoaded.onSuccess(response);
        System.out.println("IMAGE " + response);
    }

    public interface OnImageLoaded {
        void onSuccess(Bitmap bitmap);
    }
}
