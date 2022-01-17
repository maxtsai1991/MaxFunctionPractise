
package com.example.demo.jsonpersoncat.obj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyData {

    @SerializedName("dataId")
    private String dataId;

    @SerializedName("dataName")
    private String dataName;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

}
