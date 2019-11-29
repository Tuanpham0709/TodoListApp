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

public class DashboardFragment extends Fragment implements View.OnClickListener {
    LinearLayout personal, work, study, meeting,shopping, party;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_dashboard, container, false);
         init();
         return root;
    }
    void init(){
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
        Intent intent = new Intent(getActivity(), TaskListActivity.class);
        switch (view.getId()){
            case R.id.work_task:
                break;
            case R.id.study_task:
                break;
            case R.id.shopping_task:
                break;
            case R.id.meetting_task:
                break;
            case R.id.personal_task:
                break;
            case R.id.party_task:
                break;
                default:break;

        }
        startActivity(intent);
    }
}