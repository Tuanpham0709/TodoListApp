package com.example.tododaily.presenter;

import com.example.tododaily.interfaces.TaskList;
import com.example.tododaily.model.Task;

public class TaskListPresenter {
    TaskList taskList;

    public TaskListPresenter(TaskList taskList) {
        this.taskList = taskList;
    }
    public void toggleTask(boolean checked){
        taskList.checkTask(checked);
    }
}
