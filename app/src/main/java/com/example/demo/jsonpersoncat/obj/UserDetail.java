
package com.example.demo.jsonpersoncat.obj;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDetail {

    @SerializedName("updateDt")
    private String updateDate;

    @SerializedName("id")
    private int id;

    @SerializedName("myData")
    private List<MyData> catList;

    @SerializedName("userData")
    private UserData userData;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MyData> getCatList() {
        return catList;
    }

    public void setCatList(List<MyData> catList) {
        this.catList = catList;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

}
