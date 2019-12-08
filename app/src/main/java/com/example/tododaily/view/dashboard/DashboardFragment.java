package com.example.tododaily.view.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tododaily.R;
import com.example.tododaily.interfaces.Navigate;
import com.example.tododaily.presenter.NavigatePresenter;

public class DashboardFragment extends Fragment implements View.OnClickListener, Navigate {
    LinearLayout personal, work, study, meeting,shopping, party;
    View root;
    NavigatePresenter navigatePresenter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_dashboard, container, false);
         init();
         return root;
    }
    void init(){
        navigatePresenter = new NavigatePresenter(this);
        personal =root.findViewById(R.id.personal_task);
        work =root.findViewById(R.id.work_task);
        study =root.findViewById(R.id.study_task);
        meeting =root.findViewById(R.id.meetting_task);
        shopping =root.findViewById(R.id.shopping_task);
        party =root.findViewById(R.id.party_task);
        personal.setOnClickListener(this);
        work.setOnClickListener(this);
        study.setOnClickListener(this);
        meeting.setOnClickListener(this);
        party.setOnClickListener(this);
        shopping.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String catName = null;
        switch (view.getId()){
            case R.id.work_task:
                catName = "Work";
                break;
            case R.id.study_task:
                catName = "Study";
                break;
            case R.id.shopping_task:
                catName = "Shopping";
                break;
            case R.id.meetting_task:
                catName = "Meeting";
                break;
            case R.id.personal_task:
                catName = "Personal";
                break;
            case R.id.party_task:
                catName = "Party";
                break;
            default:break;
        }
        navigatePresenter.NavigateTo(catName);
    }

    @Override
    public void navigateToActivity(String catName) {
        Intent intent = new Intent(getActivity(), TaskListActivity.class);
        intent.putExtra("cat", catName);
        startActivity(intent);
    }
}