package freeskill.app.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import freeskill.app.R;
import freeskill.app.controller.ChatListActivity;
import freeskill.app.controller.HomepageScreen;
import freeskill.app.controller.ProfileScreen;
import freeskill.app.controller.SettingsScreen;
import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.query.CurrentProfileQuery;

public class DisplayMessageActivity extends AppCompatActivity {

    private CurrentApp app;
    private CurrentProfileQuery currentprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_connexion);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        String email = intent.getStringExtra(HomepageScreen.EXTRA_EMAIL);
        // Capture the layout's TextView and set the string as its text
        TextView emailView = findViewById(R.id.email_display);
        emailView.setText(email);

        String password = intent.getStringExtra(HomepageScreen.EXTRA_PASSWORD);
        // Capture the layout's TextView and set the string as its text
        TextView passwordView = findViewById(R.id.password_display);
        passwordView.setText(password);

        String token = intent.getStringExtra(HomepageScreen.EXTRA_TOKEN);
        // Capture the layout's TextView and set the string as its text
        TextView tokenView = findViewById(R.id.token_display);
        tokenView.setText(token);

        //this.app = CurrentApp.getInstance(null);
        //this.app.createProfileEditor().createCurrentProfile();
    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfileScreen.class);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsScreen.class);
        startActivity(intent);
    }

    public void swipe(View view) {
        Intent intent = new Intent(this, SwipeScreen.class);
        startActivity(intent);
    }

    public void chatList(View view) {
        CurrentApp ca = CurrentApp.getInstance(null);
        Log.d("chatlist", "Get chatList");
        Intent intent = new Intent(this, ChatListActivity.class);
        //intent.
        Log.d("chatlist", "Intent ok");
        startActivity(intent);

        /*Intent intent1 = new Intent(this, ChatActivity.class);

        startActivity(intent1);*/

    }

}