package com.example.tododaily.view.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tododaily.R;
import com.example.tododaily.adapter.TaskAdapter;
import com.example.tododaily.database.DBHelper;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity{
    TextView tvTitle;
    ImageView imgCat;
    RecyclerView rvCatTask;
    DBHelper dbHelper;
    ArrayList<Task> tasksOfCat;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        toolbar = findViewById(R.id.tb_tasks_of_cat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.tb_tasks_of_cat:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void init(){
        dbHelper = new DBHelper(this);
        tvTitle =findViewById(R.id.tv_title_tb);

        rvCatTask = findViewById(R.id.rv_task_of_cat);
        tasksOfCat = new ArrayList<>();
        rvCatTask.setLayoutManager( new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
    void initView(){
        String cat = getIntent().getStringExtra("cat");
        switch (cat){
            case "Personal":
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.personal, 0, 0, 0);
                break;
            case "Work":
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.work, 0, 0, 0);
                break;
            case "Meeting":
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.meeting, 0, 0, 0);
                break;
            case "Study":
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.study, 0, 0, 0);
                break;
            case "Party":
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.party, 0, 0, 0);
                break;
            case "Shopping":
                tvTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shopping, 0, 0, 0);
                break;
        }
        tvTitle.setText(cat);
        tasksOfCat = dbHelper.getAllTask();
        TaskAdapter taskAdapter = new TaskAdapter(tasksOfCat,this);
        rvCatTask.setAdapter(taskAdapter);
    }
}
