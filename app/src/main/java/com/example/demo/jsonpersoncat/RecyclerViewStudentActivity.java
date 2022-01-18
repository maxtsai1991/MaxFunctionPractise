package com.example.demo.jsonpersoncat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.jsonpersoncat.adapters.StudentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

    /*
    參考網址: https://thumbb13555.pixnet.net/blog/post/311803031
    RecycleView 下拉刷新及點擊事件 : https://thumbb13555.pixnet.net/blog/post/312844960-android-studio-%E4%B9%8B%E5%9F%BA%E6%9C%ACrecycleview-%E7%94%A8%E6%B3%95-2--%E5%9F%BA%E6%9C%AC%E7%89%88%E4%B8%8B
    RecycleView 上下滑動排序與側滑刪除 : https://thumbb13555.pixnet.net/blog/post/316420566-recyclerview-swipe
    Github:https://github.com/thumbb13555/SimpleRecycleViewExample
    Github(activity_main.xml & recycle_item.xml):https://github.com/thumbb13555/SimpleRecycleViewExample/tree/master/app/src/main/res/layout
    Github(RecyclerViewSwipeDelete):https://github.com/thumbb13555/RecyclerViewSwipeDelete
    官方範例:https://developer.android.com/guide/topics/ui/layout/recyclerview
    色卡的網站:https://nipponcolors.com/
    1.以迴圈的方法產生30人(亦可於程式調整)的成績數值
    2.科目1與科目2皆是亂數產生
    3.平均值是取科目一&科目二的分數下去平均後顯示
    4.座號欄會根據平均分數有所變化，變化規則為:
    >80 ->綠
    <=80 && >60 ->藍
    <=60 && >40 ->黃
    <=40 ->紅
    此外也在這提供四個顏色的色卡
    紅：#C73E3A
    黃：#FFB11B
    綠：#1B813E
    藍：#005CAF
     */
public class RecyclerViewStudentActivity extends AppCompatActivity {
    public Button bt_return_home;
    public RecyclerView mRecyclerView;
//    public StudentAdapter myStudentAdapter; // 如用靜態adapter 就會用到它
    public StudentAdapter studentAdapter ;
    public SwipeRefreshLayout swipeRefreshLayout;
    public ArrayList<HashMap<String,String>>arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_student);
        //返回首頁按鈕
        bt_return_home = findViewById(R.id.bt_return_home);
        bt_return_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewStudentActivity.this, HomeMainActivity.class);
                startActivity(intent);
                RecyclerViewStudentActivity.this.finish();//結束目前 Activity
            }
        });

        //製造資料
        makeData();

        //設置RecycleView
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 每個item 之間用橫線區隔開
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        // new出adapter物件 塞學生假資料 給RecyclerView
        mRecyclerView.setAdapter(new StudentAdapter(arrayList));

        // 因adapter 拆出去一個class才需要寫這段
        studentAdapter = new StudentAdapter();

        //下拉刷新
        swipeRefreshLayout = findViewById(R.id.refreshLayout);
        // 設定刷新圈圈的顏色
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.green_TOKIWA));
        swipeRefreshLayout.setOnRefreshListener(()->{
            // 資料清空(清除表單資料)
            arrayList.clear();
            // 載入資料(呼叫假資料)
            makeData();
            // 通知Adapter資料有被改變 (Adapter 刷新)
            studentAdapter.notifyDataSetChanged();
            // 清除轉圈圈 (讓刷新的圈圈消滅方法)
            swipeRefreshLayout.setRefreshing(false);
        });
    }
        //製造生成假資料 : 迴圈跑30次表示30個學生
        private void makeData() {
            for (int i = 0;i<30;i++){
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("Id","座號："+String.format("%02d",i+1)); // String.format("%02d",i+1)是讓字串補零的方法
                hashMap.put("Sub1",String.valueOf(new Random().nextInt(80) + 20));
                hashMap.put("Sub2",String.valueOf(new Random().nextInt(80) + 20));
                hashMap.put("Avg",String.valueOf(
                        (Integer.parseInt(hashMap.get("Sub1"))
                                +Integer.parseInt(hashMap.get("Sub2")))/2));
                arrayList.add(hashMap);
            }
        }

        /*
        Adapter 可寫成內部類別 也可獨立一個Class檔(StudentAdapter.java)
         */
//    private static class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
//        /*
//        設定完RecycleView的控制區域 :
//        元件的連結需要在大的class ( MyListAdapter )底下再新增一個小的class ,並繼承於RecycleView.ViewHolder ,這樣裡面就可以取用方法並在內部連結所需要的控件
//         */
//        class ViewHolder extends RecyclerView.ViewHolder {
//            // 在VIEWHOLDER內加入我們每個物件(item)
//            private TextView tvId, tvSub1, tvSub2, tvAvg;
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//                tvId = itemView.findViewById(R.id.textView_Id); //座位流水號
//                tvSub1 = itemView.findViewById(R.id.textView_sub1); // 科目1 : 分數為亂數
//                tvSub2 = itemView.findViewById(R.id.textView_sub2); // 科目2 : 分數為亂數
//                tvAvg = itemView.findViewById(R.id.textView_avg);   // 平均分 : (科目1 + 科目2) / 2
//            }
//        }
//
//        @NonNull
//        @Override
//        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            // 利用LayoutInflater方法載入介面
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_student, parent, false);
//            return new ViewHolder(view);
//        }
//
//        /*
//        onBindViewHolder方法是用來管控內部元件的操作的
//        需要準備的材料基本上就是你再onCreate內已經載入好的arrayList
//        這時候只要善用onBindViewHolder內的兩個傳值，就可以在陣列中取出你要的值並設定囉
//        傳值1是ViewHolder內的 holder，該用意就是連結剛才宣告過的元件
//        傳值2是int 的position，簡單來說就像是for迴圈內的那個變數一樣
//         */
//        @Override
//        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//            int avgS = Integer.parseInt(arrayList.get(position).get("Avg")); //取得平均值並在下面設定平均值範圍的顏色
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (avgS >= 80) { // 平均值80(含)以上 顯示綠色
//                    holder.tvId.setBackgroundColor(getColor(R.color.green_TOKIWA));
//                } else if (avgS < 80 && avgS >= 60) {// 平均值60(含)以上 顯示藍色
//                    holder.tvId.setBackgroundColor(getColor(R.color.blue_RURI));
//                } else if (avgS < 60 && avgS >= 40) {// 平均值40(含)以上 顯示黃色
//                    holder.tvId.setBackgroundColor(getColor(R.color.yellow_YAMABUKI));
//                } else {
//                    holder.tvId.setBackgroundColor(getColor(R.color.red_GINSYU));
//                }
//                /*
//                設定學號的欄位(holder.tvId) :
//                就可以取得他的內容
//                而因為我這邊需要設定顯示的值
//                因此呼叫前面的arrayList陣列
//                取得裡面的ID值
//
//                取得資料的時候必須是這樣的語法(arrayList.get(position).get("Id")) :
//                 */
//                holder.tvId.setText(arrayList.get(position).get("Id"));
//                holder.tvSub1.setText(arrayList.get(position).get("Sub1"));
//                holder.tvSub2.setText(arrayList.get(position).get("Sub2"));
//                holder.tvAvg.setText(arrayList.get(position).get("Avg"));
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return arrayList.size();
//        }
//    }

    }