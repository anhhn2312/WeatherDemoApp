package com.anhhn.weatherdemoapp.utils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {
    private static Gson sGson;

    public static synchronized Gson getGson() {
        if (sGson == null) {
            sGson = new Gson();
        }
        return sGson;
    }

    public static synchronized boolean isJson(String data) {
        if (data == null || "".equals(data)) return false;
        try {
            new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                new JSONArray(data);
            } catch (JSONException e1) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
