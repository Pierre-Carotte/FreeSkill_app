package freeskill.app.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import freeskill.app.FreeskillApplication;
import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.DataConnection;
import freeskill.app.model.ProfileEditor;
import freeskill.app.model.query.VolleyMultipartRequest;
import freeskill.app.utils.Constants;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE;

/**
 * Created by Olivier on 06/12/2017.
 */

public class ProfileScreen extends AppCompatActivity {

    private CurrentApp app;
    private ProfileEditor profileEditor;

    private ImageView imageView;
    private TextView textview_description;
    private TextView textview_tags_share;
    private TextView textview_tags_discover;
    private TextView textview_username;
    private RatingBar ratingBar_stars;
    private ImageView button_tags_share;
    private ImageView button_tags_discover;
    private ImageView button_description;
    private ProgressBar progressBar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        this.progressBar = findViewById(R.id.progressBar2);
        this.progressBar.setVisibility(View.VISIBLE);

        this.imageView = this.findViewById(R.id.imageView);
        this.textview_username = this.findViewById(R.id.textview_username);
        this.textview_description = this.findViewById(R.id.textview_description);
        this.textview_tags_share = this.findViewById(R.id.textview_tags_share);
        this.textview_tags_discover = this.findViewById(R.id.textview_tags_discover);
        this.ratingBar_stars = this.findViewById(R.id.ratingBar_stars);

        this.button_tags_share = this.findViewById(R.id.button_tags_share);
        this.button_tags_share.setEnabled(false);
        this.button_tags_discover = this.findViewById(R.id.button_tags_discover);
        this.button_tags_discover.setEnabled(false);
        this.button_description = this.findViewById(R.id.button_description);
        this.button_description.setEnabled(false);

        this.app = CurrentApp.getInstance(null);
        this.profileEditor = this.app.createProfileEditor();
        this.profileEditor.createCurrentProfile(this);


        this.ratingBar_stars.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(ProfileScreen.this, ListMarksScreen.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        addCustomActionBar();
    }

