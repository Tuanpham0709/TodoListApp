package com.example.tododaily.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                root = inflater.inflate(R.layout.fragment_home, container, false);
              init();
        return root;
    }
    void init(){
        dbHelper = new DBHelper(getActivity());
       configView();
    }
    void configView(){
        tasks = dbHelper.getAllTask();
        rvTask = root.findViewById(R.id.rv_task);
        rvTask.setLayoutManager( new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        taskAdapter = new TaskAdapter(tasks,getContext());
        rvTask.setAdapter(taskAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(taskAdapter, getContext(), this));
        itemTouchHelper.attachToRecyclerView(rvTask);
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