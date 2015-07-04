package com.example.edwinmperazaduran.simpletodo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends FragmentActivity implements EditItemDialog.EditItemDialogListener,
EraseItemDialog.EraseItemDialogListener, CreateItemDialog.CreateItemDialogListener{

    ArrayList<TodoItem> items;
    ArrayAdapter<TodoItem> itemsAdapter;
    ListView lvItems;
    EditText etNewItem;
    private final static int EDIT_ITEM_REQUEST = 1;
    DatePicker datePicker;
    Date dueDate;
    private int itemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        etNewItem = (EditText) findViewById(R.id.etEditItem);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        readItems();
        itemsAdapter = new TodoItemAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }

    private void showEditDialog(TodoItem item, Integer pos) {
        FragmentManager fm = getSupportFragmentManager();
        EditItemDialog editItemDialog = EditItemDialog.newInstance("Edit Item",item, pos);
        editItemDialog.show(fm, "fragment_edit_item");
    }


    private void showEraseDialog() {
        DialogFragment fm = new EraseItemDialog();
        fm.show(getSupportFragmentManager(), "eraseItems");
    }

    private void showCreateDialog(){
        FragmentManager fm = getSupportFragmentManager();
        CreateItemDialog createItemDialog = CreateItemDialog.newInstance("Create Item");
        createItemDialog.show(fm, "fragment_create_item");
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                itemPosition = pos;
                showEraseDialog();
                return true;
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoItem item = items.get(position);
                Integer pos = position;
                showEditDialog(item, pos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onAddItem(View view) {
       /* String itemText = etNewItem.getText().toString();
        GregorianCalendar c = new GregorianCalendar(datePicker.getYear(),datePicker.getMonth(),
                                                    datePicker.getDayOfMonth());
        dueDate = c.getTime();
        if (!itemText.trim().isEmpty()) {
            TodoItem item = new TodoItem(itemText,dueDate,"Low");
            item.save();
            items.clear();
            items.addAll(TodoItem.getAll());
            itemsAdapter.notifyDataSetChanged();
        }
        etNewItem.setText("");
        hideKeyboard();*/

        showCreateDialog();
    }

    private void readItems(){
        items = new ArrayList<TodoItem>(TodoItem.getAll());
    }

    @Override
    public void onEditFinished(int itemPosition, String itemText, Date dueDate, String priority) {
        if (!itemText.trim().isEmpty()) {
            TodoItem item = items.get(itemPosition);
            item.setName(itemText);
            item.setDueDate(dueDate);
            item.setPriority(priority);
            itemsAdapter.notifyDataSetChanged();
            item.save();

        }
    }
    @Override
    public void onCreateFinished(String itemText, Date datePicker, String priority){
        if (!itemText.trim().isEmpty()) {
            TodoItem item = new TodoItem(itemText, datePicker, priority);
            item.save();
          /*  item.setName(itemText);
            item.setDueDate(datePicker);
            item.setPriority(priority);
          //  Log.i("Date: ", datePicker.toString());*/
            items.clear();
            items.addAll(TodoItem.getAll());
            itemsAdapter.notifyDataSetChanged();

        }
    }

    private void hideKeyboard (){
        InputMethodManager imm = (InputMethodManager)getSystemService(
            Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etNewItem.getWindowToken(), 0);
    }

    @Override
    public void onDialogClick(boolean b) {
        if (b){
            TodoItem item = items.remove(itemPosition);
            itemsAdapter.notifyDataSetChanged();
            item.delete();
        }else{
            itemPosition = -1;
        }
    }

}

