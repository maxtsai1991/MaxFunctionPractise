package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.demo.jsonpersoncat.obj.MyData;
import com.example.demo.jsonpersoncat.obj.UserData;
import com.example.demo.jsonpersoncat.obj.UserDetail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Switch2Activity extends AppCompatActivity {

    // 全部
    private String tempJsonString = "[{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":1,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"英國短毛貓\"}],\"userData\":{\"id\":\"A120000001\",\"type\":\"男\",\"name\":\"王曉明\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":2,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"}],\"userData\":{\"id\":\"A220000022\",\"type\":\"女\",\"name\":\"林梅女\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":3,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"家非貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"無毛貓\"},{\"dataId\":\"SC-3\",\"dataName\":\"肥貓\"}],\"userData\":{\"id\":\"A220000032\",\"type\":\"女\",\"name\":\"林軒女\"}}]";
    // 元件
    private TextView txt01;
    private Switch switch01,SW_markerColorPoint;

    private final Gson gson = new Gson();
    private final List<UserDetail> userDetails = new ArrayList<>();

    private Button bt_return_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch2);
        /**
         * gson
         * switch open: 所有貓
         * switch close: src-1貓品種
         */

        initViews();

        userDetails.addAll(parseJsonData(tempJsonString));
        txt01.setText("Gson寫法:" + "\n" + genShowStringFromStringList(getAllCatName(userDetails))); // 一開始就顯示

    }

    private void initViews() {
        txt01 = findViewById(R.id.txt01);
        switch01 = findViewById(R.id.switch01);
        SW_markerColorPoint = findViewById(R.id.SW_markerColorPoint);
        switch01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                List<String> showCatNameList = isChecked ? getAllCatName(userDetails) : getFirstCatNameForEachUser(userDetails); // 三元運算子-> 成立(顯示所有人的貓) : 不成立(只顯示所有人的第一隻貓)
                txt01.setText("Gson寫法:" + "\n" + genShowStringFromStringList(showCatNameList));
            }
        });

        SW_markerColorPoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        bt_return_home = findViewById(R.id.bt_return_home);
        bt_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Switch2Activity.this, HomeMainActivity.class);
                startActivity(intent);
                finish();//結束目前 Activity
            }
        });

    }

    private List<UserDetail> parseJsonData(String json) {
        List<UserDetail> list = gson.fromJson(json, new TypeToken<List<UserDetail>>() {}.getType());//第一個參數json : 給Gson的json字串 ;第二個參數 宣告目的型態為List<UserDetail> gson類別將json資料轉換List類別,最後以list物件保存
        Log.i("myTag", "list = " + list);
        return list;
    }

    private List<String> getAllCatName(List<UserDetail> userDetails) {
        List<String> tempList = new ArrayList<>();
        for (UserDetail userDetail : userDetails) {
            List<MyData> catDatas = userDetail.getCatList();
            for (MyData catData : catDatas) {
                tempList.add(catData.getDataName());
            }
        }
        Log.i("myTag", "(getAllCatName)tempList = " + tempList);
        return tempList;
    }

    private List<String> getFirstCatNameForEachUser(List<UserDetail> userDetails) {
        List<String> tempList = new ArrayList<>();
        for (UserDetail userDetail : userDetails) {
            List<MyData> catDatas = userDetail.getCatList();
            if (!catDatas.isEmpty()) {
                tempList.add(catDatas.get(0).getDataName());
            }
        }
        Log.i("myTag", "(getFirstCatNameForEachUser)tempList = " + "\n"  + tempList);
        return tempList;

    }

    private String genShowStringFromStringList(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string).append("\n");
        }
        Log.i("myTag", "(genShowStringFromStringList)stringBuilder = " + "\n" + stringBuilder.toString());
        return stringBuilder.toString();
    }


}