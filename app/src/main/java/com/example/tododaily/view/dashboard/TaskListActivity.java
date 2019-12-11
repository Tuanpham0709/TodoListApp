package com.example.tododaily.view.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tododaily.R;
import com.example.tododaily.adapter.SwipeToDeleteCallback;
import com.example.tododaily.adapter.TaskAdapter;
import com.example.tododaily.database.DBHelper;
import com.example.tododaily.interfaces.TaskList;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity implements TaskList {
    TextView tvTitle;
    RecyclerView rvCatTask;
    DBHelper dbHelper;
    ArrayList<Task> tasksOfCat;
    Toolbar toolbar;
    TaskAdapter taskAdapter;
    LinearLayout emtyTask;
     ViewGroup viewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_task_list);
        toolbar = findViewById(R.id.tb_tasks_of_cat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
      viewGroup   = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        init();
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void init(){
        emtyTask = findViewById(R.id.empty_task);
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
        tasksOfCat = dbHelper.getTasksOfCat(cat);
        if(tasksOfCat.size() >0){
            emtyTask.setVisibility(View.GONE);
             taskAdapter = new TaskAdapter(tasksOfCat,this,viewGroup,"activity", getSupportFragmentManager());
            rvCatTask.setAdapter(taskAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(taskAdapter, this, this));
            itemTouchHelper.attachToRecyclerView(rvCatTask);
            return;
        }
        rvCatTask.setVisibility(View.GONE);
        emtyTask.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void checkTask(boolean checked) {

    }

    @Override
    public void editTask() {

    }

    @Override
    public void removeTask(int position, boolean isRemoved, String message) {
        taskAdapter.deletedItem(isRemoved, position);
    }

    @Override
    public void getAllTask(ArrayList<Task> tasks) {

    }
}
