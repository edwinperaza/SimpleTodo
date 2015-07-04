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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by edwinmperazaduran on 24/6/15.
 */
public class EditItemDialog extends DialogFragment {

    private EditText mEditText;
    private Button btnSave;
    private DatePicker datePicker;
    private Date dueDate;
    private TodoItem item;
    private Spinner sp_priority;
    private String priority;

    public interface EditItemDialogListener {
        void onEditFinished(int itemPosition, String itemText, Date datePicker, String priority);
    }

    public EditItemDialog() {
        // Empty constructor required for DialogFragment
    }

    public static EditItemDialog newInstance(String title, TodoItem editItem, Integer itemPos) {
        EditItemDialog frag = new EditItemDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("editText", editItem.getName());
        args.putString("priority", editItem.getPriority());
        args.putInt("position", itemPos);
        args.putSerializable("dueDate", editItem.getDueDate());
        //args.putSerializable("editItem", (Serializable) editItem);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_item, container);

        mEditText = (EditText) view.findViewById(R.id.etEditItem);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        //item = (TodoItem) getArguments().getSerializable("editItem");
        String title = getArguments().getString("title", "Enter Name");
        String edit = getArguments().getString("editText", "NaN");
        dueDate = (Date) getArguments().getSerializable("dueDate");
        getDialog().setTitle(title);
        mEditText.setText(edit);
        mEditText.selectAll();

        sp_priority = (Spinner) view.findViewById(R.id.sp_priority_edit);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                 R.array.sp_priority_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp_priority.setAdapter(adapter);
        sp_priority.setSelection(adapter.getPosition(getArguments().getString("priority")));
        setupSpSpinnerListener();



        // Show soft keyboard automatically
        mEditText.requestFocus();
        //datePicker.
        if (dueDate != null) {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(dueDate);
            datePicker.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                    c.get(Calendar.DATE));

        }


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

    public void finishEditing(View v) {
        boolean isEmpty = mEditText.getText().toString().trim().isEmpty();
        if (isEmpty) {
            dismiss();
            return;
        }
        if (dueDate != null){
            GregorianCalendar c = new GregorianCalendar(datePicker.getYear(),
                    datePicker.getMonth(), datePicker.getDayOfMonth());
            dueDate = c.getTime();
        }
        String itemText = mEditText.getText().toString();
        Integer itemPosition = getArguments().getInt("position");
        EditItemDialogListener listener = (EditItemDialogListener)getActivity();
        listener.onEditFinished(itemPosition, itemText, dueDate, priority);
        dismiss();
    }




}
