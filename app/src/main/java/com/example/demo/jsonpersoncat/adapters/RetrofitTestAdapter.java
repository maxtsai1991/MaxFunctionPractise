package com.example.demo.jsonpersoncat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo.jsonpersoncat.R;
import com.example.demo.jsonpersoncat.data.SyncResponse;

import java.util.List;


/*
    RetrofitTestActivity.java 的Adapter
 */
    public class RetrofitTestAdapter extends RecyclerView.Adapter<RetrofitTestAdapter.ViewHolder>{
        private final List<SyncResponse.FoodData> list;

        public RetrofitTestAdapter(List<SyncResponse.FoodData> list) {
            this.list = list;
        }


        static class ViewHolder extends RecyclerView.ViewHolder{
            // 宣告item上的元件
            ImageView iv_food_pic;// 中譯:圖片位置
            TextView tv_food_name, tv_food_weight, tv_food_weight_unit,tv_food_calories;// 中譯:食物名稱,食物重量,食物重量單位,食物熱量

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_food_pic = itemView.findViewById(R.id.iv_food_pic);          // 食物圖片
                tv_food_name = itemView.findViewById(R.id.tv_foodname);         // 食物名稱
                tv_food_weight = itemView.findViewById(R.id.tv_unit_weight);    // 食物重量
                tv_food_weight_unit = itemView.findViewById(R.id.tv_unit_name); // 食物重量單位
                tv_food_calories = itemView.findViewById(R.id.tv_food_calories);// 食物熱量
            }

            // 處理API資料後設定給對應的元件
            public void setApiData(String url, String foodname, int weight, int calories, String unit){
                // 加載圖片,參考處 : https://muyangmin.github.io/glide-docs-cn/doc/getting-started.html
                Glide.with(iv_food_pic)
                        .load(url)
                        .into(iv_food_pic);
                tv_food_name.setText(foodname);
                tv_food_weight.setText(String.valueOf(weight)); // int change to string
                tv_food_calories.setText(calories + "");        // int change to string (偷吃步方法: int轉String)
                tv_food_weight_unit.setText(unit);
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 連接recycler_cardview_item_view.xml檔案，return一個View
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foodinfo,parent,false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // 在這裡取得元件的控制(每個item內的控制)
            final SyncResponse.FoodData food = list.get(position);
            holder.setApiData(food.getmIconUrl(), food.getmName(), food.getmWeight(),food.getmCalories(),food.getmUnit());


        }

        @Override
        public int getItemCount() {
            // 取得顯示數量，return一個int，通常都會return陣列長度(arrayList.size)
            return list == null ? 0 : list.size();
        }
    }

