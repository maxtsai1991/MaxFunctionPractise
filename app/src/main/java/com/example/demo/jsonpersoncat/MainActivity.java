package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.jsonpersoncat.obj.Book;
import com.example.demo.jsonpersoncat.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    public static MainActivity getInstance() {
        return instance;
    }

    private String tempJsonString = "[{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":1,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"英國短毛貓\"}],\"userData\":{\"id\":\"A120000001\",\"type\":\"男\",\"name\":\"王曉明\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":2,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"}],\"userData\":{\"id\":\"A220000022\",\"type\":\"女\",\"name\":\"林梅女\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":3,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"家非貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"無毛貓\"},{\"dataId\":\"SC-3\",\"dataName\":\"肥貓\"}],\"userData\":{\"id\":\"A220000032\",\"type\":\"女\",\"name\":\"林軒女\"}}]";
    private Button switchBt,bt_return_home;
    private TextView nameTxt,catCountTxt;
    private int index = 0;
    private ArrayList<HashMap<String, String>> list;
    private HashMap<String, String> resultMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTxt = findViewById(R.id.nameTxt);
        catCountTxt = findViewById(R.id.catCountTxt);
        switchBt  = findViewById(R.id.switchBt);
        bt_return_home  = findViewById(R.id.bt_return_home);
        handleJsonView();
        handleBt();
        /**
         * json 方式解析資料
         * 按鈕點擊下一筆資料 共三筆資料
         * 兩個tv 一個顯示姓名 一個顯示貓的數量
         */


    }

    private void handleSetData() {
        nameTxt.setText(resultMap.get("name"));
        Log.i("myTag", "resultMap.get(\"name\") = "+ resultMap.get("name"));
        catCountTxt.setText(String.valueOf(list.size()));
        Log.i("myTag", "list.size() = "+ list.size());

    }

    private void handleJsonView() {
        ArrayList<HashMap<String, String>> resultList = JsonUtil.getList(tempJsonString);
        Log.i("myTag", "resultList = "+ resultList);

        resultMap = JsonUtil.getMap(resultList.get(index).get("userData"));
        Log.i("myTag", "resultMap = "+ resultMap);

        String tempJsonString1 = resultList.get(index).get("myData");
        Log.i("myTag", "tempJsonString1 = "+ tempJsonString1);

        list = JsonUtil.getList(tempJsonString1);
        Log.i("myTag", "list = "+ list);

        handleSetData();
    }

    private void handleBt() {
        bt_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeMainActivity.class);
                startActivity(intent);
                MainActivity.this.finish();//結束目前 Activity
            }
        });

        switchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++index;
                if(index >= 3){
                    index = 0;
                }
                handleJsonView();
            }
        });

    }

}