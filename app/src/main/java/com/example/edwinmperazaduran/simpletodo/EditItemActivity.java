package com.example.edwinmperazaduran.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private EditText etEditItem;
    private String item;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etEditItem = (EditText) findViewById(R.id.etEditItem);
        Intent i = getIntent();
        item     = i.getStringExtra("item");
        pos      = i.getIntExtra("pos",0);
        etEditItem.setText(item);
        etEditItem.setSelection(item.length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEditItem(View view) {
        Intent data = new Intent();
        data.putExtra("item", etEditItem.getText().toString());
        data.putExtra("pos", pos);
        setResult(RESULT_OK, data);
        finish();
    }
}
