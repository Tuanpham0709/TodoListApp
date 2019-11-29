package com.example.tododaily.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.tododaily.interfaces.NewTask;
import com.example.tododaily.model.Category;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class NewTaskPresenter {
   NewTask newTask;

    public NewTaskPresenter(NewTask newTask) {
        this.newTask = newTask;
    }
   public void navigate(){
        newTask.navigate();
    }
   public void addTask(Task task){
        newTask.addTask(task);
    }
    public  void toggleTask(ArrayList<Category> cats){
        newTask.toggleCategory(cats);
    }
    public void showDatePicker(){
        newTask.showDatePicker();
    }
    public void showTimePicker(){
        newTask.showTimePicker();
    }
}
