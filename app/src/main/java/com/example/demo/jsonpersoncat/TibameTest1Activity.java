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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tibame_test1);



        findViews();
        handleBtReturnHome();
        handleAutoCompleteTextView();
        handleButton();
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


}