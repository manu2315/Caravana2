package calculatuesfuerzo.finsol.com.mx.calcula.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import calculatuesfuerzo.finsol.com.mx.calcula.R;

public class AlertDialogErrorFragment extends DialogFragment {


    private String message;
    private String title;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        message= getArguments() != null ? getArguments().getString(Constantes.MESSAGE_DIALOG_ERROR):"";

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.dialog_error)
                .setIcon(R.drawable.ic_close)
        /*builder.setMessage(R.string.dialog_message_client)
                .setTitle(R.string.dialog_error)
                .setIcon(R.drawable.ic_close)
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })*/
                .setNegativeButton(R.string.dialog_close, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        return;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}