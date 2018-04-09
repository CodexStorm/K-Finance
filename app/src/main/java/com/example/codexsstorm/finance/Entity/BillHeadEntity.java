package com.example.codexsstorm.finance.Entity;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class BillHeadEntity {
    BillCoverEntity month = null;
    float balance;

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public BillCoverEntity getMonth() {
        return month;
    }

    public void setMonth(BillCoverEntity month) {
        this.month = month;
    }
}
