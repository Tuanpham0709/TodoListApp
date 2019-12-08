package com.example.tododaily.presenter;

import android.content.Intent;

import com.example.tododaily.interfaces.Navigate;

public class NavigatePresenter {
    Navigate navigate;

    public NavigatePresenter(Navigate navigate) {
        this.navigate = navigate;
    }

    public void NavigateTo(String catName){
        navigate.navigateToActivity(catName);
    }
}
