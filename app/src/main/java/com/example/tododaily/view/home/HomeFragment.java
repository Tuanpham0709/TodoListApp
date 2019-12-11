package com.example.tododaily.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododaily.R;
import com.example.tododaily.adapter.SwipeToDeleteCallback;
import com.example.tododaily.adapter.TaskAdapter;
import com.example.tododaily.database.DBHelper;
import com.example.tododaily.interfaces.TaskList;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements TaskList {
    RecyclerView rvTask;
    View root;
    DBHelper dbHelper;
    TaskAdapter taskAdapter;
    ArrayList<Task> tasks;
    LinearLayout emtyTask ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                root = inflater.inflate(R.layout.fragment_home, container, false);
              init();
        return root;
    }
    void init(){
        dbHelper = new DBHelper(getActivity());
        emtyTask = root.findViewById(R.id.empty_task);
        rvTask = root.findViewById(R.id.rv_task);
       configView();
    }
    void configView(){
        tasks = dbHelper.getAllTask();
        if(tasks.size() > 0){
            emtyTask.setVisibility(View.GONE);
            rvTask.setLayoutManager( new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            taskAdapter = new TaskAdapter(tasks,getContext(), root, "fragment");
            rvTask.setAdapter(taskAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(taskAdapter, getContext(), this));
            itemTouchHelper.attachToRecyclerView(rvTask);
            return;
        }
        rvTask.setVisibility(View.GONE);
        emtyTask.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume() {
        super.onResume();
        configView();
    }

    @Override
    public void onStart() {
        super.onStart();
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