package com.example.edwinmperazaduran.simpletodo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

//import java.util.Date;
import java.util.List;
import java.util.Date;

/**
 * Created by edwinmperazaduran on 21/6/15.
 */
@Table(name = "TodoItems")
public class TodoItem extends Model {
        @Column(name = "Name")
        public String name;
        @Column(name = "DueDate")
        public Date dueDate;

        public TodoItem() {
            super();
        }

    public void setName(String name) {
        this.name = name;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public TodoItem(String name, Date dueDate) {
            super();
            this.name = name;
            this.dueDate = dueDate;
        }

    public static List<TodoItem> getAll() {
        return new Select().from(TodoItem.class).orderBy("ID ASC").execute();
    }

}