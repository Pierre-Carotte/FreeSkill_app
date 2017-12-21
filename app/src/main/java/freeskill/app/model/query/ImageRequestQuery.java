package freeskill.app.model.query;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Olivier on 15/12/2017.
 */

public class ImageRequestQuery {
    private ImageLoader mImageLoader;

    public void getImageRequest(RequestQueue queue){
        String url = "https://freeskill.ddns.net/user/GetImage";

        //ImageRequest imageRequest = new ImageRequest(url, this, 0,0,null, this);
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache(){
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
    }
}
