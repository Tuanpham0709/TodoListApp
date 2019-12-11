package com.example.tododaily.view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.tododaily.R;
import com.example.tododaily.adapter.CategoryAdapter;
import com.example.tododaily.adapter.TaskAdapter;
import com.example.tododaily.interfaces.Main;
import com.example.tododaily.model.Category;
import com.example.tododaily.model.DialogAddTask;
import com.example.tododaily.presenter.MainPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Main, View.OnClickListener {
    FloatingActionButton fbAddTask;
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    void init() {
        fbAddTask = findViewById(R.id.fl_add);
        fbAddTask.setOnClickListener(this);
        mainPresenter = new MainPresenter(this);
    }
    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogAddTask editNameDialogFragment = DialogAddTask.newInstance("Some Title");
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    @Override
    public void showDialog() {
        showEditDialog();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fl_add:
                mainPresenter.show();
        }
    }
}
