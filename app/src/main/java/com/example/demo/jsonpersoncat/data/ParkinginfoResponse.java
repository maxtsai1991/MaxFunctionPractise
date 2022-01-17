package com.example.demo.jsonpersoncat.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/*
    建立資料Model : Retrofit會自動將連線取得的資料轉成物件便於做後續處理
    注意 : 欄位名稱要跟API裡的一致
    API URL (用GET) : https://gw.openapi.org.tw/5ce625594dae4e0f00e4aa89/parkinginfolist?client_id=5c53d140-7369-11ec-bcd3-2fe59eaf628f&client_secret=lVwHXHNmAl9aB14f8h%2FDHcqMXUZi2bBtvxL%2FatbLKFs%3D&UID=TNN&token=SURCOklEQjEyMzoxOTgyLTEwLTE5IDAwOjAwOjAw
    Postman 下方Response
    停車場基本資料 API 解說文件 : https://api.openapi.org.tw/apiImages/664ae730-730f-11ea-8ad6-db953ab970a1.pdf
    停車場基本資料 API Response 有很多欄位 先取幾個欄位使用: "AREA" "AREA_NAME" "SECTION_ID" "SECTION" "SECTION_LAT" "SECTION_LNG"
     */
public class ParkinginfoResponse {

    @SerializedName("data")
    private List<ParkingInfoData> mParkingInfoData = new ArrayList<>();

    public class ParkingInfoData {

        @SerializedName("AREA")      // 中譯: 停車區域代碼，ex：TNN=台南
        private String mArea = "";

        @SerializedName("AREA_NAME") // 中譯: 停車區域名稱，ex：GR=歸仁區
        private int mAreaName;

        public String getmArea() {
            return mArea;
        }

        public void setmArea(String mArea) {
            this.mArea = mArea;
        }

        public int getmAreaName() {
            return mAreaName;
        }

        public void setmAreaName(int mAreaName) {
            this.mAreaName = mAreaName;
        }

        @SerializedName("SectionInfo")
        List<SectionInfo> mSectionInfoList = new ArrayList<>();

        public class SectionInfo {

            @SerializedName("SECTION_ID")      // 中譯: 路段代碼，ex：GR01
            private String mSectionId = "";

            @SerializedName("SECTION") // 中譯: 路段名稱，ex：寶慶路
            private int mSection;

            @SerializedName("SECTION_LAT") // 中譯: 緯度
            private int mSectionLat;

            @SerializedName("SECTION_LNG") // 中譯: 經度
            private int mSectionLng;

            public String getmSectionId() {
                return mSectionId;
            }

            public void setmSectionId(String mSectionId) {
                this.mSectionId = mSectionId;
            }

            public int getmSection() {
                return mSection;
            }

            public void setmSection(int mSection) {
                this.mSection = mSection;
            }

            public int getmSectionLat() {
                return mSectionLat;
            }

            public void setmSectionLat(int mSectionLat) {
                this.mSectionLat = mSectionLat;
            }

            public int getmSectionLng() {
                return mSectionLng;
            }

            public void setmSectionLng(int mSectionLng) {
                this.mSectionLng = mSectionLng;
            }
        }

    }

    public List<ParkingInfoData> getmParkingInfoData() {
        return mParkingInfoData;
    }

    public void setmParkingInfoData(List<ParkingInfoData> mParkingInfoData) {
        this.mParkingInfoData = mParkingInfoData;
    }
}
