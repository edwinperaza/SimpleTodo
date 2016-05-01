package com.example.edwinmperazaduran.simpletodo.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.edwinmperazaduran.simpletodo.R;

/**
 * Dialog Fragment to verify if an user want to erase an Item from the ToDo list.
 */
public class EraseItemDialog extends DialogFragment {

    public interface EraseItemDialogListener {
        public void onDialogClick(boolean b);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_erase_row)
                .setTitle(R.string.erase_item_dialog_title)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EraseItemDialogListener listener = (EraseItemDialogListener)getActivity();
                        listener.onDialogClick(true);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EraseItemDialogListener listener = (EraseItemDialogListener) getActivity();
                        listener.onDialogClick(false);
                    }
                });
        return builder.create();
    }
}