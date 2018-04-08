package com.example.codexsstorm.finance.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.codexsstorm.finance.Adapter.BillListAdapter;
import com.example.codexsstorm.finance.Entity.BillCoverEntity;
import com.example.codexsstorm.finance.Entity.BillDetailsEntity;
import com.example.codexsstorm.finance.Entity.BillEntity;
import com.example.codexsstorm.finance.Entity.BillHeadEntity;
import com.example.codexsstorm.finance.R;
import com.example.codexsstorm.finance.RESTclient.RESTClientImplementation;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private BillListAdapter adapter;
    BillHeadEntity billHeadEntity;
    BillCoverEntity billCoverEntity;
    List<BillDetailsEntity> billDetailsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        callapi();
        fab = (FloatingActionButton)findViewById(R.id.fab);
        recyclerView = (RecyclerView)findViewById(R.id.rvMyBills);
        LinearLayoutManager manager=new LinearLayoutManager(HomeActivity.this);
        recyclerView.setLayoutManager(manager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,ReimbursementActivity.class);

                startActivity(i);
            }
        });

    }

    private void callapi() {
        BillEntity b = new BillEntity();
        RESTClientImplementation.billsApi(b, new BillEntity.RestClientInterface() {

            @Override
            public void onSuccess(JSONObject response, VolleyError error) {
                Gson gson = new Gson();
                billHeadEntity = gson.fromJson(response.toString(),BillHeadEntity.class);
                billCoverEntity = billHeadEntity.getMonth();
                billDetailsEntity = billCoverEntity.getOutflows();
                Log.e("Wassup",billHeadEntity+"");
                Log.e("Wassup",billCoverEntity+"");
                Log.e("Wassup",billDetailsEntity+"");
                Log.e("Wassup",billDetailsEntity.get(0).getBill_url());
                adapter = new BillListAdapter(billDetailsEntity, HomeActivity.this, new BillListAdapter.CallBack() {
                    @Override
                    public void CallBillDetailsActivity(BillDetailsEntity billDetailsEntity) {
                            Intent i = new Intent(HomeActivity.this,BillDetailsActivity.class);
                            i.putExtra("type",billDetailsEntity.getType());
                        i.putExtra("amount",billDetailsEntity.getAmount());
                        i.putExtra("id",billDetailsEntity.getId());
                        i.putExtra("reason",billDetailsEntity.getReason());
                        i.putExtra("dob",billDetailsEntity.getDob());
                        i.putExtra("uploaddate",billDetailsEntity.getUpload_date());
                        i.putExtra("paidfor",billDetailsEntity.getPaid_for());
                        startActivity(i);
                    }
                });
                recyclerView.hasFixedSize();
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);


            }
        },HomeActivity.this);
    }
}
