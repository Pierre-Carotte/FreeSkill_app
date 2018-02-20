package freeskill.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.utils.Tools;

/**
 * Created by Olivier on 22/12/2017.
 */

public class UserRegistrationScreen extends AppCompatActivity {
    CurrentApp currentApp = CurrentApp.getInstance(null);

    EditText editText_first_name;
    EditText editText_last_name;
    EditText editText_email;
    EditText editText_password;
    EditText editText_password_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_registration);

        editText_first_name = this.findViewById(R.id.editText_first_name);
        editText_last_name = this.findViewById(R.id.editText_last_name);
        editText_email = this.findViewById(R.id.editText_email);
        editText_password = this.findViewById(R.id.editText_password);
        editText_password_confirmation = this.findViewById(R.id.editText_password_confirmation);

        ConstraintLayout rootView = findViewById(R.id.user_registration_view);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.hideKeyboard(getApplicationContext(), view);
            }
        });
    }

    public void register(View view) {
        if(editText_first_name.getText().toString().isEmpty() ||
                editText_last_name.getText().toString().isEmpty() ||
                editText_email.getText().toString().isEmpty() ||
                editText_password.getText().toString().isEmpty() ||
                editText_password_confirmation.getText().toString().isEmpty()) {
            Snackbar.make(view, "Veuillez remplir tous les champs",
                    Snackbar.LENGTH_LONG).show();
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(editText_email.getText().toString())
                .matches()) {
            Snackbar.make(view, "Veuillez écrire un email correct",
                    Snackbar.LENGTH_LONG).show();
        }else if ((editText_password.getText().toString()).equals(editText_password_confirmation
                .getText().toString()) && !editText_password.getText().toString().isEmpty() &&
                !editText_password_confirmation.getText().toString().isEmpty()) {
            this.currentApp.userRegistration(editText_first_name.getText().toString(),
                    editText_last_name.getText().toString(), editText_email.getText().toString(),
                    editText_password.getText().toString());
            Snackbar.make(view, "Inscription en cours.",
                    Snackbar.LENGTH_LONG).show();
            Intent intentHomePage = new Intent(this, HomepageScreen.class);
            intentHomePage.putExtra("EMAIL", this.editText_email.getText().toString());
            this.startActivity(intentHomePage);
            this.finish();
        } else {
            Snackbar.make(view, "Les mots de passe ne sont pas identiques.",
                    Snackbar.LENGTH_LONG).show();
        }


        System.out.println(editText_first_name.getText().toString());
        System.out.println(editText_last_name.getText().toString());
        System.out.println(editText_email.getText().toString());
        System.out.println(editText_password.getText().toString());
        System.out.println(editText_password_confirmation.getText().toString());
    }
}
