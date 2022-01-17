package com.example.demo.jsonpersoncat.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Constant {
    //region API URL Definition
//    public static final String PROD_BASE_URL = "http://smis.thsrc.com.tw/";
//    public static final String PROD_LAN_URL = "http://10.0.64.117/";
//    public static final String PROD_TCMS_URL = "http://tcms.thsrc.com.tw:8081/";
//
//    public static final String UAT_BASE_URL = "http://175.98.149.89/";
//    public static final String UAT_LAN_URL = "http://10.0.64.118/";
//    public static final String UAT_TCMS_URL = "http://10.0.30.105:8081/";
//
//    public static final String DEV_BASE_URL = "http://10.0.31.5:8080/";
//    public static final String DEV_LAN_URL = "http://10.0.31.5:8080/";
//    public static final String DEV_TCMS_URL = "http://10.0.31.149:8081/";
    //endregion

//    public static final String SMIS_APP_ID = "SMIS";
//    public static final String SMIS_S_KEY = "12888";
//
//    public static final String API_ENCODE = "utf8";

    /**
     * 今天日期
     * pattern = "yyyy-MM-dd HH:mm:ss"
     */
    public static final String DATE_TODAY = new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN).format(Calendar.getInstance().getTime().getTime());

    public static final String DATE_TODAY_DATE = new SimpleDateFormat("yyyyMMdd", Locale.TAIWAN).format(Calendar.getInstance().getTime().getTime());


    /**
     * 回傳預設 pattern = yyyy-MM-dd HH:mm:ss 的日期
     *
     * @return 日期
     */
    public static String getCurrentDate() {
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 回傳指定 pattern 格式日期
     *
     * @param pattern 指定的日期格式
     * @return 日期
     */
    public static String getCurrentDate(String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.TAIWAN).format(System.currentTimeMillis());
        } catch (Exception e) {
            return "";
        }
    }

    public static final String ACTION_TOKEN_INVALID = "INTENT_ACTION_TOKEN_INVALID";
}
