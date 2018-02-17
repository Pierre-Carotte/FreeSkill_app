package freeskill.app.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.ProfileEditor;
import freeskill.app.test.UnderConstruction;

/**
 * Created by Olivier on 06/12/2017.
 */

public class ProfileScreen extends AppCompatActivity {

    private Intent intentUnderConstruction;

    private CurrentApp app;
    private ProfileEditor profileEditor;

    private ImageView imageView;
    private TextView textview_description;
    private TextView textview_tags_share;
    private TextView textview_tags_discover;
    private TextView textview_username;
    private RatingBar ratingBar_stars;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        this.intentUnderConstruction = new Intent(this, UnderConstruction.class);

        this.imageView = this.findViewById(R.id.imageView);
        this.textview_username = this.findViewById(R.id.textview_username);
        this.textview_description = this.findViewById(R.id.textview_description);
        this.textview_tags_share = this.findViewById(R.id.textview_tags_share);
        this.textview_tags_discover = this.findViewById(R.id.textview_tags_discover);
        this.ratingBar_stars = this.findViewById(R.id.ratingBar_stars);

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

    public void addCustomActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_profilescreen);
        View view =getSupportActionBar().getCustomView();

        ImageView imageViewSettings= this.findViewById(R.id.action_bar_settings);
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileScreen.this.startActivity(new Intent(ProfileScreen.this, SettingsScreen.class));
            }
        });

        ImageView imageViewBack= this.findViewById(R.id.action_bar_back);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileScreen.this.startActivity(new Intent(ProfileScreen.this, SwipeScreen.class));
            }
        });
    }

    public void edit_tags_share(View view){
        startActivity(this.intentUnderConstruction);
        Toast.makeText(ProfileScreen.this,"Edit tags share",Toast.LENGTH_LONG).show();
    }

    public void edit_tags_discover(View view){
        startActivity(this.intentUnderConstruction);
        Toast.makeText(ProfileScreen.this,"Edit tags discover",Toast.LENGTH_LONG).show();
    }

    public void edit_description(View view){
        startActivity(this.intentUnderConstruction);
        Toast.makeText(ProfileScreen.this,"Edit description",Toast.LENGTH_LONG).show();
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

}