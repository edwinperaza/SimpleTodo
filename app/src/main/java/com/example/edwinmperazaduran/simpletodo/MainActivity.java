package com.example.edwinmperazaduran.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    //ArrayList<String> items;
    ArrayList<TodoItem> items;
    //TodoItemAdapter itemsAdapter;
    ArrayAdapter<TodoItem> itemsAdapter;
    ListView lvItems;
    EditText etNewItem;
    private final static int EDIT_ITEM_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        etNewItem = (EditText) findViewById(R.id.etEditItem);
        readItems();
        itemsAdapter = new TodoItemAdapter(this, android.R.layout.simple_expandable_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();

    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                TodoItem item = items.remove(pos);
                //items.remove(pos);
                itemsAdapter.notifyDataSetChanged();
                item.delete();
                //writeItems();
                return true;
            }




        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("item", items.get(position).name);
                intent.putExtra("pos", position);
                startActivityForResult(intent, EDIT_ITEM_REQUEST);
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
        String itemText = etNewItem.getText().toString();
        /*  itemsAdapter.add(itemText);
            etNewItem.setText("");
            writeItems();*/

            TodoItem item = new TodoItem(itemText);
            itemsAdapter.add(item);
            item.save();
    }

    private void readItems(){
        /*File filesDir = getFilesDir(); //Access for special directory for this android app
        File todoFile = new File(filesDir, "todo.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        }catch (IOException e){
            items = new ArrayList<String>();

        }*/
        items = new ArrayList<TodoItem>(TodoItem.getAll());
    }

    /*private void writeItems(){
        File fileDir = getFilesDir();
        File todoFile = new File(fileDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e){
            e.printStackTrace();
        }
    }*/
    @Override
    protected void  onActivityResult (int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK && requestCode == EDIT_ITEM_REQUEST) {
            // Extract name value from result extras
            String itemText = data.getExtras().getString("item");
            int pos = data.getExtras().getInt("pos", 0);
            //items.set(pos, itemText);
            TodoItem item = items.get(pos);
            item.name = itemText;
            itemsAdapter.notifyDataSetChanged();
            //writeItems();
            item.save();
        }
    };




}
