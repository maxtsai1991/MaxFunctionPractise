package com.example.demo.jsonpersoncat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.demo.jsonpersoncat.databinding.ActivityMvvmTest1MainBinding;
import com.example.demo.jsonpersoncat.mvvmtest1.Test1MainViewModel;


public class MvvmTest1MainActivity extends AppCompatActivity {
    private ActivityMvvmTest1MainBinding activityMvvmTest1MainBinding;
    private Test1MainViewModel  test1MainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMvvmTest1MainBinding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm_test1_main);
        test1MainViewModel = new ViewModelProvider(this).get(Test1MainViewModel.class);
        test1MainViewModel.getMpData();
        activityMvvmTest1MainBinding.rvMain.setAdapter(test1MainViewModel.test1MainAdapter);
    }


}