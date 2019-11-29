package com.example.tododaily.model;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tododaily.R;
import com.example.tododaily.adapter.CategoryAdapter;
import com.example.tododaily.interfaces.NewTask;
import com.example.tododaily.presenter.NewTaskPresenter;
import com.example.tododaily.view.dashboard.TaskListActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DialogAddTask extends DialogFragment implements NewTask,View.OnClickListener {
    ArrayList <Category> catList;
    NewTaskPresenter newTaskPresenter;
    Button btnAddTask;
    ImageView imgDismiss;
    TextView tvDatePicker, tvTimePicker;
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
       init();
        return inflater.inflate(R.layout.popup_add_task, container);
    }
    void init(){
        catList = new ArrayList<>();
        catList.add(new Category("#F9C229", "Personal", false));
        catList.add(new Category("#1ED102", "Work", false));
        catList.add(new Category("#D10263", "Meeting", false));
        catList.add(new Category("#EC6C0B", "Shopping", false));
        catList.add(new Category("#09ACCE", "Party", false));
        catList.add(new Category("#BF0080", "Study", false));
        newTaskPresenter = new NewTaskPresenter(this);
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
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), catList, this);
        rvCat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvCat.setAdapter(categoryAdapter);
        initViewCreated(view);
    }
    void initViewCreated(View view){
        btnAddTask = view.findViewById(R.id.btn_add_task);;
        imgDismiss = view.findViewById(R.id.img_dismiss_popup);
        tvDatePicker = view.findViewById(R.id.tv_choose_date);
        tvTimePicker = view.findViewById(R.id.tv_choose_time);
        tvTimePicker.setText(getCurrentTime());
        tvDatePicker.setText(getCurrentDate());
        tvTimePicker.setOnClickListener(this);
        tvDatePicker.setOnClickListener(this);
        btnAddTask.setOnClickListener(this);
        imgDismiss.setOnClickListener(this);
    }
    String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return  formatter.format(date);
    }
    String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        Date date = new Date();
        return  formatter.format(date);
    }
    @Override
    public void showPopup() {

    }

    @Override
    public void toggleCategory(ArrayList<Category> cats) {

    }
    @Override
    public void chooseDate() {

    }
    @Override
    public void checkForm() {

    }
    @Override
    public void addTask(Task task) {

    }
    @Override
    public void navigate() {
        startActivity(new Intent(getActivity(), TaskListActivity.class));
    }

    @Override
    public void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tvDatePicker.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        tvTimePicker.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_dismiss_popup:
                dismiss();
                break;
            case R.id.btn_add_task:
                newTaskPresenter.navigate();
                break;
            case R.id.tv_choose_date:
                newTaskPresenter.showDatePicker();
                break;
            case R.id.tv_choose_time:
                newTaskPresenter.showTimePicker();
                break;
            default:break;
        }
    }
}
