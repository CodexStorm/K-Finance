package com.example.codexsstorm.finance.Entity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class BillEntity {
    String token;
    int month;

    public String getToken() {
        return token;
    }

    public int getMonth() {
        return month;
    }

    public interface RestClientInterface{
        void onSuccess(JSONObject response, VolleyError error);
    }

    public JSONObject getParams(){
        JSONObject billEntityJson = null;
        Gson gson = new Gson();
        String loginString = gson.toJson(this);
        try {
            billEntityJson = new JSONObject(loginString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return billEntityJson;
    }
}