    public void addCustomActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_profilescreen);
        View view = getSupportActionBar().getCustomView();

        ImageView imageViewSettings = this.findViewById(R.id.action_bar_settings);
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileScreen.this.startActivity(new Intent(ProfileScreen.this,
                        SettingsScreen.class));
            }
        });

        ImageView imageViewBack = this.findViewById(R.id.action_bar_back);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileScreen.this.startActivity(new Intent(ProfileScreen.this,
                        SwipeScreen.class));
            }
        });
    }

    public void edit_tags_share(View view) {
        //startActivity(this.intentUnderConstruction);
        //Toast.makeText(ProfileScreen.this, "Edit tags share", Toast.LENGTH_LONG).show();
        this.button_tags_discover.setEnabled(false);
        this.button_description.setEnabled(false);

        this.textview_tags_share.setFocusableInTouchMode(true);
        this.textview_tags_share.setFocusable(true);
        this.textview_tags_share.setClickable(true);
        this.textview_tags_share.setCursorVisible(true);
        //this.textview_tags_share.setInputType(TYPE_TEXT_FLAG_MULTI_LINE);
        this.textview_tags_share.setEms(10);

        ProfileScreen.this.button_tags_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray tags_share_array = new JSONArray();
                String tags_share_string = ProfileScreen.this.textview_tags_share.getText().toString()
                        .replace(" ", "").replace("#", " ");
                String[] tags_share = tags_share_string.split(" ");
                ProfileScreen.this.profileEditor.getProfile().setTagShareArray(new ArrayList());
                for (String tag : tags_share) {
                    if(!tag.isEmpty()){
                        ProfileScreen.this.profileEditor.getProfile().setTagShare(tag);
                    }
                }
                for (Object tag : ProfileScreen.this.profileEditor.getProfile().getTagShareArray()){
                    tags_share_array.put(tag.toString());
                }
                ProfileScreen.this.profileEditor.updateCurrentProfile(Constants.JSONparameters.TAGS_SHARE,
                        tags_share_array);
                ProfileScreen.this.recreate();
            }
        });
    }

    public void edit_tags_discover(View view) {
        //startActivity(this.intentUnderConstruction);
        //Toast.makeText(ProfileScreen.this, "Edit tags discover", Toast.LENGTH_LONG).show();
        this.button_tags_share.setEnabled(false);
        this.button_description.setEnabled(false);

        this.textview_tags_discover.setFocusableInTouchMode(true);
        this.textview_tags_discover.setFocusable(true);
        this.textview_tags_discover.setClickable(true);
        this.textview_tags_discover.setCursorVisible(true);
        //this.textview_tags_share.setInputType(TYPE_TEXT_FLAG_MULTI_LINE);
        this.textview_tags_discover.setEms(10);

        ProfileScreen.this.button_tags_discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONArray tags_discover_array = new JSONArray();
                String tags_discover_string = ProfileScreen.this.textview_tags_discover.getText().toString()
                        .replace(" ", "").replace("#", " ");
                String[] tags_discover = tags_discover_string.split(" ");
                ProfileScreen.this.profileEditor.getProfile().setTagDiscoverArray(new ArrayList());
                for (String tag : tags_discover) {
                    if(!tag.isEmpty()){
                        ProfileScreen.this.profileEditor.getProfile().setTagDiscover(tag);
                    }
                }
                for (Object tag : ProfileScreen.this.profileEditor.getProfile().getTagDiscoverArray()){
                    tags_discover_array.put(tag.toString());
                }
                ProfileScreen.this.profileEditor.updateCurrentProfile
                        (Constants.JSONparameters.TAGS_DISCOVER, tags_discover_array);
                ProfileScreen.this.recreate();
            }
        });
    }

    public void edit_description(View view) {
        //startActivity(this.intentUnderConstruction);
        //Toast.makeText(ProfileScreen.this, "Edit description", Toast.LENGTH_LONG).show();
        this.button_tags_discover.setEnabled(false);
        this.button_tags_share.setEnabled(false);

        this.textview_description.setFocusableInTouchMode(true);
        this.textview_description.setFocusable(true);
        this.textview_description.setClickable(true);
        this.textview_description.setCursorVisible(true);
        //this.textview_tags_share.setInputType(TYPE_TEXT_FLAG_MULTI_LINE);
        this.textview_description.setEms(10);

        ProfileScreen.this.button_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileScreen.this.profileEditor.updateCurrentSettings("description",
                        ProfileScreen.this.textview_description.getText().toString());
                ProfileScreen.this.recreate();
            }
        });
    }


    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextview_description() {
        return textview_description;
    }

    public TextView getTextview_tags_share() {
        return textview_tags_share;
    }

    public TextView getTextview_tags_discover() {
        return textview_tags_discover;
    }

    public TextView getTextview_username() {
        return textview_username;
    }

    public RatingBar getRatingBar_stars() {
        return ratingBar_stars;
    }

    public ProgressBar getProgressBar(){
        return progressBar;
    }

    public ImageView getButton_tags_share() {
        return button_tags_share;
    }

    public ImageView getButton_tags_discover() {
        return button_tags_discover;
    }

    public ImageView getButton_description() {
        return button_description;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            //getting the image Uri
            Uri imageUri = data.getData();
            try {
                //getting bitmap object from uri
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                //displaying selected image to imageview
                imageView.setImageBitmap(bitmap);

                //calling the method uploadBitmap to upload image
                uploadBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void imageBrowse(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, 100);
    }

    private void uploadBitmap(final Bitmap bitmap) {

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST,
                Constants.API.SetImage.URI,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        Log.d("Response", String.valueOf(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", String.valueOf(error));
                    }
                }) {
            /*
            * Here we are passing image by renaming it with a unique name
            * */
            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("img", new DataPart("newImage.png", getFileDataFromDrawable(bitmap)));
                return params;
            }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT());
                    return headers;
            }
        };

        //adding the request to volley
        CurrentApp.getInstance(null).getQueue().add(volleyMultipartRequest);
    }

    /*
    * The method is taking Bitmap as an argument
    * then it will return the byte[] array for the given bitmap
    * and we will send this array to the server
    * here we are using PNG Compression with 80% quality
    * you can give quality between 0 to 100
    * 0 means worse quality
    * 100 means best quality
    * */
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}