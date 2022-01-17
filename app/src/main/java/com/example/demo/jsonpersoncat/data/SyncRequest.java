package com.example.demo.jsonpersoncat.data;

import com.google.gson.annotations.SerializedName;


/*
 Postman URL(用GET):https://gw.openapi.org.tw/18463fd0-8aa7-11ea-8b2f-dfcba39a3448/6ace07502582?client_id=5c53d140-7369-11ec-bcd3-2fe59eaf628f&client_secret=lVwHXHNmAl9aB14f8h%2FDHcqMXUZi2bBtvxL%2FatbLKFs%3D&skip=10&limit=25
 */
public class SyncRequest {
    // Postman 的 Query Params 欄位 (API參數)
    @SerializedName("client_id") // 跟欄位一模一樣名稱
    private String mClient_id = "";

    @SerializedName("client_secret")
    private String mClient_secret = "";

    @SerializedName("skip")
    private Integer mSkip = null;

    @SerializedName("limit")
    private Integer mLimit = null;

    public String getmClient_id() {
        return mClient_id;
    }

    public void setmClient_id(String mClient_id) {
        this.mClient_id = mClient_id;
    }

    public String getmClient_secret() {
        return mClient_secret;
    }

    public void setmClient_secret(String mClient_secret) {
        this.mClient_secret = mClient_secret;
    }

    public Integer getmSkip() {
        return mSkip;
    }

    public void setmSkip(Integer mSkip) {
        this.mSkip = mSkip;
    }

    public Integer getmLimit() {
        return mLimit;
    }

    public void setmLimit(Integer mLimit) {
        this.mLimit = mLimit;
    }
}
