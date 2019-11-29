package com.example.tododaily.view.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tododaily.R;
import com.example.tododaily.adapter.TaskAdapter;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {
    TextView tvTitle;
    RecyclerView rvCatTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        init();
    }
    void init(){
        tvTitle =findViewById(R.id.tv_title);
        rvCatTask = findViewById(R.id.rv_task_of_cat);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("7 : 20 AM", "Send project file", "yellow", false));
        tasks.add(new Task("7 : 20 AM", "Implement project react", "green", false));
        tasks.add(new Task("7 : 20 AM", "Assignment", "pink", false));
        tasks.add(new Task("7 : 20 AM", "Send project file", "orange", false));
        tasks.add(new Task("7 : 20 AM", "To do something", "blue",false));
        tasks.add(new Task("7 : 20 AM", "Send project file", "violet", true));
        tasks.add(new Task("7 : 20 AM", "Send project file", "blue", false));
        tasks.add(new Task("7 : 20 AM", "Send project file", "green", true));
        tasks.add(new Task("7 : 20 AM", "Send project file", "violet", false));
        tasks.add(new Task("7 : 20 AM", "Send project file", "yellow", true));
        rvCatTask.setLayoutManager( new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        TaskAdapter taskAdapter = new TaskAdapter(tasks,this);
        rvCatTask.setAdapter(taskAdapter);
    }
}
