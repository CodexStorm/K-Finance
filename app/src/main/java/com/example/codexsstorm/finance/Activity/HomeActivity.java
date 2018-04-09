package com.example.codexsstorm.finance.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.codexsstorm.finance.Entity.TicketEntity;
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
    private TextView tvRaise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        callapi();
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        tvRaise = (TextView)findViewById(R.id.ticket);
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

        tvRaise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RESTClientImplementation.RaiseTickets(new TicketEntity.RestClientInterface() {
                    @Override
                    public void onSucce(int response, VolleyError error) {
                        if(response == 200){
                            Toast.makeText(HomeActivity.this,"Tickets Raised",Toast.LENGTH_SHORT).show();
                        }
                        else if(response == 400)
                            Toast.makeText(HomeActivity.this,"Tickets Already Raised",Toast.LENGTH_SHORT).show();
                    }
                },HomeActivity.this);
            }
        });

    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void callapi() {
        BillEntity b = new BillEntity();
        RESTClientImplementation.billsApi(b, new BillEntity.RestClientInterface() {

            @Override
            public void onSuccess(JSONObject response, VolleyError error) {
                Gson gson = new Gson();
                billHeadEntity = gson.fromJson(response.toString(), BillHeadEntity.class);
                billCoverEntity = billHeadEntity.getMonth();
                if(billCoverEntity!=null) {
                    billDetailsEntity = billCoverEntity.getOutflows();
                    Log.e("Wassup", billHeadEntity + "");
                    Log.e("Wassup", billCoverEntity + "");
                    Log.e("Wassup", billDetailsEntity + "");

                    adapter = new BillListAdapter(billDetailsEntity, HomeActivity.this, new BillListAdapter.CallBack() {
                        @Override
                        public void CallBillDetailsActivity(BillDetailsEntity billDetailsEntity) {
                            Intent i = new Intent(HomeActivity.this, BillDetailsActivity.class);
                            i.putExtra("type", billDetailsEntity.getType());
                            i.putExtra("amount", billDetailsEntity.getAmount());
                            i.putExtra("id", billDetailsEntity.getId());
                            i.putExtra("reason", billDetailsEntity.getReason());
                            i.putExtra("dob", billDetailsEntity.getDob());
                            i.putExtra("uploaddate", billDetailsEntity.getUpload_date());
                            i.putExtra("paidfor", billDetailsEntity.getPaid_for());
                            startActivity(i);
                        }
                    });
                    recyclerView.hasFixedSize();
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else
                    Toast.makeText(HomeActivity.this,"No bills for this month",Toast.LENGTH_SHORT).show();



            }
        },HomeActivity.this);
    }
}
