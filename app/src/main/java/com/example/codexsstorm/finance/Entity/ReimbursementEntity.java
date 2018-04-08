package com.example.codexsstorm.finance.Entity;

import android.content.Context;

/**
 * Created by codexsstorm on 8/4/18.
 */

public class ReimbursementEntity {
    private String Filepath;
    private String DOB;
    private String PaidFor;
    private String Reason;
    private String TotalAmount;
    private int type;
    private Context context;

    public ReimbursementEntity(String filepath, String DOB, String paidFor, String reason, String totalAmount, int type, Context context) {
        Filepath = filepath;
        this.DOB = DOB;
        PaidFor = paidFor;
        Reason = reason;
        TotalAmount = totalAmount;
        this.type = type;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public int getType() {
        return type;
    }

    public String getFilepath() {
        return Filepath;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPaidFor() {
        return PaidFor;
    }

    public String getReason() {
        return Reason;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }
}
