package com.example.edwinmperazaduran.simpletodo.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * TodoItem Model
 */
@Table(name = "TodoItems")
public class TodoItem extends Model {

    @Column(name = "Name")
    public String name;
    @Column(name = "DueDate")
    public Date dueDate;
    @Column(name = "Priority")
    public String priority;


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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public TodoItem(String name, Date dueDate, String priority) {
            super();
            this.name = name;
            this.dueDate = dueDate;
            this.priority = priority;
    }

    public static List<TodoItem> getAll() {
        return new Select().from(TodoItem.class).orderBy("DueDate ASC").execute();
    }
}