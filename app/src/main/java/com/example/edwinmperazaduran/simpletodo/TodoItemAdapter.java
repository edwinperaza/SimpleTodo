package com.example.edwinmperazaduran.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by edwinmperazaduran on 21/6/15.
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    private Context context;
    private int textViewResourceId;

    public TodoItemAdapter(Context context, int textViewResourceId, ArrayList<TodoItem> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(textViewResourceId,null);
        }
        TodoItem item = (TodoItem) getItem(position);
        if (item != null) {
            TextView itemView = (TextView) convertView.findViewById(android.R.id.text1);
            if (itemView != null) {
                itemView.setText(item.name);
            }
        }
        return convertView;

        /*
        TodoItem item = (TodoItem) getItem(position);
        if (item != null) {
            TextView itemView = (TextView) view.findViewById(android.R.id.text1);
            if (itemView != null) {
                itemView.setText(item.name);
            }
        }

        return view;*/
    }
}
