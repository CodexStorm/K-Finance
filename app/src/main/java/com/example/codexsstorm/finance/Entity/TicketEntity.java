package com.example.codexsstorm.finance.Entity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class TicketEntity {
    public interface RestClientInterface{
        void onSucce(int response, VolleyError error);
    }
}
