package com.example.tododaily.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.tododaily.database.DBHelper;
import com.example.tododaily.interfaces.NewTask;
import com.example.tododaily.model.Category;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class NewTaskPresenter {
   NewTask newTask;
    DBHelper dbHelper;
    public NewTaskPresenter(NewTask newTask, Context context) {
        this.newTask = newTask;
        dbHelper = new DBHelper(context);
    }
    public void navigate(){
        newTask.navigate();
    }
    public boolean checkValid(Task task){
        if(task.getNameTask().length() ==0 ){
            newTask.checkValid("Enter your task");
            return false;
        }
        if(task.getCateName() ==null){
            newTask.checkValid("Choose one task group");
            return  false;
        }
        newTask.checkValid("Add task success");
        return true;
    }
   public void addTask(Task task){
        if (checkValid(task)){
            dbHelper.addTask(task);
            newTask.addTask(task);
            navigate();
        }

    }
    public  void toggleTask(ArrayList<Category> cats){
        for (Category category : cats){
            if (category.isSelected()){
                newTask.toggleCategory(category.getCategory());
                return;
            }
        }
    }
    public void showDatePicker(){
        newTask.showDatePicker();
    }
    public void showTimePicker(){
        newTask.showTimePicker();
    }
}
