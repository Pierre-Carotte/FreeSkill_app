package freeskill.app.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import freeskill.app.R;

/**
 * Created by Olivier on 06/12/2017.
 */

public class ProfileScreen extends AppCompatActivity {

    private ImageView imageView;
    private TextView textview_description;
    private TextView textview_tags_share;
    private TextView textview_tags_discover;

    private RequestQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        this.queue = Volley.newRequestQueue(this);

        this.imageView = this.findViewById(R.id.imageView);
        this.textview_description = this.findViewById(R.id.textview_description);
        this.textview_tags_share = this.findViewById(R.id.textview_tags_share);
        this.textview_tags_discover = this.findViewById(R.id.textview_tags_discover);

        this.showPicture();

    }

    public void edit_tags_share(View view){
        Toast.makeText(ProfileScreen.this,"Edit tags share",Toast.LENGTH_LONG).show();
    }

    public void edit_tags_discover(View view){
        Toast.makeText(ProfileScreen.this,"Edit tags discover",Toast.LENGTH_LONG).show();
    }

    public void showPicture(){
        String url = "https://freeskill.ddns.net/user/GetImage";
        ImageRequest imageRequest = new ImageRequest(
                url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                System.out.println(response);
                imageView.setImageBitmap(response);
            }
        },0,0, ImageView.ScaleType.CENTER_CROP,null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(imageRequest);
    }
}
