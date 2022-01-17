package com.example.demo.jsonpersoncat.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by YunKai-Ke(柯紜凱) on 2017/3/28.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class JsonUtil {
    /**
     * 將Json Object轉為 HashMap集合
     */
    public static HashMap<String, String> getMap(String jsonString) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            String value;
            HashMap<String, String> valueMap = new HashMap<>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key).toString();
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 將Json Array轉為 HashMap集合
     */
    public static ArrayList<HashMap<String, String>> getList(String jsonString) {
        ArrayList<HashMap<String, String>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 將Json Array轉為 jsonString List
     */
    public static ArrayList<String> jsonStringToArray(String jsonString) {
        ArrayList<String> stringArray = null;
        try {
            stringArray = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                stringArray.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringArray;
    }

    /**
     * 由Asset取出Json
     * input asset內json檔名 ex: oceanIdName.json
     * @return json string
     */
    public static String loadJSONFile(Context activity, String file) {
        String json;

        try {
            InputStream is = activity.getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            //noinspection ResultOfMethodCallIgnored
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
