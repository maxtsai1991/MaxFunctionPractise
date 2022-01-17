package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GsonJsonTestActivity extends AppCompatActivity {
    private Button bt;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_json_test);
        bt = findViewById(R.id.bt_return_home);


        handleBt();
    }

    private void handleBt() {

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "123", Toast.LENGTH_SHORT).show();
//                try {
//                    // jsonstringer P511
//                    String jsonString = new JSONStringer()
//                            .object()
//                            .key("JSON").value("Hello, World!.")
//                            .endObject()
//                            .toString();
//
//                    Log.i("TAG","jsonString : " + jsonString);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }


}