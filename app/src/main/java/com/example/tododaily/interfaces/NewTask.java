package com.example.tododaily.interfaces;

import com.example.tododaily.model.Category;
import com.example.tododaily.model.Task;

import java.util.ArrayList;

public interface NewTask {
    void showPopup();
    void toggleCategory(String taskName);
    void chooseDate();
    void checkForm();
    void checkValid(String message, boolean isValid);
    void addTask(Task task);
    void editTask(Task task);
    void navigate();
    void showDatePicker();
    void showTimePicker();
}
