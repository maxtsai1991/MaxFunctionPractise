package com.example.demo.jsonpersoncat.data;


import com.google.gson.annotations.SerializedName;

/*
 Postman URL(用GET): https://gw.openapi.org.tw/5ce625594dae4e0f00e4aa89/parkinginfolist?client_id=5c53d140-7369-11ec-bcd3-2fe59eaf628f&client_secret=lVwHXHNmAl9aB14f8h%2FDHcqMXUZi2bBtvxL%2FatbLKFs%3D&UID=TNN&token=SURCOklEQjEyMzoxOTgyLTEwLTE5IDAwOjAwOjAw
 Query Params (查詢參數)
 */
public class ParkinginfoRequest {

    // Postman 的 Query Params 欄位 (API參數)
    @SerializedName("client_id") // 跟欄位一模一樣名稱
    private String mClient_id = "";

    @SerializedName("client_secret")
    private String mClient_secret = "";

    @SerializedName("UID")
    private String mUid = "";

    @SerializedName("token")
    private String mToken = "";

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

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }
}
