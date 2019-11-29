package com.example.tododaily.model;

public class Task {
    String time, nameTask, groupColor;
    boolean checked;
    public Task(String time, String nameTask, String groupColor) {
        this.time = time;
        this.nameTask = nameTask;
        this.groupColor = groupColor;
    }

    public Task(String time, String nameTask, String groupColor, boolean checked) {
        this.time = time;
        this.nameTask = nameTask;
        this.groupColor = groupColor;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getGroupColor() {
        return groupColor;
    }

    public void setGroupColor(String groupColor) {
        this.groupColor = groupColor;
    }
}
