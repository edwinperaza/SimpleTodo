package com.example.edwinmperazaduran.simpletodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by edwinmperazaduran on 21/6/15.
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    private Context context;
    private static DateFormat df = DateFormat.getDateInstance();

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
        TextView dateView = (TextView) convertView.findViewById(R.id.tvDueDate);
        TextView priorityView = (TextView) convertView.findViewById(R.id.tvPriority);

        if (item != null) {
                itemView.setText(item.getName());
                String dateStr = df.format(item.getDueDate());
                dateView.setText(dateStr+" ");
                priorityView.setText(" "+item.getPriority());
        }
        return convertView;
    }
}
