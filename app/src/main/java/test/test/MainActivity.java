package test.test;


import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import test.test.utils.Tools;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "com.example.test.EMAIL";
    public static final String EXTRA_PASSWORD = "com.example.test.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout rootView = (ConstraintLayout) findViewById(R.id.RootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.hideKeyboard(getApplicationContext(),view);
            }
        });

    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText emailField = (EditText) findViewById(R.id.email);
        String email = emailField.getText().toString();
        intent.putExtra(EXTRA_EMAIL, email);

        EditText passField = (EditText) findViewById(R.id.password);
        String password = passField.getText().toString();
        intent.putExtra(EXTRA_PASSWORD, password);

        final String loginTxt = emailField.getText().toString();
        final String passTxt = passField.getText().toString();


        //Verification of email & password fields
        //Check if email field have the good pattern
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(loginTxt);
        if(!m.matches()){
            Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
            return;
        }
        //Display a toast if one of the connect fields is empty
        if(loginTxt.equals("") || passTxt.equals("")){
            Toast.makeText(MainActivity.this,"ERROR: champs vide",Toast.LENGTH_LONG).show();
            return;
        }

        startActivity(intent);
    }
}
