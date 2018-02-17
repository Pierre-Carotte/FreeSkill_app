package freeskill.app.utils;

import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import freeskill.app.FreeskillApplication;
import freeskill.app.R;
import freeskill.app.controller.HomepageScreen;

/**
 * Created by Olivier on 15/02/2018.
 */

public class CheckConnectionDialogFragment extends AppCompatDialogFragment {

    public static CheckConnectionDialogFragment newInstance() {
        CheckConnectionDialogFragment dialog = new CheckConnectionDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", 20);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Absence de connexion internet")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Kill the application process
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}