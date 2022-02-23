package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
            " 『Android studio』BatteryManager獲取手機裝置電池狀況BatteryManager "
 Code 出處 : https://thumbb13555.pixnet.net/blog/post/333030259-batterymanager?utm_source=PIXNET&utm_medium=Hashtag_article
 */
public class BatteryActivity extends AppCompatActivity {
    private TextView tvBatterResult;
    private Button bt_battery_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        tvBatterResult = findViewById(R.id.tvBatterResult);
        bt_battery_return = findViewById(R.id.bt_battery_return);

        IntentFilter mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mIntentReceiver, mIntentFilter);

        bt_battery_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(BatteryActivity.this, HomeMainActivity.class);
                startActivity(intent);
                BatteryActivity.this.finish();//結束目前 Activity
            }
        });

    }
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
//從這邊開始
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                StringBuilder builder = new StringBuilder();

                builder.append("電池狀態: ");
                int status = intent.getIntExtra("status", 0); // 电池状态
                switch (status){
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        builder.append("充電中");
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        builder.append("放電中");
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        builder.append("未充電");
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        builder.append("電量已滿");
                        break;
                }
                builder.append("\n");

                builder.append("電池健康情形: ");
                int health = intent.getIntExtra("health", 0);
                switch (health){
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        builder.append("良好");
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        builder.append("溫度過高");
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        builder.append("電池已耗盡");
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        builder.append("過電壓");
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        builder.append("未知錯誤");
                        break;
                }
                builder.append("\n");

                builder.append("電池最大容量: ");
                int scale = intent.getIntExtra("scale", 0);
                builder.append(scale+"%");
                builder.append("\n");

                builder.append("電池電壓: ");
                int voltage = intent.getIntExtra("voltage", 0);
                builder.append(voltage+"mV");
                builder.append("\n");

                builder.append("電池電量: ");
                int level = intent.getIntExtra("level", 0);
                builder.append(level+"%");
                builder.append("\n");

                builder.append("電池溫度: ");
                int temperature = intent.getIntExtra("temperature", 0);
                builder.append((Math.round(temperature)/10.0)+"度");
                builder.append("\n");

                tvBatterResult.setText(builder.toString());
            }
            //到這邊
        }
    };

}