package com.example.tododaily.interfaces;

import com.example.tododaily.model.Category;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public interface NewTask {
    void showPopup();
    void toggleCategory(ArrayList<Category> cats);
    void chooseDate();
    void checkForm();
    void addTask(Task task);
    void navigate();
    void showDatePicker();
    void showTimePicker();
}
