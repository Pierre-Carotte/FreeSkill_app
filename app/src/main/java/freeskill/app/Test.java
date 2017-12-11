package freeskill.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import freeskill.app.Controller.ProfileScreen;
import freeskill.app.Controller.SettingsScreen;
import test.test.R;

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
}
