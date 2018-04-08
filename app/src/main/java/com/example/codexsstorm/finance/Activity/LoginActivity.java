package com.example.codexsstorm.finance.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.codexsstorm.finance.Common.UserDetails;
import com.example.codexsstorm.finance.Entity.LoginEntity;
import com.example.codexsstorm.finance.R;
import com.example.codexsstorm.finance.RESTclient.RESTClientImplementation;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail,etPassword;
    private Button bLogin;
    private RelativeLayout rlprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        rlprogress = findViewById(R.id.rlprogress);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                LoginEntity loginEntity = new LoginEntity(etEmail.getText().toString(),etPassword.getText().toString());
                RESTClientImplementation.normalLogin(loginEntity, new LoginEntity.RestClientInterface() {
                    @Override
                    public void onLogin(String token,int role ,int code,VolleyError error) {
                        hideProgress();
                        if(code == 200 && error == null) {
                            Toast.makeText(LoginActivity.this, "Token" + token, Toast.LENGTH_SHORT).show();
                            UserDetails.setUserLoggedIn(LoginActivity.this, true);
                            UserDetails.setUserToken(LoginActivity.this, token);
                            goToActivity(HomeActivity.class);
                        }else {
                            Toast.makeText(LoginActivity.this, "Something went wrong" + token, Toast.LENGTH_SHORT).show();
                        }
                    }

                },LoginActivity.this);
            }
        });


    }

    void showProgress(){
        rlprogress.setVisibility(View.VISIBLE);
    }

    void hideProgress(){
        rlprogress.setVisibility(View.GONE);
    }

    private void goToActivity(Class activity) {
        Intent intent;
        intent = new Intent(LoginActivity.this, activity);
        intent.putExtra("role",UserDetails.getUserRole(LoginActivity.this));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserDetails.isUserLoggedIn(LoginActivity.this)) {
            // Toast.makeText(LoginActivity.this,"Already Logged in",Toast.LENGTH_SHORT).show();
            goToActivity(HomeActivity.class);
        }
    }

    @Override
    public void onBackPressed() {

    }
}
