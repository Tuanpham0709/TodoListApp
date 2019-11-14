package com.example.tododaily.ui.home;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododaily.MainActivity;
import com.example.tododaily.R;
import com.example.tododaily.adapter.TaskAdapter;
import com.example.tododaily.model.DialogAddTask;
import com.example.tododaily.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.opengl.ETC1.getWidth;

public class HomeFragment extends Fragment {
    RecyclerView rvTask;
    View root;
    FloatingActionButton fbAdd;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
         root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        init();
        return root;
    }
    void init(){
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("7 : 20 AM", "Send project file", "yellow"));
        tasks.add(new Task("7 : 20 AM", "Implement project react", "green"));
        tasks.add(new Task("7 : 20 AM", "Assignment", "pink"));
        tasks.add(new Task("7 : 20 AM", "Send project file", "orange"));
        tasks.add(new Task("7 : 20 AM", "To do something", "blue"));
        tasks.add(new Task("7 : 20 AM", "Send project file", "violet"));
        tasks.add(new Task("7 : 20 AM", "Send project file", "blue"));
        tasks.add(new Task("7 : 20 AM", "Send project file", "green"));
        tasks.add(new Task("7 : 20 AM", "Send project file", "violet"));
        tasks.add(new Task("7 : 20 AM", "Send project file", "yellow"));
        rvTask = root.findViewById(R.id.rv_task);
//        fbAdd = root.findViewById(R.id.fl_add);
        rvTask.setLayoutManager( new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        TaskAdapter taskAdapter = new TaskAdapter(tasks,getContext());
        rvTask.setAdapter(taskAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }



}