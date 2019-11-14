package com.example.tododaily.model;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tododaily.R;
import com.example.tododaily.adapter.CategoryAdapter;

import java.util.ArrayList;

public class DialogAddTask extends DialogFragment {
    ArrayList <Category> catList;
    public static DialogAddTask newInstance(String title) {
        DialogAddTask frag = new DialogAddTask();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        catList = new ArrayList<>();
        catList.add(new Category("#F9C229", "Personal", false));
        catList.add(new Category("#1ED102", "Work", false));
        catList.add(new Category("#D10263", "Meeting", false));
        catList.add(new Category("#EC6C0B", "Shopping", false));
        catList.add(new Category("#09ACCE", "Party", false));
        catList.add(new Category("#BF0080", "Study", false));
        return inflater.inflate(R.layout.popup_add_task, container);
    }
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            d.getWindow().setLayout(width, height);
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvCat = view.findViewById(R.id.rv_cat);
        ImageView imgDissmiss = view.findViewById(R.id.img_dismiss_popup);
        imgDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), catList);
        rvCat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvCat.setAdapter(categoryAdapter);
    }


}
