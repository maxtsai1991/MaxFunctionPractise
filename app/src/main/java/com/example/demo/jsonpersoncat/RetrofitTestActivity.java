package com.example.demo.jsonpersoncat;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.demo.jsonpersoncat.adapters.RetrofitTestAdapter;
import com.example.demo.jsonpersoncat.data.SyncRequest;
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



/*
    使用retrofit 與 API連線
    參考影片處 : https://www.youtube.com/watch?v=v1EcwuYIfFA
    Retrofit : https://square.github.io/retrofit/
    Open API平台 : https://www.openapi.org.tw/#/
    食物資訊樣貌API URL : https://www.openapi.org.tw/#/api/e3fa5230-8c8a-11ea-8b2f-dfcba39a3448/info
    使用說明 :
    點擊 "取得食物資料" 可獲得API 資料 ,資料在LogcatD上顯示
    API資料用Recycleview呈現
 */
public class RetrofitTestActivity extends AppCompatActivity {
    private Button bt_food,bt_return;
    private Retrofit mRetrofit;
    private ApiService mApiService;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        bt_food = findViewById(R.id.bt_food);
        bt_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syneFood();
            }
        });

        bt_return = findViewById(R.id.bt_tibame_return_home);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RetrofitTestActivity.this, HomeMainActivity.class);
                startActivity(intent);
                RetrofitTestActivity.this.finish();//結束目前 Activity
            }
        });

        //RecycleView FindViewById
        mRecyclerView = findViewById(R.id.recycleview);

    }
        //定義Retrofit
        private Retrofit getRetrofit(){
            if (mRetrofit == null){
                mRetrofit = new Retrofit.Builder()
                        .baseUrl("https://gw.openapi.org.tw/") // 最後一定要是斜線"/"結尾
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return mRetrofit;
        }

        //定義API
        private interface ApiService{
            @GET("18463fd0-8aa7-11ea-8b2f-dfcba39a3448/6ace07502582") //baseUrl之後,問號之前
            Call<SyncResponse> getFoodInfomation(
                    @Query("client_id") String clientID,
                    @Query("client_secret") String clientSecret,
                    @Query("skip") Integer skip,
                    @Query("limit") Integer limit);
        }

    private void syneFood() {
        mApiService = getRetrofit().create(ApiService.class); //取得Retrofit
        SyncRequest apiRequest = new SyncRequest();
        apiRequest.setmClient_id("5c53d140-7369-11ec-bcd3-2fe59eaf628f"); // PostMan 參數VALUE
        try {
            apiRequest.setmClient_secret(URLDecoder.decode("lVwHXHNmAl9aB14f8h%2FDHcqMXUZi2bBtvxL%2FatbLKFs%3D","UTF-8"));// PostMan 參數VALUE
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        apiRequest.setmSkip(0); // skip參數:中譯偏移數量,指定要從第幾筆資料開始進行查詢
        apiRequest.setmLimit(100);// limit參數:中譯取的數量,每次查詢所能回應的最大筆數,上限為100

        Callback<SyncResponse> apiCallback = new Callback<SyncResponse>() {
            @Override
            public void onResponse(Call<SyncResponse> call, Response<SyncResponse> response) { // API返回成功
                // response.isSuccessful() 一定要true (http stats:200), 才能去取response.body()
                if(response.isSuccessful()){
                    Log.d("Logd", "onResponse _  Request : " + call.request());
                    //.body一次性 .body只能呼叫一次  > 宣告變數SAVA 再去做儲存
                    Log.d("Logd", "onResponse _  Response1 : " + new Gson().toJson(response.body()));
//                    Log.d("Logd", "onResponse _  Response2 : " + response.body());
                    List<SyncResponse.FoodData> foodInfoList = response.body().getmFoodDataList();
                    // API 拿到的資料 用RecyclerView裝起來
                    handleRecyclerView(foodInfoList);
                }
            }

            @Override
            public void onFailure(Call<SyncResponse> call, Throwable t) { // API返回失敗
                Log.d("Logd", "onFailure : " + t.getMessage());
            }
        };
        // .enqueue(apiCallback) : 執行API Callback
        mApiService.getFoodInfomation(apiRequest.getmClient_id(), apiRequest.getmClient_secret(), apiRequest.getmSkip(), apiRequest.getmLimit()).enqueue(apiCallback);
    }

        // RecyclerView
    private void handleRecyclerView(List<SyncResponse.FoodData> foodInfoList) {
        // 4.2 設定Adapter
        mRecyclerView.setAdapter(new RetrofitTestAdapter(foodInfoList));
        // 4.3 設定LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


         /*
         Adapter 可寫成內部類別 也可獨立一個Class檔(RetrofitTestAdapter.java)
         */
//    private static class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
//        private final List<SyncResponse.FoodData> list;
//
//        private MyListAdapter(List<SyncResponse.FoodData> list) {
//            this.list = list;
//        }
//
//        static class ViewHolder extends RecyclerView.ViewHolder{
//            ImageView iv_food_pic;
//            TextView tv_foodname,tv_unit_weight,tv_unit_name,tv_food_calories;
//
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//                iv_food_pic = itemView.findViewById(R.id.iv_food_pic);
//                tv_foodname = itemView.findViewById(R.id.tv_foodname);
//                tv_unit_weight = itemView.findViewById(R.id.tv_unit_weight);
//                tv_unit_name = itemView.findViewById(R.id.tv_unit_name);
//                tv_food_calories = itemView.findViewById(R.id.tv_food_calories);
//            }
//
//            public void setApiData(String url, String foodname, int weight, int calories, String unit){
//                Glide.with(iv_food_pic)
//                        .load(url)
//                        .into(iv_food_pic);
//                tv_foodname.setText(foodname);
//                tv_unit_weight.setText(String.valueOf(weight));
//                tv_food_calories.setText(calories + "");
//                tv_unit_name.setText(unit);
//            }
//        }
//
//        @NonNull
//        @Override
//        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            // 連接recycler_cardview_item_view.xml檔案，return一個View
//            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retrofitrecycle,parent,false);
//            return new ViewHolder(itemView);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//            // 在這裡取得元件的控制(每個item內的控制)
//            final SyncResponse.FoodData food = list.get(position);
//            holder.setApiData(food.getmIconUrl(), food.getmName(), food.getmWeight(),food.getmCalories(),food.getmUnit());
//        }
//
//        @Override
//        public int getItemCount() {
//            // 取得顯示數量，return一個int，通常都會return陣列長度(arrayList.size)
//            return list == null ? 0 : list.size();
//        }
//    }
}