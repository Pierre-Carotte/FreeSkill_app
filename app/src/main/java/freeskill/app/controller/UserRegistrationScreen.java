package freeskill.app.controller;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.ProfileEditor;
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

        public void register(View view){
            if((editText_password.getText().toString()).equals(editText_password_confirmation.getText().toString())){
                this.currentApp.userRegistration(editText_first_name.getText().toString(),
                        editText_last_name.getText().toString(), editText_email.getText().toString(),
                        editText_password.getText().toString());
                lockEditText();
                Snackbar.make(view, "Inscription en cours.",
                        Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(view, "Les mots de passe ne sont pas identiques.",
                        Snackbar.LENGTH_LONG).show();
            }


            System.out.println(editText_first_name.getText().toString());
            System.out.println(editText_last_name.getText().toString());
            System.out.println(editText_email.getText().toString());
            System.out.println(editText_password.getText().toString());
            System.out.println(editText_password_confirmation.getText().toString());
        }

        public void lockEditText(){
            editText_first_name.setFocusableInTouchMode(false);
            editText_first_name.setClickable(false);
            editText_first_name.setCursorVisible(false);
            editText_last_name.setFocusableInTouchMode(false);
            editText_last_name.setClickable(false);
            editText_last_name.setCursorVisible(false);
            editText_email.setFocusableInTouchMode(false);
            editText_email.setClickable(false);
            editText_email.setCursorVisible(false);
            editText_password.setFocusableInTouchMode(false);
            editText_password.setClickable(false);
            editText_password.setCursorVisible(false);
            editText_password_confirmation.setFocusableInTouchMode(false);
            editText_password_confirmation.setClickable(false);
            editText_password_confirmation.setCursorVisible(false);
        }
}
