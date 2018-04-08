package com.example.codexsstorm.finance.Entity;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class BillHeadEntity {
    BillCoverEntity month;
    int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public BillCoverEntity getMonth() {
        return month;
    }

    public void setMonth(BillCoverEntity month) {
        this.month = month;
    }
}
