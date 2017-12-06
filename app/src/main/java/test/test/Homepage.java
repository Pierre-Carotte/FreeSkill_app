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

public class Homepage extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "com.example.test.EMAIL";
    public static final String EXTRA_PASSWORD = "com.example.test.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        ConstraintLayout rootView = findViewById(R.id.rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.hideKeyboard(getApplicationContext(),view);
            }
        });

    }

    public void connection(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        EditText emailField = findViewById(R.id.email);
        String email = emailField.getText().toString();
        intent.putExtra(EXTRA_EMAIL, email);

        EditText passField = findViewById(R.id.password);
        String password = passField.getText().toString();
        intent.putExtra(EXTRA_PASSWORD, password);

        final String loginTxt = emailField.getText().toString();
        final String pwdTxt = passField.getText().toString();


        //Verification of email & password fields
        //Display a toast if one of the connect fields is empty
        if(loginTxt.equals("") && pwdTxt.equals("")){
            Toast.makeText(Homepage.this,"Aucun champ renseigné",Toast.LENGTH_LONG).show();
            return;
        }
        if(loginTxt.equals("")){
            emailField.setError("Email required!");
            //Toast.makeText(Homepage.this,"Email vide",Toast.LENGTH_LONG).show();
            return;
        }
        if(pwdTxt.equals("")){
            passField.setError("Pwd required!");
            Toast.makeText(Homepage.this,"Mot de passe vide",Toast.LENGTH_LONG).show();
            return;
        }
        //Check if email field have the good pattern
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(loginTxt);
        if(!m.matches()){
            Toast.makeText(Homepage.this,"Format incorrect pour l'email",Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(intent);
    }

    public void register(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
