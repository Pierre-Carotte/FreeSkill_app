package freeskill.app.model.query;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import freeskill.app.controller.ProfileScreen;

/**
 * Created by Olivier on 15/12/2017.
 */

public class ImageRequestQuery implements Response.Listener<Bitmap>, Response.ErrorListener{

    private ProfileScreen profileScreen;
    public ImageRequestQuery(ProfileScreen profileScreen) {
        this.profileScreen = profileScreen;
    }

    public void getImage(final String accessToken, RequestQueue queue){
        String url = "https://freeskill.ddns.net/user/GetImage";
        ImageRequest imageRequest = new ImageRequest(
                url, this,0,0, ImageView.ScaleType.CENTER_CROP,null, this){
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
        this.profileScreen.getImageView().setImageBitmap(response);
    }
}
