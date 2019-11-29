package com.example.tododaily.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododaily.R;
import com.example.tododaily.adapter.TaskAdapter;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rvTask;
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
                root = inflater.inflate(R.layout.fragment_home, container, false);
              init();
        return root;
    }
    void init(){
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
        rvTask = root.findViewById(R.id.rv_task);
        rvTask.setLayoutManager( new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        TaskAdapter taskAdapter = new TaskAdapter(tasks,getContext());
        rvTask.setAdapter(taskAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }



}