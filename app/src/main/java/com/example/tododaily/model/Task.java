package com.example.tododaily.model;

public class Task {
    String id;
    String time, nameTask, cateName;
    boolean checked;


    public Task(String time, String nameTask, String groupColor, boolean checked) {
        this.time = time;
        this.nameTask = nameTask;
        this.cateName = groupColor;
        this.checked = checked;
    }
    public Task(){

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
