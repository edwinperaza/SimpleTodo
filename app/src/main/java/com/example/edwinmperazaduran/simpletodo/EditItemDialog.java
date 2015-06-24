package com.example.edwinmperazaduran.simpletodo;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by edwinmperazaduran on 24/6/15.
 */
public class EditItemDialog extends DialogFragment {

    private EditText mEditText;
    private Button btnSave;

    public interface EditItemDialogListener {
        void onEditFinished(int itemPosition, String itemText);
    }

    public EditItemDialog() {
        // Empty constructor required for DialogFragment
    }

    public static EditItemDialog newInstance(String title, TodoItem editItem, Integer itemPos) {
        EditItemDialog frag = new EditItemDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("editText", editItem.name);
        args.putInt("position", itemPos);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_item, container);
        mEditText = (EditText) view.findViewById(R.id.etEditItem);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        String title = getArguments().getString("title", "Enter Name");
        String editItem = getArguments().getString("editText", "NaN");

        getDialog().setTitle(title);
        mEditText.setText(editItem);
        mEditText.selectAll();
        // Show soft keyboard automatically
        mEditText.requestFocus();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(v);
            }
        });
        return view;
    }


    public void finishEditing(View v) {
        boolean isEmpty = mEditText.getText().toString().trim().isEmpty();
        if (isEmpty) {
            dismiss();
            return;
        }
        String itemText = mEditText.getText().toString();
        Integer itemPosition = getArguments().getInt("position");
        EditItemDialogListener listener = (EditItemDialogListener)getActivity();
        listener.onEditFinished(itemPosition, itemText);
        dismiss();
    }
}
