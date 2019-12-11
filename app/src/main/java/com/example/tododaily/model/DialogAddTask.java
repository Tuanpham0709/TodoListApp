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
import android.widget.EditText;
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
import com.example.tododaily.interfaces.Main;
import com.example.tododaily.interfaces.NewTask;
import com.example.tododaily.presenter.MainPresenter;
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
    TextView tvDatePicker, tvTimePicker, tvTitle, tvTitleBtn;
    EditText edtTaskName ;
    Category category;
    String catName;
    MainPresenter mainPresenter;
    Main mMain;
    String title, titleBtn;
    String type;
    public DialogAddTask (String title, String titleBtn, String type){
        this.title = title;
        this.titleBtn = titleBtn;
        this.type = type;
    }
    public static DialogAddTask newInstance(String title, String titleBtn, String type) {
        DialogAddTask frag = new DialogAddTask(title, titleBtn, type);
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }
    Task task;
    public DialogAddTask(String title, String titleBtn, Task task){
        this.task = task;
    }
    public static DialogAddTask editInstance(String title, String titleBtn, Task task){
        DialogAddTask frag = new DialogAddTask(title, titleBtn, task);
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
        catList.add(new Category("#F9C229", "Personal", true));
        catList.add(new Category("#1ED102", "Work", false));
        catList.add(new Category("#D10263", "Meeting", false));
        catList.add(new Category("#EC6C0B", "Shopping", false));
        catList.add(new Category("#09ACCE", "Party", false));
        catList.add(new Category("#BF0080", "Study", false));
        category = new Category();
        mainPresenter = new MainPresenter(mMain);
        newTaskPresenter = new NewTaskPresenter(this, getContext());
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
        if(type == "new_task"){
            tvTitle.setText("Add new task");
            btnAddTask.setText("Add task");
            return;
        }
        tvTitle.setText("Edit task");
        btnAddTask.setText("Edit task");

    }
    void initViewCreated(View view){
        tvTitle = view.findViewById(R.id.title_dialog);
        btnAddTask = view.findViewById(R.id.btn_add_task);;
        imgDismiss = view.findViewById(R.id.img_dismiss_popup);
        tvDatePicker = view.findViewById(R.id.tv_choose_date);
        tvTimePicker = view.findViewById(R.id.tv_choose_time);
        tvTimePicker.setText(getCurrentTime());
        edtTaskName = view.findViewById(R.id.edt_taskname);
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
    String getCurrentDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd/MM/yyyy");

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
    public void toggleCategory(String catName) {
       this.catName= catName;
    }
    @Override
    public void chooseDate() {

    }
    @Override
    public void checkForm() {

    }

    @Override
    public void checkValid(String message, boolean isValid) {
        if(isValid){
            dismiss();
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void editTask(Task task) {

    }

    @Override
    public void navigate() {
        Intent intent = new Intent(getActivity(), TaskListActivity.class);
        intent.putExtra("cat", this.catName);
        startActivity(intent);
    }

    @Override
    public void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        final int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        tvDatePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        final int mMinute = c.get(Calendar.MINUTE);
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
                String date = tvTimePicker.getText().toString() + " " +  tvDatePicker.getText().toString();
                String taskName = edtTaskName.getText().toString();

                if(type == "new_task"){
                    Task newTask = new Task();
                    newTask.setNameTask(taskName);
                    newTask.setTime(date);
                    newTask.setCateName(this.catName);
                    newTaskPresenter.addTask(newTask);
                    return;
                }
                this.task.setNameTask(taskName);
                this.task.setTime(date);
                this.task.setCateName(this.catName);
                newTaskPresenter.editTask(this.task);
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
