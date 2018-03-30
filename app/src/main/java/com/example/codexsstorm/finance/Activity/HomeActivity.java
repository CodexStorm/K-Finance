package com.example.codexsstorm.finance.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.codexsstorm.finance.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    ArrayList<HashMap<String,Object>> items =new ArrayList<HashMap<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        final PackageManager pm = getPackageManager();
        List<PackageInfo> packs = pm.getInstalledPackages(0);
        for (PackageInfo pi : packs) {
            if( pi.packageName.toString().toLowerCase().contains("calcul")){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("appName", pi.applicationInfo.loadLabel(pm));
                map.put("packageName", pi.packageName);
                items.add(map);
            }
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.size()>=1){
                    String packageName = (String) items.get(0).get("packageName");
                    Intent i = pm.getLaunchIntentForPackage(packageName);
                    if (i != null)
                        startActivity(i);
                }
                else{
                    // Application not found
                }
            }
        });
    }
}
