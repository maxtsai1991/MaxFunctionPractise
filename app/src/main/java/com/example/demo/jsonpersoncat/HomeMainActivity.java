package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.demo.jsonpersoncat.api.MyApi;
import com.example.demo.jsonpersoncat.obj.Todo;
import com.example.demo.jsonpersoncat.obj.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class HomeMainActivity extends AppCompatActivity {
    private static final String TAG = "Lifecycle_Test";
    private String intent_return_url = "https://xnfood.com.tw/android-intent-bundle/#skill_01";
    private Button bt_json_catnum_name_click,bt_json_cat_switch,bt_gson_catnum_name_click,bt_Gson_cat_switch,bt_Relative,bt_RecyclerView,bt_dialog,crashButton,bt_Retrofitfood,bt_code_notes,bt_battery,bt_room_stetho, bt_parking,bt_StudentRecyclerView;
    private WebView webView;
    private String roomtourUrl = "https://www.youtube.com/";

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        /*
        用LOG觀察生命週期, info , 搜尋TAG的字串
        https://juejin.cn/post/6893870636733890574
        1、生命週期擁有者使用getLifecycle()獲取Lifecycle實例，然後代用addObserve()添加觀察者；
        2、觀察者實現LifecycleObserver，方法上使用OnLifecycleEvent註解關注對應生命週期，生命週期觸發時就會執行對應方法；
         */
        getLifecycle().addObserver(new MyObserver());
        Log.i(TAG, "onCreate:  ");



        webView = findViewById(R.id.webView);
        bt_json_catnum_name_click = findViewById(R.id.bt_json_catnum_name_click);
        bt_json_cat_switch = findViewById(R.id.bt_json_cat_switch);
        bt_gson_catnum_name_click = findViewById(R.id.bt_gson_catnum_name_click);
        bt_Gson_cat_switch = findViewById(R.id.bt_Gson_cat_switch);
        bt_Relative = findViewById(R.id.bt_Relative);
        bt_RecyclerView = findViewById(R.id.bt_RecyclerView);
        bt_dialog = findViewById(R.id.bt_dialog);
        crashButton = findViewById(R.id.crashButton);
        bt_Retrofitfood = findViewById(R.id.bt_Retrofitfood);
        bt_code_notes = findViewById(R.id.bt_code_notes);
        bt_battery = findViewById(R.id.bt_battery);
        bt_room_stetho = findViewById(R.id.bt_room_stetho);
        bt_parking = findViewById(R.id.bt_parking);
        bt_StudentRecyclerView = findViewById(R.id.bt_StudentRecyclerView);

        initView();
        handleWebView();
    }

    private void handleWebView() {
        // 啟用JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // 載入網址
        webView.loadUrl(roomtourUrl);

        // 使用自訂的WebView來處理請求
        /**
         * 若沒有覆寫shouldOverrideUrlLoading()，並回傳false，
         * 使用者點擊任何連結，會使用裝置內建的瀏覽器來開啟
         */
        webView.setWebViewClient(new WebViewClient() {

            /** API 24-使用  */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            /** API 24(+使用  */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
    }


    private void initView() {
            /*
                Fragment 跳轉 Activity
                Intent intent = new Intent(getActivity(),FragmentTwoActivity.class);
                startActivity(intent);
             */
        
        // reference intent_return_url
        bt_json_catnum_name_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, MainActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        bt_json_cat_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, SwitchActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        bt_gson_catnum_name_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, Main2Activity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        bt_Gson_cat_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, Switch2Activity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        bt_Relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, RelativeTestActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        //RecyclerViewTest
        bt_RecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, RecyclerCardViewActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        bt_StudentRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, RecyclerViewStudentActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        // Dialog
        bt_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this, DialogActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        // 測試firebase crashlytics 按鈕 測試完要註解掉 不然無法專案無法RUN起來
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                throw new RuntimeException("Test Crash"); // Force a crash
            }
        });

        // 網路API連線套件Retrofit ( 食物資訊樣貌API :  https://www.openapi.org.tw/#/api/e3fa5230-8c8a-11ea-8b2f-dfcba39a3448/info )
        bt_Retrofitfood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this, RetrofitTestActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        // 網路API連線套件Retrofit ( 人氣店家排行榜API :  https://www.openapi.org.tw/#/api/9dc5e0c0-e8e4-11ea-a441-4ffa89b0e5f1/info )
        bt_parking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this, ParkingInfoRetrofitActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        // 各類程式碼紀錄區
        bt_code_notes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this, CodeTestDemoActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        //電池資訊
        bt_battery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this, BatteryActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

        //Room & Stetho
        bt_room_stetho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this, RoomStethoActivity.class);
                startActivity(intent);
                HomeMainActivity.this.finish();//結束目前 Activity
            }
        });

    }



    /**
     * Java objects to JSON
     */
    public void todoToJson(View view){
        Log.i(TAG,"#todoToJson : " + MyApi.MY_CHANNEL_NAME);
    }

    public void todoListToJsonArray(View view){
        Log.i(TAG,"#todoList To JsonArray : ");
    }

    public void userToJson(View view){
        Log.i(TAG,"#user To Json : ");
    }


    /**
     * Json objects(arrays) to Java Objects
     */
    public void todoJsonTotodoObject(View view){
        Log.i(TAG,"---- todoJsonTotodoObject ----");
        Gson gson = new Gson();
        Todo todo = gson.fromJson(MyApi.TODO_JSON_STRING, Todo.class);
        Log.i(TAG,"#todoJsonTotodoObject : " + todo.toString());// 取全部轉字串
        Log.i(TAG,"#todoJsonTotodoObject : " + todo.getBody());// 各別取出body
    }

    public void todoJsonArrayTotodoArray(View view){
        Log.i(TAG,"---- todoJsonArrayTotodoArray ----");
        Gson gson = new Gson();
        Todo[] todos = gson.fromJson(MyApi.TODO_JSON_ARRAY_STRING,Todo[].class);
        Log.i(TAG,"#todoJsonArrayTotodoArray : " + Arrays.toString(todos));
    }

    public void todoJsonArrayTotodoList(View view){
        Log.i(TAG,"---- todoJsonArrayTotodoList ----");
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Todo>>(){}.getType();
        List<Todo> todoList = gson.fromJson(MyApi.TODO_JSON_ARRAY_STRING,listType);
        Todo todo = todoList.get(0);
        Log.i(TAG,"# todoJsonArrayTotodoList : " + todo.toString());
        Log.i(TAG,"# todoJsonArrayTotodoList : " + todo.getBody());
        }

    public void userJsonToUserUserObject(View view){
        Log.i(TAG,"---- userJsonToUserUserObject ---- ");
        Gson gson = new Gson();
        User user = gson.fromJson(MyApi.USER_JSON_STRING,User.class);
        Log.i(TAG,"# userJsonToUserUserObject : "+ user.toString());
        Log.i(TAG,"# userJsonToUserUserObject : "+ user.getAddress().getGeo().getLat());
        Log.i(TAG,"# userJsonToUserUserObject : "+ user.getAddress().getGeo().getLng());

    }

}
