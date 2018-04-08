package com.example.codexsstorm.finance.Common;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.codexsstorm.finance.Constants.Constants;

public class UserDetails {
    public static void setUserLoggedIn(Context context, Boolean loginStatus){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("userLoggedIn",loginStatus);
        editor.apply();
    }

    public static Boolean isUserLoggedIn(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        return (settings.getBoolean("userLoggedIn", false));
    }

    public static void setUserToken(Context context,String token){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token",token);
        editor.apply();
    }

    public static String getUserToken(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        return  (settings.getString("token",""));
    }

    public static void setUserRole(Context context,int role){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("role",role);
        editor.apply();
    }
    public static String getUserRole(Context context){
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFERENCE,0);
        return  (settings.getString("role",""));
    }


}
