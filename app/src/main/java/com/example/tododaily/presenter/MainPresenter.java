package com.example.tododaily.presenter;

import com.example.tododaily.interfaces.Main;

public class MainPresenter  {
    private Main mainEvent;

    public MainPresenter(Main mainEvent) {
        this.mainEvent = mainEvent;
    }
    public void show(){
        mainEvent.showDialog();
    }
}
