package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CodeTestDemoActivity extends AppCompatActivity {
    private Button bt_viewbutton,bt_return_codedemo;
    private TextView tv_view_remark,tv_viewblock;
    private ImageView iv_code_pic;
    private String operatorString = "java &與&&的區別： & 既是位運算子又是邏輯運算子，&的兩側可以是int，也可以是boolean表示式，當&兩側是int時，要先把運算子兩側的數轉化為二進位制數再進行運算，而短路與（&&）的兩側要求必須是布林表示式。";
    private String operatorStringTwo = "我們現在可能有一些模糊不清，現在我們先看看&和&&的電路問題：\n" +
            "\n" +
            "對於：&&\n" +
            "\n" +
            "if(str != null && !””.equals(str))\n" +
            "\n" +
            "當： str != null 的時候，接下來才會去執行： !””.equals(str)\n" +
            "\n" +
            "如果： str != null為false，那麼這個時候，程式是處於短路的情況，則，!””.equals(str) 是不會執行的。\n" +
            "\n" +
            "但是對於：&\n" +
            "\n" +
            "if(str != null & !””.equals(str))\n" +
            "\n" +
            "不管： str != null 的結果如何(即true，false)，程式都會執行： !””.equal(str)\n" +
            "\n" +
            "電路問題總結：\n" +
            "\n" +
            "對於：&   — >  不管怎樣，都會執行”&”符號左右兩邊的程式\n" +
            "\n" +
            "對於：&& — >  只有當符號”&&”左邊程式為真(true)後，才會執行符號”&&”右邊的程式。\n" +
            "\n" +
            "下面來說說運算規則：\n" +
            "\n" +
            "對於：&  — >  只要左右兩邊有一個為false，則為false；只有全部都為true的時候，結果為true\n" +
            "\n" +
            "對於：&& — > 只要符號左邊為false，則結果為false；當左邊為true，同時右邊也為true，則結果為true";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_test_demo);

        findViews();
        enterTheNoteCodeDisplay();
        btreturn();
    }

    private void findViews() {
        bt_viewbutton = findViewById(R.id.bt_viewbutton);
        tv_view_remark = findViewById(R.id.tv_view_remark); // 程式碼說明區
        tv_viewblock = findViewById(R.id.tv_viewblock); // 顯示區
        iv_code_pic = findViewById(R.id.iv_code_pic); // 顯示照片
        bt_return_codedemo = findViewById(R.id.bt_return_codedemo);

    }

    private void btreturn() {
        bt_return_codedemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeTestDemoActivity.this, HomeMainActivity.class);
                startActivity(intent);
                CodeTestDemoActivity.this.finish();//結束目前 Activity
            }
        });
    }

    private void enterTheNoteCodeDisplay() { // 顯示下方TV 及 IM 的點擊事件
        bt_viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator();
            }
        });
    }

    // java &與&&的區別
    private void operator(){
        tv_view_remark.setText(operatorString);
        iv_code_pic.setImageResource(R.drawable.operator);
        tv_viewblock.setText(getFromAssets(this, "operatordoc.txt"));

    }

    public static String getFromAssets(Context context, String fileName) {
        // https://blog.csdn.net/Tokyo_2024/article/details/100068491
        InputStreamReader inputReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            StringBuilder result = new StringBuilder();
            String line;
            while((line = bufReader.readLine()) != null) {
                result.append(line);
//                result.append(Arrays.toString("\r\n".getBytes()));
            }
            String var6 = result.toString() + "\n";
            return var6;
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            if(null != inputReader) {
                try {
                    inputReader.close();
                } catch (IOException var15) {
                    var15.printStackTrace();
                }
            }
        }
        return null;
    }



}