package test.test;


import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import test.test.utils.HttpsTrustManager;
import test.test.utils.Tools;

public class Homepage extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "com.example.test.EMAIL";
    public static final String EXTRA_PASSWORD = "com.example.test.PASSWORD";
    public static final String EXTRA_TOKEN = "com.example.test.TOKEN";

    private String jsonResponse;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        queue = Volley.newRequestQueue(this);

        InputStream caInput=getResources().openRawResource(R.raw.freeskillddnsnet);
        HttpsTrustManager https = new HttpsTrustManager(caInput);
        https.allowMySSL();

        ConstraintLayout rootView = findViewById(R.id.rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.hideKeyboard(getApplicationContext(),view);
            }
        });

    }

    public void sendMessage(View view){
        final Intent intent = new Intent(this, DisplayMessageActivity.class);

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
            Toast.makeText(Homepage.this,"Email vide",Toast.LENGTH_LONG).show();
            return;
        }
        if(pwdTxt.equals("")){
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

        String url = "https://freeskill.ddns.net/auth/connection?email=" + email + "&password=" + password;

        // Request a JSON response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String success = response.getString("success");
                            String message = response.getString("message");
                            if(success.equals("true")){
                                intent.putExtra(EXTRA_TOKEN, message);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Homepage.this, message ,Toast.LENGTH_SHORT).show();
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Homepage.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}
