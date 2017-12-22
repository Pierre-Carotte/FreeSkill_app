package freeskill.app.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import freeskill.app.controller.ProfileScreen;
import freeskill.app.controller.SettingsScreen;
import freeskill.app.R;
import freeskill.app.controller.SwipeScreen;

/**
 * Created by Olivier on 06/12/2017.
 */

public class Test extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }

    public void settings(View view){
        Intent intent = new Intent(this, SettingsScreen.class);
        startActivity(intent);
    }

    public void profile(View view){
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void swipe (View view){
        Intent intent = new Intent(this,SwipeScreen.class);
        startActivity(intent);
    }
}
