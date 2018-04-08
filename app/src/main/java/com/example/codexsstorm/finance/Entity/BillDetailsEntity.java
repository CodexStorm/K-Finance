package com.example.codexsstorm.finance.Entity;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class BillDetailsEntity {
    int type;
    int id;
    String dob;
    String upload_date;
    String reason;
    String bill_url;
    String paid_for;
    int amount;

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


}
