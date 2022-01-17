package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.jsonpersoncat.obj.MyData;
import com.example.demo.jsonpersoncat.obj.UserData;
import com.example.demo.jsonpersoncat.obj.UserDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private String getTempJsonString = "{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":1,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"英國短毛貓\"}],\"userData\":{\"id\":\"A120000001\",\"type\":\"男\",\"name\":\"王曉明\"}}";

    private String tempJsonString = "[{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":1,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"英國短毛貓\"}],\"userData\":{\"id\":\"A120000001\",\"type\":\"男\",\"name\":\"王曉明\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":2,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"波絲貓\"}],\"userData\":{\"id\":\"A220000022\",\"type\":\"女\",\"name\":\"林梅女\"}},{\"updateDt\":\"2021-10-20 11:00:00\",\"id\":3,\"myData\":[{\"dataId\":\"SC-1\",\"dataName\":\"家非貓\"},{\"dataId\":\"SC-2\",\"dataName\":\"無毛貓\"},{\"dataId\":\"SC-3\",\"dataName\":\"肥貓\"}],\"userData\":{\"id\":\"A220000032\",\"type\":\"女\",\"name\":\"林軒女\"}}]";
    private Button switchBt,bt_return_home;
    private TextView nameTxt, catCountTxt;

    private List<UserDetail> mResults;
    private Gson mGson;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /**
         *  gson 方式解析資料
         *  按鈕點擊下一筆資料 共三筆資料
         *  兩個tv 一個顯示姓名 一個顯示貓的數量
         */

        mGson = new Gson();

        nameTxt = findViewById(R.id.nameTxt);
        catCountTxt = findViewById(R.id.catCountTxt);
        switchBt = findViewById(R.id.switchBt);
        bt_return_home = findViewById(R.id.bt_return_home);

        handleBt();

        // tempJsonString  (jsonArray)資料來源 ; List 資料來源為jsonArray等於[]就須使用List ; UserDetail 為javabean ; mResults 型態List<UserDetail>
        // 將jsonArray 用gson 去解析成 List
        mResults = mGson.fromJson(tempJsonString, new TypeToken<List<UserDetail>>() {}.getType());
        Log.i("MyTag","0mResults: " + String.valueOf(mResults));// [com.example.demo.jsonpersoncat.obj.UserDetail@c35940b, com.example.demo.jsonpersoncat.obj.UserDetail@16d92e8, com.example.demo.jsonpersoncat.obj.UserDetail@5da6d01]

        switchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++index;
                if (index >= mResults.size()) {
                    index = 0;
                }
                handleJsonView();
            }
        });
        handleJsonView(); //一開始就顯示取到的第一筆資料

        // getTempJsonString (jsonObject)資料來源 ; UserDetail 為javabean ; cUserDetail 型態UserDetail
        UserDetail cUserDetail = mGson.fromJson(getTempJsonString, UserDetail.class);
        Log.i("MyTag","1cUserDetail: " + String.valueOf(cUserDetail));// com.example.demo.jsonpersoncat.obj.UserDetail@afce7a6

        // jsonObject 用gson 取值方式
        Log.i("MyTag", cUserDetail.getUpdateDate());// 2021-10-20 11:00:00
        Log.i("MyTag", String.valueOf(cUserDetail.getId()));// 1
        Log.i("MyTag", cUserDetail.getUserData().getName());// 王曉明
        Log.i("MyTag", cUserDetail.getUserData().getType());// 男
    }

    private void handleBt() {
        bt_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, HomeMainActivity.class);
                startActivity(intent);
                Main2Activity.this.finish();//結束目前 Activity
            }
        });
    }

    // show data to view
    private void handleJsonView() {
        // mResults (jsonArray)資料來源 ; .get(index) 一開始取陣列的第0筆資料 ; index 則為在陣列取值(浮動方式)
        UserDetail cUserDetail = mResults.get(index);
        Log.i("MyTag","2cUserDetail: " + String.valueOf(cUserDetail));// com.example.demo.jsonpersoncat.obj.UserDetail@27427da

        UserData cUserData = cUserDetail.getUserData();// 在陣列0的資料再往下取UserData
        Log.i("MyTag","3cUserData: " + String.valueOf(cUserData));// com.example.demo.jsonpersoncat.obj.UserData@5da6d01
        nameTxt.setText(cUserData.getName());
        Log.i("MyTag","4cUserData.getName(): " + String.valueOf(cUserData.getName()));// 王曉明


        List<MyData> cMyDatas = cUserDetail.getCatList();
        catCountTxt.setText(String.valueOf(cMyDatas.size()));
        Log.i("MyTag","5cMyDatas.size(): " + String.valueOf(cMyDatas.size()));// 2
    }
}
