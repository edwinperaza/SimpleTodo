package com.example.edwinmperazaduran.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import java.util.List;

/**
 * Created by edwinmperazaduran on 21/6/15.
 */
@Table(name = "TodoItems")
public class TodoItem extends Model {
        @Column(name = "Name")
        public String name;

        public TodoItem() {
            super();
        }

        public TodoItem(String name) {
            super();
            this.name = name;
        }

        public static List<TodoItem> getAll() {
            return new Select().from(TodoItem.class).orderBy("ID ASC").execute();
        }

}