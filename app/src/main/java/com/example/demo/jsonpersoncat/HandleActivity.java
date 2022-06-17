package com.example.demo.jsonpersoncat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 29-Android中的非同步任務與多執行緒
 *
 */
public class HandleActivity extends AppCompatActivity {
    private TextView tv_content;
    private Button bt_contentopen;

    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0){
                String strData = (String) msg.obj;
                tv_content.setText(strData);
                Toast.makeText(HandleActivity.this, "主執行緒收到消息了~~~~~~", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);
        tv_content = findViewById(R.id.tv_content);
        bt_contentopen = findViewById(R.id.bt_contentopen);

    }

    public void start(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringFromNet = getStringFromNet();

                Message message = new Message();
                message.what = 0; // message.what用來區分誰發的消息的
                message.obj = stringFromNet;

                mHandler.sendMessage(message);
            }
        }).start();

//        String stringFromNet = getStringFromNet();
//        tv_content.setText(stringFromNet);
        Toast.makeText(this, "開始任務~~~~~~", Toast.LENGTH_SHORT).show();
    }

    private String getStringFromNet() {
        // 假設從網路獲取一個字串
        String result = "";
        
        StringBuilder stringBuilder = new StringBuilder();
        
        // 模擬一個耗時操作
        for (int i = 0; i < 100; i++){
            stringBuilder.append("字串" + i + "\n");
        }
        
        try{
            Thread.sleep(3500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        
        result = stringBuilder.toString();
        
        return result;
    }

    /**
     * 初始化Toolbar內SearchView的設置
     * 建立選單(Menu) : https://ithelp.ithome.com.tw/articles/10188217
     * Android的各種Bar，從ActionBar到CollapsingToolbarLayout :
     * https://medium.com/evan-android-note/android-%E7%9A%84%E5%90%84%E7%A8%AEbar-%E5%BE%9Eactionbar%E5%88%B0collapsingtoolbarlayout-c95d33640be4
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 設置要用哪個menu檔做為選單
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.hometest){
            Toast.makeText(this, "點選了回首頁", Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(HandleActivity.this,HomeMainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}