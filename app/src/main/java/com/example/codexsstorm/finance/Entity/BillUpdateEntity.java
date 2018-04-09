package com.example.codexsstorm.finance.Entity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class BillUpdateEntity {
    int type;
    int id;
    String dob;
    String upload_date;
    String reason;
    String bill_url;
    String paid_for;
    int amount;

    public BillUpdateEntity(int type, int id, String dob, String upload_date, String reason, String bill_url, String paid_for, int amount) {
        this.type = type;
        this.id = id;
        this.dob = dob;
        this.upload_date = upload_date;
        this.reason = reason;
        this.bill_url = bill_url;
        this.paid_for = paid_for;
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setBill_url(String bill_url) {
        this.bill_url = bill_url;
    }

    public void setPaid_for(String paid_for) {
        this.paid_for = paid_for;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getDob() {
        return dob;
    }

    public String getReason() {
        return reason;
    }

    public String getBill_url() {
        return bill_url;
    }

    public String getPaid_for() {
        return paid_for;
    }

    public int getAmount() {
        return amount;
    }

    public interface RestClientInterface{
        void onSucce(int response, VolleyError error);
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
