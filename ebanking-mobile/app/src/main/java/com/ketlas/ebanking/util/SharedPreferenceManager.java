package com.ketlas.ebanking.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ketlas.ebanking.utils.MyApplication;

public class SharedPreferenceManager {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String SHARED_PREFERENCE_NAME = "test";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";

    public SharedPreferenceManager(){
        sharedPreferences = MyApplication.getAppContext().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void put(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public void putAccessToken(String value){
        editor.putString(SharedPreferenceManager.ACCESS_TOKEN,value);
        editor.commit();
    }
    public void putRefreshToken(String value){
        editor.putString(SharedPreferenceManager.ACCESS_TOKEN,value);
        editor.commit();
    }
    public String getAccessToken(){
        return sharedPreferences.getString(ACCESS_TOKEN,"");
    }
    public String getRefreshTokenToken(){
        return sharedPreferences.getString(REFRESH_TOKEN,"");
    }
    public boolean isLogin(){
        return !getAccessToken().equals("");
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }

    public void put(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }
    public void put(String key,int value){
        editor.putInt(key,value);
        editor.commit();
    }
    public String getString(String key){
        return sharedPreferences.getString(key,"");
    }
    public int getInt(String key){
        return sharedPreferences.getInt(key,0);
    }
    public void remove(String key){
        editor.remove(key);
    }
}
