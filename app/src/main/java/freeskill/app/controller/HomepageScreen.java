package freeskill.app.controller;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.DataConnection;
import freeskill.app.model.query.CurrentProfileQuery;
import freeskill.app.model.query.GetConnection;
import freeskill.app.utils.JWTUtils;
import freeskill.app.utils.Tools;

public class HomepageScreen extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "com.example.test.EMAIL";
    public static final String EXTRA_PASSWORD = "com.example.test.PASSWORD";
    public static final String EXTRA_TOKEN = "com.example.test.TOKEN";

    private Intent intentSwipeScreen;

    public Intent getIntentSwipeScreen() {
        return intentSwipeScreen;
    }

    private Intent intentRegisterScreen;

    private EditText emailField;
    private EditText passField;

    private String email;
    private String password;

    private RequestQueue queue;
    private CurrentApp app;
    private CurrentProfileQuery currentprofile;
    private GetConnection connection;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        queue = Volley.newRequestQueue(this);

        this.app = CurrentApp.getInstance(this.queue);

        this.connection = new GetConnection(this);

        ConstraintLayout rootView = findViewById(R.id.rootView);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.hideKeyboard(getApplicationContext(), view);
            }
        });

        this.sharedPreferences = getPreferences(MODE_PRIVATE);

        this.editor = sharedPreferences.edit();

        this.emailField = findViewById(R.id.email);
        this.passField = findViewById(R.id.password);

        String email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password", null);

        this.emailField.setText(email);
        this.passField.setText(password);

        //Persistent connection
        //Test if the token is available
        String jwt = DataConnection.getInstance().getJWT();
        if (null != jwt && "" != jwt) {
            //test if token is valid
            try {
                JWTUtils.decoded(jwt);
                Log.d("token: ", jwt);
                Intent intent = new Intent(this, SwipeScreen.class);
                startActivity(intent);

                //destroy the activity after sign in
                this.finish();
            } catch (Exception e) {
                Log.d("test Token", "no token");
            }
        }

        /*CheckConnectionDialogFragment.newInstance().show(this.getSupportFragmentManager(),
                "Absence de connexion internet");*/
    }

    public void connection(View view) {
        this.intentSwipeScreen = new Intent(this, SwipeScreen.class);

        this.email = emailField.getText().toString();
        intentSwipeScreen.putExtra(EXTRA_EMAIL, email);

        this.password = passField.getText().toString();
        intentSwipeScreen.putExtra(EXTRA_PASSWORD, password);

        final String loginTxt = emailField.getText().toString();
        final String pwdTxt = passField.getText().toString();

        editor.putString("email", this.email);
        editor.putString("password", this.password);
        editor.apply();


        if (!isConnected()) {
            Snackbar.make(view, "Aucune connexion à internet.", Snackbar.LENGTH_LONG).show();
            return;
        }

        //à mettre à la fin de la méthode
        this.app.createConnection(this);
        this.app.getConnection(email, password);

        //Verification of email & password fields
        //Display a toast if one of the connect fields is empty
        if (loginTxt.equals("") && pwdTxt.equals("")) {
            Toast.makeText(HomepageScreen.this, "Aucun champ renseigné",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (loginTxt.equals("")) {
            emailField.setError("Email required!");
            //Toast.makeText(HomepageScreen.this,"Email vide",Toast.LENGTH_LONG).show();
            return;
        }
        if (pwdTxt.equals("")) {
            passField.setError("Pwd required!");
            Toast.makeText(HomepageScreen.this, "Mot de passe vide",
                    Toast.LENGTH_LONG).show();
            return;
        }
        //Check if email field have the good pattern
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(loginTxt);
        if (!m.matches()) {
            Toast.makeText(HomepageScreen.this, "Format incorrect pour l'email",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void register(View view) {
        this.intentRegisterScreen = new Intent(this, UserRegistrationScreen.class);
        startActivity(this.intentRegisterScreen);
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
