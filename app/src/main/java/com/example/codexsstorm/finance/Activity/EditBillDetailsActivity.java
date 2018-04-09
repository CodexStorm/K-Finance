package com.example.codexsstorm.finance.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.codexsstorm.finance.Entity.BillUpdateEntity;
import com.example.codexsstorm.finance.R;
import com.example.codexsstorm.finance.RESTclient.RESTClientImplementation;

public class EditBillDetailsActivity extends AppCompatActivity {

    private TextView tvBillid;
    private TextView tvAmount;
    private TextView tvbilltype;
    private EditText tvReason;
    private EditText tvdob;
    private EditText tvUplaodDate;
    private EditText tvPaidFor;
    private TextView tvProceed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bill_details);
        Bundle extras = getIntent().getExtras();
        tvBillid = (TextView)findViewById(R.id.tvBillid);
        tvAmount = (TextView)findViewById(R.id.tvBillAmount);
        tvbilltype = (TextView)findViewById(R.id.tvBilltype);
        tvReason = (EditText) findViewById(R.id.Reason);
        tvdob = (EditText) findViewById(R.id.tvdob);
        tvUplaodDate = (EditText) findViewById(R.id.tvUplaodDate);
        tvPaidFor = (EditText) findViewById(R.id.tvPaidFor);
        tvProceed = (TextView)findViewById(R.id.tvProceed);
        tvBillid.setText(extras.getString("id"));
        tvAmount.setText(extras.getString("amount"));
        tvbilltype.setText(extras.getString("type"));
        tvReason.setText(extras.getString("reason"));
        tvdob.setText(extras.getString("dob"));
        tvUplaodDate.setText(extras.getString("uploaddata"));
        tvPaidFor.setText(extras.getString("paidfor"));

        tvProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                BillUpdateEntity billUpdateEntity = new BillUpdateEntity(Integer.parseInt(tvbilltype.getText().toString()),Integer.parseInt(tvBillid.getText().toString()),tvdob.getText().toString(),tvUplaodDate.getText().toString(),tvReason.getText().toString(),"",tvPaidFor.getText().toString(),Integer.parseInt(tvAmount.getText().toString()));
                RESTClientImplementation.UpdateBill(billUpdateEntity, new BillUpdateEntity.RestClientInterface() {
                    @Override
                    public void onSucce(int response, VolleyError error) {

                    }
                },EditBillDetailsActivity.this);
            }
        });
    }
}
