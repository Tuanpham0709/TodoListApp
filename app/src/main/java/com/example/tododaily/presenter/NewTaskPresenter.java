package com.example.tododaily.presenter;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.FragmentManager;

import com.example.tododaily.database.DBHelper;
import com.example.tododaily.interfaces.NewTask;
import com.example.tododaily.model.Category;
import com.example.tododaily.model.DialogAddTask;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class NewTaskPresenter {
   NewTask newTask;
    DBHelper dbHelper;
    FragmentManager fm;
    public NewTaskPresenter(NewTask newTask, Context context) {
        this.newTask = newTask;
        dbHelper = new DBHelper(context);
    }
    public void navigate(){
        newTask.navigate();
    }
    public boolean checkValid(Task task, String type){
        if(task.getNameTask().length() ==0 ){

            newTask.checkValid("Enter your task",false);
            return false;
        }

        return true;
    }
   public void addTask(Task task){
        if (checkValid(task, "new_task")){
            dbHelper.addTask(task);
            newTask.addTask(task);
            newTask.checkValid("Add task success", true);
            navigate();
        }

    }
    public void editTask(Task task){
        if(checkValid(task, "edit_task")){
            int result = dbHelper.update(task);
            if(result > 0){
                newTask.editTask(task);
                newTask.checkValid("Edit task success", true);
                return;
            }
            newTask.checkValid("An error occurred", true);

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
