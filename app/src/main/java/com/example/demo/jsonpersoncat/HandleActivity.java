package com.example.demo.jsonpersoncat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
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
}