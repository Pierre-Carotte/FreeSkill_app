package freeskill.app.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import freeskill.app.controller.HomepageScreen;
import freeskill.app.R;
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
}