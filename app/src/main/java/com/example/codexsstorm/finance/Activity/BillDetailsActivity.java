package com.example.codexsstorm.finance.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.codexsstorm.finance.R;

public class BillDetailsActivity extends AppCompatActivity {

    private TextView tvBillid;
    private TextView tvAmount;
    private TextView tvbilltype;
    private TextView tvReason;
    private TextView tvdob;
    private TextView tvUplaodDate;
    private TextView tvPaidFor;
    private TextView tvEditBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        Bundle extras = getIntent().getExtras();
        tvBillid = (TextView)findViewById(R.id.tvBillid);
        tvAmount = (TextView)findViewById(R.id.tvBillAmount);
        tvbilltype = (TextView)findViewById(R.id.tvBilltype);
        tvReason = (TextView)findViewById(R.id.Reason);
        tvdob = (TextView)findViewById(R.id.tvdob);
        tvUplaodDate = (TextView)findViewById(R.id.tvUplaodDate);
        tvPaidFor = (TextView)findViewById(R.id.tvPaidFor);
        tvEditBill = (TextView)findViewById(R.id.tvEditBill);
        tvBillid.setText(extras.getInt("id")+"");
        tvAmount.setText(extras.getInt("amount")+"");
        tvbilltype.setText(extras.getInt("type")+"");
        tvReason.setText(extras.getString("reason"));
        tvdob.setText(extras.getString("dob"));
        tvUplaodDate.setText(extras.getString("uploaddata"));
        tvPaidFor.setText(extras.getString("paidfor"));
        tvEditBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BillDetailsActivity.this,EditBillDetailsActivity.class);
                i.putExtra("type",tvbilltype.getText());
                i.putExtra("amount",tvAmount.getText());
                i.putExtra("id",tvBillid.getText());
                i.putExtra("reason",tvReason.getText());
                i.putExtra("dob",tvdob.getText());
                i.putExtra("uploaddate",tvUplaodDate.getText());
                i.putExtra("paidfor",tvPaidFor.getText());
                startActivity(i);
            }
        });


    }
}
