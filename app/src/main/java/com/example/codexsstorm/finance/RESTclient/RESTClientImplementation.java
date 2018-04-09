package com.example.codexsstorm.finance.RESTclient;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.codexsstorm.finance.Common.UserDetails;
import com.example.codexsstorm.finance.Constants.Constants;
import com.example.codexsstorm.finance.Entity.BillCoverEntity;
import com.example.codexsstorm.finance.Entity.BillDetailsEntity;
import com.example.codexsstorm.finance.Entity.BillEntity;
import com.example.codexsstorm.finance.Entity.BillUpdateEntity;
import com.example.codexsstorm.finance.Entity.LoginEntity;
import com.example.codexsstorm.finance.Entity.ResponseEntity;
import com.example.codexsstorm.finance.Entity.TicketEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RESTClientImplementation {

    static RequestQueue queue;
    private static final String BASE_URL = Constants.BASE_URL;
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void normalLogin(final LoginEntity loginEntity, final LoginEntity.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/login");
        JSONObject postParams = new JSONObject();
        try {
            postParams.put("username",loginEntity.getEmail());
            postParams.put("password",loginEntity.getPassword());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Login Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                try {
                    int statusCode = response.getJSONObject("code").getInt("statusCode");
                    Log.d("Status Code : ",statusCode+"");
                    String token = response.getJSONObject("data").getString("token");
                    int role = response.getJSONObject("data").getInt("role");
                    if(statusCode == 200){
                        restClientInterface.onLogin(token,role,200,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("normal login","error");

                restClientInterface.onLogin("",-1,error.networkResponse.statusCode,new VolleyError());
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }

    public static void billsApi(final BillEntity billEntity, final BillEntity.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/outflows");
        JSONObject postParams = new JSONObject();
        try {
            postParams.put("token", UserDetails.getUserToken(context));
            postParams.put("month",3);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("sup",postParams.toString());
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Bill Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                try {
                    JSONObject x = response.getJSONObject("data");
                    int statusCode = response.getJSONObject("code").getInt("statusCode");
                    if(statusCode == 200){
                        restClientInterface.onSuccess(x,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("normal login","error");

               // restClientInterface.onSuccess(x,new VolleyError());
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }
    public static void UpdateBill(final BillUpdateEntity billEntity, final BillUpdateEntity.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/outflows");
        JSONObject postParams = new JSONObject();
        try {
            postParams.put("token", UserDetails.getUserToken(context));
            postParams.put("month",3);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("sup",postParams.toString());
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                try {
                    int statusCode = response.getJSONObject("code").getInt("statusCode");
                    if(statusCode == 200){
                        restClientInterface.onSucce(statusCode,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("normal login","error");

                restClientInterface.onSucce(error.networkResponse.statusCode,new VolleyError());
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }

    public static void RaiseTickets(final TicketEntity.RestClientInterface restClientInterface, final Context context){
        queue = VolleySingleton.getInstance(context).getRequestQueue();
        String url = getAbsoluteUrl("/raise-ticket");
        JSONObject postParams = new JSONObject();
        try {
            postParams.put("token", UserDetails.getUserToken(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("sup",postParams.toString());
        JsonBaseRequest jsonBaseRequest = new JsonBaseRequest(Request.Method.POST, url, postParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response",response.toString());
                Gson gson = new Gson();
                ResponseEntity responseEntity = gson.fromJson(response.toString(),ResponseEntity.class);
                try {
                    int statusCode = response.getJSONObject("code").getInt("statusCode");
                    if(statusCode == 200){
                        restClientInterface.onSucce(statusCode,null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("normal login","error");

                restClientInterface.onSucce(error.networkResponse.statusCode,new VolleyError());
            }
        },30000,0);
        queue.add(jsonBaseRequest);
    }
}
