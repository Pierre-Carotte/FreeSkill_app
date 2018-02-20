package freeskill.app.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by Olivier on 15/02/2018.
 */

public class CheckConnectionDialogFragment extends AppCompatDialogFragment {

    public static CheckConnectionDialogFragment newInstance() {
        CheckConnectionDialogFragment dialog = new CheckConnectionDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", 20);
        dialog.setArguments(args);
        dialog.setCancelable(false);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Absence de connexion internet")
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