package com.example.tododaily.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.tododaily.database.DBHelper;
import com.example.tododaily.interfaces.NewTask;
import com.example.tododaily.interfaces.TaskList;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class TaskListPresenter {
    TaskList taskList;
    NewTask newTask;
    DBHelper dbHelper;
    Context context;
    public TaskListPresenter(TaskList taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
        dbHelper = new DBHelper(context);
    }
    public void getALlTask(){
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = dbHelper.getAllTask();
        taskList.getAllTask(tasks);
    }
    public void toggleTask(boolean checked){
        taskList.checkTask(checked);
    }
    public void deleteItem(int position,int id){
        int result = dbHelper.delete(id);
        if(result > 0) {
            taskList.removeTask(position, true, "Deleted item");
            return;
        }
        taskList.removeTask(position,false,"An error has occurred");
        Toast.makeText(context, "ac", Toast.LENGTH_SHORT).show();

    }
}
