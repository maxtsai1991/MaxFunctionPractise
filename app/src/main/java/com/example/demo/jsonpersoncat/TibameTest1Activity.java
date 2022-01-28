package com.example.demo.jsonpersoncat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;

public class TibameTest1Activity extends AppCompatActivity {
    private Button bt_tibame_return_home;
    private Resources resources;
    private EditText editText;
    private TextInputEditText textInputEditText;
    private AutoCompleteTextView acTextView;
    private Button button;
    private TextView tvResult;
    private ProgressBar progressBar1;
    private SeekBar seekBar;
    private TextView textView1, textView2;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tibame_test1);



        findViews();
        handleBtReturnHome();
        handleAutoCompleteTextView();
        handleButton();

        handleProgressBar();
        handleSeekBar();
        handleRatingBar();
    }

    /**
     * 取得元件參考
     */
    private void findViews() {
        bt_tibame_return_home = findViewById(R.id.bt_tibame_return_home);
        editText = findViewById(R.id.editText);
        textInputEditText = findViewById(R.id.textInputEditText);
        acTextView = findViewById(R.id.acTextView);
        button = findViewById(R.id.button);
        tvResult = findViewById(R.id.tvResult);

        progressBar1 = findViewById(R.id.progressBar1);
        seekBar = findViewById(R.id.seekBar);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        ratingBar = findViewById(R.id.ratingBar);
    }

    private void handleBtReturnHome() {

        bt_tibame_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TibameTest1Activity.this, HomeMainActivity.class);
                startActivity(intent);
                TibameTest1Activity.this.finish();//結束目前 Activity
            }
        });
    }

    /**
     * AutoCompleteTextView相關處理
     */
    private void handleAutoCompleteTextView() {
        final List<String> itemList = Arrays.asList("Taiwan", "Thailand", "Japan", "Korea", "US", "Canada");
        acTextView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_view, itemList));
    }

    /**
     * Button相關處理
     */
    private void handleButton() {
        button.setOnClickListener(view -> {
            // 確認Username不可為空
            final String username = String.valueOf(editText.getText());
            if (username.isEmpty()) {
                editText.setError(resources.getString(R.string.textUsernameRequired));
                return;
            }

            // 確認Password不可為空
            final String password = String.valueOf(textInputEditText.getText());
            if (password.isEmpty()) {
                textInputEditText.setError(resources.getString(R.string.textPasswordRequired));
                return;
            }

            // 確認country不可為空
            final String country = String.valueOf(acTextView.getText());
            if (country.isEmpty()) {
                acTextView.setError(resources.getString(R.string.textCountryRequired));
                return;
            }

            // 顯示結果
            tvResult.setText("");
            tvResult.append("Username: ");
            tvResult.append(username);
            tvResult.append("\n");
            tvResult.append("Password: ");
            tvResult.append(password);
            tvResult.append("\n");
            tvResult.append("Country: ");
            tvResult.append(country);
        });
    }

    /**
     * ProgressBar相關處理
     */
    private void handleProgressBar() {
        new Thread(() -> {
            final int max = progressBar1.getMax();
            int progress;
            while ((progress = progressBar1.getProgress()) < max) {
                progressBar1.setProgress(progress + 1);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * SeekBar相關處理
     */
    private void handleSeekBar() {
        // 將SeekBar的預設值設定為TextView的文字大小
        final float sizePx = textView1.getTextSize();
        // textView.getTextSize()取得的大小之單位為px，須手動轉換成sp
        final float sizeSp = sizePx / getResources().getDisplayMetrics().scaledDensity;
        seekBar.setProgress((int) sizeSp);

        // 註冊拖曳條改變監聽器
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 當數值改變時，自動被呼叫
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 將TextView的文字大小設定為當前數值
                textView1.setTextSize(progress);
            }

            // 當開始改變拖曳條(被按住)時，自動被呼叫
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            // 當停止改變拖曳條(被放開)時，自動被呼叫
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /**
     * RatingBar相關處理
     */
    private void handleRatingBar() {
        // 評分條改變監聽器
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            textView2.setText(rating + " 分");
        });
    }

}