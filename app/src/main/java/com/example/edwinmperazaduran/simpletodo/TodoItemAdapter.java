package com.example.edwinmperazaduran.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * TodoItem Array adapter
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    private Context context;
    private static DateFormat df = DateFormat.getDateInstance();
    private SimpleDateFormat month = new SimpleDateFormat("MMMM") ;
    private SimpleDateFormat day = new SimpleDateFormat("d");

    public TodoItemAdapter(Context context, ArrayList<TodoItem> items) {
        super(context,0, items);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem item = (TodoItem) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,null);
        }
        TextView itemView = (TextView) convertView.findViewById(R.id.tvTodoItem);
        TextView dateMonthView = (TextView) convertView.findViewById(R.id.tvDueMonth);
        TextView dateDayView = (TextView) convertView.findViewById(R.id.tvDueDay);
        TextView priorityView = (TextView) convertView.findViewById(R.id.tvPriority);

        if (item != null) {
            itemView.setText(item.getName());
            dateMonthView.setText(month.format(item.getDueDate()));
            priorityView.setText(item.getPriority());
            dateDayView.setText(day.format(item.getDueDate()));
        }
        return convertView;
    }
}
