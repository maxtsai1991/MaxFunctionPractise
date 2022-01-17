package com.example.demo.jsonpersoncat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.jsonpersoncat.obj.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecyclerCardViewActivity extends AppCompatActivity {
    // 測試資料
    private final List<Book>  androidInfoMap = getBookList();
    // Spinner-spName使用的選項集合
    private List<String> itemList;
    // Spinner-spName使用的Adapter
    private ArrayAdapter<String> adapter;


    private RecyclerView recyclerView;
    private TextView tv_title_name;
    private ImageView iv_arrow_back,iv_home;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card_view);
        findViews();
        handleRecyclerView();
        handleReturnHome();
        handleSpinner();
        tv_title_name.setText("RecycleSpinner");
    }

    private void findViews() {
        // 4.1取得RecyclerView參考
        recyclerView = findViewById(R.id.recyclerView);
        tv_title_name = findViewById(R.id.tv_title_name);
        iv_arrow_back = findViewById(R.id.iv_arrow_back);
        spinner = findViewById(R.id.spinner);
    }

    private void handleSpinner() {
        /**
         * spinner 方法有二:
         * https://ithelp.ithome.com.tw/articles/10261820
         * 參考這
         */

        // 建立一個ArrayAdapter元件，並將加入下拉式選項
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,new String[]{"突破困境","大話AWS雲端架構","為你自己學Git","無瑕的程式碼","大話設計模式","敏捷軟體開發技巧守則","iOS App 程式開發實務攻略","獨角獸專案","PowerShell","機器學習的數學基礎"});
        // 建立下拉式選單樣式
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView itemView = (TextView) view;
                final String text = String.valueOf(itemView.getText());
                List<Book> androidList = androidInfoMap;
                itemList = new ArrayList<>();
                if (androidList != null) {
                    for (Book item : androidList) {
                        itemList.add(item.getTitle());
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void handleRecyclerView() {
        // 4.2 設定Adapter
        recyclerView.setAdapter(new MyAdapter(this, getBookList()));
        // 4.3 設定LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void handleReturnHome() {
        iv_arrow_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerCardViewActivity.this, HomeMainActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 3. 自定義Adapter類別
     * 3.1 繼承RecyclerView.Adapter
     */
    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        // 3.2 欄位: Context物件、選項資料物件
        private final Context context;
        private final List<Book> list;

        // 3.3 建構子: 2個參數(Context型態、選項資料的型態)，用來初始化2欄位
        private MyAdapter(Context context, List<Book> list) {
            this.context = context;
            this.list = list;
        }

        // 3.4 內部類別: 自定義ViewHolder類別
        // 3.4.1 繼承RecyclerView.ViewHolder
        private static class MyViewHolder extends RecyclerView.ViewHolder {
            // 3.4.2 欄位: 對應選項容器元件，之內的所有元件
            ImageView imageView;
            TextView textView;

            // 3.4.3 建構子: 1個參數(View型態)，該參數就是選項容器元件，用來取得各容器元件的參考
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);
            }
        }

        // 3.5 方法(MyAdapter): 覆寫以下3方法
        // 3.5.1 getItemCount(): 回傳選項數量
        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        // 3.5.2 onCreateViewHolder()
        //  宣告itemView，並載入選項容器元件的外觀
        //  實例化自定義的ViewHolder類別，並回傳
        // 註:LayoutInflater.inflate()的第3個參數attachToRoot表⽰是否直接被⽗元件包含，此處⼀定要寫false
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.e("tag", "onCreateViewHolder");
            View itemView = LayoutInflater.from(context).inflate(R.layout.recycler_cardview_item_view,parent,false);

            return new MyViewHolder(itemView);
        }

        // 3.5.3 onBindViewHolder()
        //  透過ViewHolder物件，將資料綁定 至 各元件上
        //  各元件的其他處理，EX.註冊/實作監聽器
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.e("tag", "onBindViewHolder :"+position);
            final Book book = list.get(position);
            holder.imageView.setImageResource(book.getImageId());
            holder.textView.setText(book.getTitle());
            final String text = position + 1 + ": " + book.getTitle() ;
            holder.itemView.setOnClickListener(view -> Toast.makeText(context, text, Toast.LENGTH_SHORT).show());
        }
    }
    /**
     * 取得書籍測試資料
     */
    private List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(R.drawable.book01, "突破困境：資安開源工具應用"));
        bookList.add(new Book(R.drawable.book02, "大話 AWS 雲端架構：雲端應用架構圖解輕鬆學"));
        bookList.add(new Book(R.drawable.book03, "為你自己學 Git"));
        bookList.add(new Book(R.drawable.book04, "無瑕的程式碼－整潔的軟體設計與架構篇"));
        bookList.add(new Book(R.drawable.book05, "大話設計模式"));
        bookList.add(new Book(R.drawable.book06, "無瑕的程式碼－敏捷軟體開發技巧守則"));
        bookList.add(new Book(R.drawable.book07, "iOS App 程式開發實務攻略：快速精通 SwiftUI"));
        bookList.add(new Book(R.drawable.book08, "獨角獸專案｜看IT部門如何引領百年企業振衰起敝，重返榮耀"));
        bookList.add(new Book(R.drawable.book09, "PowerShell 流程自動化攻略"));
        bookList.add(new Book(R.drawable.book10, "機器學習的數學基礎 : AI、深度學習打底必讀"));
        return bookList;
    }
}