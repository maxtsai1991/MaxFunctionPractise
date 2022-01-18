package com.example.demo.jsonpersoncat.adapters;

import static com.example.demo.jsonpersoncat.R.color.blue_RURI;
import static com.example.demo.jsonpersoncat.R.color.green_TOKIWA;
import static com.example.demo.jsonpersoncat.R.color.red_GINSYU;
import static com.example.demo.jsonpersoncat.R.color.yellow_YAMABUKI;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.jsonpersoncat.R;
import com.example.demo.jsonpersoncat.RecyclerViewStudentActivity;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    private View mView;

    public StudentAdapter() {
    }

    public StudentAdapter(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    /*
        設定完RecycleView的控制區域 :
        元件的連結需要在大的class ( MyListAdapter )底下再新增一個小的class ,並繼承於RecycleView.ViewHolder ,這樣裡面就可以取用方法並在內部連結所需要的控件
         */
    class ViewHolder extends RecyclerView.ViewHolder {
        // 在VIEWHOLDER內加入我們每個物件(item)
        private TextView tvId, tvSub1, tvSub2, tvAvg;
        private View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.textView_Id); //座位流水號
            tvSub1 = itemView.findViewById(R.id.textView_sub1); // 科目1 : 分數為亂數
            tvSub2 = itemView.findViewById(R.id.textView_sub2); // 科目2 : 分數為亂數
            tvAvg = itemView.findViewById(R.id.textView_avg);   // 平均分 : (科目1 + 科目2) / 2
            mView = itemView; // 資料型態為View 把這個View跟底下ViewHolder的View綁再一起
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 利用LayoutInflater方法載入介面
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    /*
    onBindViewHolder方法是用來管控內部元件的操作的
    需要準備的材料基本上就是你再onCreate內已經載入好的arrayList
    這時候只要善用onBindViewHolder內的兩個傳值，就可以在陣列中取出你要的值並設定囉
    傳值1是ViewHolder內的 holder，該用意就是連結剛才宣告過的元件
    傳值2是int 的position，簡單來說就像是for迴圈內的那個變數一樣
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int avgS = Integer.parseInt(arrayList.get(position).get("Avg")); //取得平均值並在下面設定平均值範圍的顏色


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (avgS >= 80) { // 平均值80(含)以上 顯示綠色
                holder.tvId.setBackgroundColor(green_TOKIWA);
            } else if (avgS < 80 && avgS >= 60) {// 平均值60(含)以上 顯示藍色
                holder.tvId.setBackgroundColor(blue_RURI);
            } else if (avgS < 60 && avgS >= 40) {// 平均值40(含)以上 顯示黃色
                holder.tvId.setBackgroundColor(yellow_YAMABUKI);
            } else {
                holder.tvId.setBackgroundColor(red_GINSYU);
            }
                /*
                設定學號的欄位(holder.tvId) :
                就可以取得他的內容
                而因為我這邊需要設定顯示的值
                因此呼叫前面的arrayList陣列
                取得裡面的ID值

                取得資料的時候必須是這樣的語法(arrayList.get(position).get("Id")) :
                 */
            holder.tvId.setText(arrayList.get(position).get("Id"));
            holder.tvSub1.setText(arrayList.get(position).get("Sub1"));
            holder.tvSub2.setText(arrayList.get(position).get("Sub2"));
            holder.tvAvg.setText(arrayList.get(position).get("Avg"));
            // Item點擊事件
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 點擊item 彈出 吐司 科目1 & 科目2 & 平均分數
                    Toast.makeText(v.getContext(), "科目1 : " + holder.tvSub1.getText() + ",\n" + "科目2 : " + holder.tvSub2.getText() + ",\n" + " 平均分數 : "+ holder.tvAvg.getText() ,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}



