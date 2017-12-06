package test.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_connexion);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        String email = intent.getStringExtra(Homepage.EXTRA_EMAIL);
        // Capture the layout's TextView and set the string as its text
        TextView emailView = findViewById(R.id.email_display);
        emailView.setText(email);

        String password = intent.getStringExtra(Homepage.EXTRA_PASSWORD);
        // Capture the layout's TextView and set the string as its text
        TextView passwordView = findViewById(R.id.password_display);
        passwordView.setText(password);

        String token = intent.getStringExtra(Homepage.EXTRA_TOKEN);
        // Capture the layout's TextView and set the string as its text
        TextView tokenView = findViewById(R.id.token_display);
        tokenView.setText(token);

    }
}