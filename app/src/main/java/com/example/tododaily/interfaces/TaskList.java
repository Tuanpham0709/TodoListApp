package com.example.tododaily.interfaces;

import com.example.tododaily.model.Task;

import java.util.ArrayList;

public interface TaskList {
    void checkTask(boolean checked);
    void editTask();
    void removeTask(int position,boolean isRemoved,String message);
    void getAllTask(ArrayList<Task> tasks);
}
