package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.demo.jsonpersoncat.adapters.ParkingRetrofitAdapter;
import com.example.demo.jsonpersoncat.data.ParkinginfoRequest;
import com.example.demo.jsonpersoncat.data.ParkinginfoResponse;
import com.example.demo.jsonpersoncat.data.SyncResponse;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 該頁未完成, 因API 會跑到 onFailure 選項
 */

/*
使用retrofit 與 API連線
參考影片處 : https://www.youtube.com/watch?v=v1EcwuYIfFA
Retrofit : https://square.github.io/retrofit/
Open API平台 : https://www.openapi.org.tw/#/
停車場基本資料API URL : https://gw.openapi.org.tw/5ce625594dae4e0f00e4aa89/parkinginfolist?client_id=5c53d140-7369-11ec-bcd3-2fe59eaf628f&client_secret=lVwHXHNmAl9aB14f8h%2FDHcqMXUZi2bBtvxL%2FatbLKFs%3D&UID=TNN&token=SURCOklEQjEyMzoxOTgyLTEwLTE5IDAwOjAwOjAw
使用說明 :
點擊 "取得停車場基本資料" 可獲得API 資料 ,資料在LogcatD上顯示
API資料用Recycleview呈現
*/
public class ParkingInfoRetrofitActivity extends AppCompatActivity {
    private Button bt_return_hotstore,bt_hot_store_data;
    private Retrofit mRetrofit;
    private ParkApiService mParkingApiService;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkinginfo_retrofit);

        // findViewID
        bt_return_hotstore = findViewById(R.id.bt_return_hotstore);
        bt_hot_store_data = findViewById(R.id.bt_get_parking_data);

        // OnClickListener
        //返回首頁
        bt_return_hotstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParkingInfoRetrofitActivity.this, HomeMainActivity.class);
                startActivity(intent);
                ParkingInfoRetrofitActivity.this.finish();//結束目前 Activity
            }
        });

        // 取得API資料
        bt_hot_store_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syneParkingInfo();
            }
        });

    }

    //定義Retrofit
    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("https://gw.openapi.org.tw/") // 最後一定要是斜線"/"結尾
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    //定義API : Query Params (查詢參數)
    private interface ParkApiService{
        @GET("5ce625594dae4e0f00e4aa89/parkinginfolist") //baseUrl之後,問號之前
        Call<ParkinginfoResponse> getParkingInfomation(
                @Query("client_id") String clientID,
                @Query("client_secret") String clientSecret,
                @Query("UID") String uid,
                @Query("token") String token);
    }

    private void syneParkingInfo(){
        mParkingApiService = getRetrofit().create(ParkApiService.class); //取得Retrofit
        ParkinginfoRequest parkinginfoRequest = new ParkinginfoRequest();
        parkinginfoRequest.setmClient_id("5c53d140-7369-11ec-bcd3-2fe59eaf628f"); // PostMan key : Client_id 的 VALUE(參數)

        try {
            parkinginfoRequest.setmClient_secret(URLDecoder.decode("lVwHXHNmAl9aB14f8h%2FDHcqMXUZi2bBtvxL%2FatbLKFs%3D","UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        parkinginfoRequest.setmUid("TNN");
        parkinginfoRequest.setmToken("SURCOklEQjEyMzoxOTgyLTEwLTE5IDAwOjAwOjAw");

        Callback<ParkinginfoResponse> parkinginfoResponseCallback = new Callback<ParkinginfoResponse>() {
            @Override
            public void onResponse(Call<ParkinginfoResponse> call, Response<ParkinginfoResponse> response) {
                Log.d("123", "onResponse: " + "onResponse");
            }

            @Override
            public void onFailure(Call<ParkinginfoResponse> call, Throwable t) {
                Log.d("123", "onFailure: " + "onFailure");
                Log.d("123", "call.request(): " + call.request());

            }

        };


        // .enqueue(apiCallback) : 執行API Callback
        mParkingApiService.getParkingInfomation(parkinginfoRequest.getmClient_id(),parkinginfoRequest.getmClient_secret(),parkinginfoRequest.getmUid(),parkinginfoRequest.getmToken()).enqueue(parkinginfoResponseCallback);
    }

    // RecyclerView
    private void handleRecyclerView(List<ParkinginfoResponse.ParkingInfoData> parkingInfoList) {
        // 4.2 設定Adapter
//        mRecyclerView.setAdapter(new ParkingRetrofitAdapter(parkingInfoList));
        // 4.3 設定LayoutManager
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}