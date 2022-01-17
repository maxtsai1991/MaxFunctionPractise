package com.example.demo.jsonpersoncat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
參考網址: https://thumbb13555.pixnet.net/blog/post/311803031
Github:https://github.com/thumbb13555/SimpleRecycleViewExample
Github(activity_main.xml & recycle_item.xml):https://github.com/thumbb13555/SimpleRecycleViewExample/tree/master/app/src/main/res/layout
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
    public RecyclerView mRecyclerView;
    public MyListAdapter myListAdapter;
    public ArrayList<HashMap<String,String>>arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_student);

        //製造資料
        for (int i = 0;i<30;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("Id","座號："+String.format("%02d",i+1));
            hashMap.put("Sub1",String.valueOf(new Random().nextInt(80) + 20));
            hashMap.put("Sub2",String.valueOf(new Random().nextInt(80) + 20));
            hashMap.put("Avg",String.valueOf(
                    (Integer.parseInt(hashMap.get("Sub1"))
                            +Integer.parseInt(hashMap.get("Sub2")))/2));

            arrayList.add(hashMap);
        }
        //設置RecycleView
        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myListAdapter = new MyListAdapter();
        mRecyclerView.setAdapter(myListAdapter);
    }//onCreate

    private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{


        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView tvId,tvSub1,tvSub2,tvAvg;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvId = itemView.findViewById(R.id.textView_Id);
                tvSub1 = itemView.findViewById(R.id.textView_sub1);
                tvSub2 = itemView.findViewById(R.id.textView_sub2);
                tvAvg  = itemView.findViewById(R.id.textView_avg);
            }
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_student,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int avgS = Integer.parseInt(arrayList.get(position).get("Avg"));
            if (avgS>=80){
                holder.tvId.setBackgroundColor(getColor(R.color.green_TOKIWA));
            }else if (avgS<80 &&avgS>=60){
                holder.tvId.setBackgroundColor(getColor(R.color.blue_RURI));
            }else if(avgS<60 &&avgS>=40){
                holder.tvId.setBackgroundColor(getColor(R.color.yellow_YAMABUKI));
            }else {
                holder.tvId.setBackgroundColor(getColor(R.color.red_GINSYU));
            }
            holder.tvId.setText(arrayList.get(position).get("Id"));
            holder.tvSub1.setText(arrayList.get(position).get("Sub1"));
            holder.tvSub2.setText(arrayList.get(position).get("Sub2"));
            holder.tvAvg.setText(arrayList.get(position).get("Avg"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

}