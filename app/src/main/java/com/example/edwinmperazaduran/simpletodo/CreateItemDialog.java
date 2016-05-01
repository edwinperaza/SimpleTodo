package com.example.edwinmperazaduran.simpletodo;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;
import java.util.GregorianCalendar;

import static android.widget.AdapterView.OnItemSelectedListener;

/**
 * Dialog Fragment to create an Item in the ToDo list.
 */
public class CreateItemDialog extends DialogFragment {

    private EditText mCreateText;
    private Button btnSave;
    private DatePicker datePicker;
    private Date dueDate;
    private TodoItem item;
    private Spinner sp_priority;
    private String priority;

    public interface CreateItemDialogListener {
        void onCreateFinished(String itemText, Date datePicker, String priority);
    }

    public CreateItemDialog() {}

    public static CreateItemDialog newInstance(String title) {
        CreateItemDialog frag = new CreateItemDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_item, container);

        mCreateText = (EditText) view.findViewById(R.id.etCreateItem);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        sp_priority = (Spinner) view.findViewById(R.id.sp_priority_create);

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        mCreateText.requestFocus();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                 R.array.sp_priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_priority.setAdapter(adapter);
        setupSpSpinnerListener();

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCreating(v);
            }
        });
        return view;
    }

    public void setupSpSpinnerListener (){
        sp_priority.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priority = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void finishCreating(View v) {
        boolean isEmpty = mCreateText.getText().toString().trim().isEmpty();
        if (isEmpty) {
            dismiss();
            return;
        }
        GregorianCalendar c = new GregorianCalendar(datePicker.getYear(),
                    datePicker.getMonth(), datePicker.getDayOfMonth());
        dueDate = c.getTime();
        String itemText = mCreateText.getText().toString();
        CreateItemDialogListener listener = (CreateItemDialogListener)getActivity();
        listener.onCreateFinished(itemText, dueDate, priority);
        dismiss();
    }
}
