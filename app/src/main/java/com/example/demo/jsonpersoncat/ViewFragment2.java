package com.example.demo.jsonpersoncat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class ViewFragment2 extends Fragment {
    public Spinner spinner;
    public Activity activity;
    public Button btnBuy,btreturnhome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_view1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findviews(view);
        handleButton(view);
    }

    private void findviews(View view) {
        btnBuy  = view.findViewById(R.id.btnBuy);
        btreturnhome  = view.findViewById(R.id.btreturnhome);
    }

    private void handleButton(View view) {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "click Buy Button ", Toast.LENGTH_SHORT).show();
            }
        });

        btreturnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "click Return Homepage ", Toast.LENGTH_SHORT).show();
                //fragment 回 activity (回首頁按鈕)
                Intent intent = new Intent(getActivity(),HomeMainActivity.class);
                startActivity(intent);
            }
        });

    }
}