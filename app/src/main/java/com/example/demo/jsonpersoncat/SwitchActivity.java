package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.demo.jsonpersoncat.util.JsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SwitchActivity extends AppCompatActivity {
    private String tempJsonString = "[{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":1,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"英國短毛貓\"}],\"userData\":{\"id\":\"A120000001\",\"type\":\"男\",\"name\":\"王曉明\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":2,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"}],\"userData\":{\"id\":\"A220000022\",\"type\":\"女\",\"name\":\"林梅女\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":3,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"家非貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"無毛貓\"},{\"dataId\":\"SC-3\",\"dataName\":\"肥貓\"}],\"userData\":{\"id\":\"A220000032\",\"type\":\"女\",\"name\":\"林軒女\"}}]";
    private List<HashMap<String, String>> catDataObj;
    private TextView txt01;
    private Switch switch01;
    private String CatSum;
    private String sc1;
    private Activity activity;
    private Button bt1,bt_return_home;
    private Gson gson;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        /**
         * json 方式
         * 學長範例體醒
         *         // init 初始化畫面元件
         *         // 1. 資料解析
         *         // 2. 取得全部貓資料放入 >> catDataObj
         *         // 初始化 switch onChange
         *         // switch = false, show all cats data
         *         // switch = true, show only dataId=SC-1 cats
         *         // private List<HashMap<String, String>> catDataObj; > 放ALLCATDATA
         */

        init();
        handleJsonData(); //處理Json 資料
        txt01.setText(passData(switch01.isChecked()));
        handleButton();
        handleGson();
        bt_return_home = findViewById(R.id.bt_return_home);
    }




    private void init() {
        txt01 = findViewById(R.id.txt01);
        switch01 = findViewById(R.id.switch01);
        switch01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String msg = passData(isChecked);
                txt01.setText(msg);
            }
        });
    }

    private void handleJsonData() {
        // 全部資料 // 1.jsonArray to java list<HashMap<String, String>>
        List<HashMap<String, String>> resultAllList = JsonUtil.getList(tempJsonString);
        Log.i("myTag", "resultAllList = " + resultAllList);// [{myData=[{"dataId":"SC-1","dataName":"波絲貓"},{"dataId":"SC-2","dataName":"英國短毛貓"}], updateDt=2021-10-20 11:00:00, id=1, userData={"id":"A120000001","type":"男","name":"王曉明"}}, {myData=[{"dataId":"SC-1","dataName":"波絲貓"}], updateDt=2021-10-20 11:00:00, id=2, userData={"id":"A220000022","type":"女","name":"林梅女"}}, {myData=[{"dataId":"SC-1","dataName":"家非貓"},{"dataId":"SC-2","dataName":"無毛貓"},{"dataId":"SC-3","dataName":"肥貓"}], updateDt=2021-10-20 11:00:00, id=3, userData={"id":"A220000032","type":"女","name":"林軒女"}}]

        catDataObj = new ArrayList<>();
        for (HashMap<String, String> item : resultAllList) { //resultAllList: size =3 (陣列3筆資料) ; item: size =4 (每筆資料裡的4個key欄位)
            String resultCatList01 = item.get("myData");// item: size =4 (取第一筆資料裡的4個key欄位) 取myData陣列 ;resultCatList01: [{"dataId":"SC-1","dataName":"波絲貓"},{"dataId":"SC-2","dataName":"英國短毛貓"}]
            List<HashMap<String, String>> resultCat001 = JsonUtil.getList(resultCatList01);// resultCat001: size =2 (取的第一筆資料的myData資料)
            catDataObj.addAll(resultCat001);// catDataObj: size =2
        }
    }

    private String passData(boolean isOpen) {
        if (isOpen) {
            sc1 = "所有貓資料 : ";
        } else {
            sc1 = "只顯示SC-1(第一隻貓品種) : ";
        }
        for (HashMap<String, String> item : catDataObj) {
            if (isOpen) {
                sc1 = sc1 + "\n" + item.get("dataName");
            } else {
                if (item.get("dataId").equals("SC-1")) {
                    sc1 = sc1 + "\n" + item.get("dataName");
                }
            }
        }
        return sc1;
    }




    private void handleButton() {
        bt_return_home = findViewById(R.id.bt_return_home);
        bt_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwitchActivity.this, HomeMainActivity.class);
                startActivity(intent);
                SwitchActivity.this.finish();//結束目前 Activity
            }
        });
    }

    private void handleGson() {
    }
}