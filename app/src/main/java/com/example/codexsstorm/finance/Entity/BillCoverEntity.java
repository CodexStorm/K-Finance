package com.example.codexsstorm.finance.Entity;

import java.util.List;

/**
 * Created by codexsstorm on 9/4/18.
 */

public class BillCoverEntity {
    int earned ;
    int spent ;
    List<BillDetailsEntity> outflows;

    public List<BillDetailsEntity> getOutflows() {
        return outflows;
    }

    public void setOutflows(List<BillDetailsEntity> outflows) {
        this.outflows = outflows;
    }



    public void setEarned(int earned) {
        this.earned = earned;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }



    public int getEarned() {
        return earned;
    }

    public int getSpent() {
        return spent;
    }

}
