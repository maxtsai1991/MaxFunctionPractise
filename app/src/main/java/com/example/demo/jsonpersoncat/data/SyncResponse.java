package com.example.demo.jsonpersoncat.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
/*
建立資料Model : Retrofit會自動將連線取得的資料轉成物件便於做後續處理
注意 : 欄位名稱要跟API裡的一致
API URL (用GET) : https://gw.openapi.org.tw/18463fd0-8aa7-11ea-8b2f-dfcba39a3448/6ace07502582?client_id=5c53d140-7369-11ec-bcd3-2fe59eaf628f&client_secret=lVwHXHNmAl9aB14f8h%2FDHcqMXUZi2bBtvxL%2FatbLKFs%3D&skip=10&limit=25
Postman 下方Response
API Response 有很多欄位 先取幾個欄位使用: "name" "weight" "unit" "calories" "iconUrl"
 */
public class SyncResponse {

    @SerializedName("success")
    private boolean mIsSuccess = false;

    @SerializedName("data")
    private List<FoodData> mFoodDataList = new ArrayList<>();

    public class FoodData {

        @SerializedName("name") // 中譯:食物名稱
        private String mName = "";

        @SerializedName("weight") // 中譯:食物重量
        private int mWeight;

        @SerializedName("unit") // 中譯:食物重量單位
        private String mUnit = "";

        @SerializedName("calories") // 中譯:食物熱量
        private int mCalories;

        @SerializedName("iconUrl") // 中譯:圖片位置
        private String mIconUrl = "";

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public int getmWeight() {
            return mWeight;
        }

        public void setmWeight(int mWeight) {
            this.mWeight = mWeight;
        }

        public String getmUnit() {
            return mUnit;
        }

        public void setmUnit(String mUnit) {
            this.mUnit = mUnit;
        }

        public int getmCalories() {
            return mCalories;
        }

        public void setmCalories(int mCalories) {
            this.mCalories = mCalories;
        }

        public String getmIconUrl() {
            return mIconUrl;
        }

        public void setmIconUrl(String mIconUrl) {
            this.mIconUrl = mIconUrl;
        }
    }

    public boolean ismIsSuccess() {
        return mIsSuccess;
    }

    public void setmIsSuccess(boolean mIsSuccess) {
        this.mIsSuccess = mIsSuccess;
    }

    public List<FoodData> getmFoodDataList() {
        return mFoodDataList;
    }

    public void setmFoodDataList(List<FoodData> mFoodDataList) {
        this.mFoodDataList = mFoodDataList;
    }
}
