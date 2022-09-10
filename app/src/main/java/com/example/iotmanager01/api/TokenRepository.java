package com.example.iotmanager01.api;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenRepository {
    private static volatile TokenRepository instance;

    SharedPreferences sharedPref;


    private TokenRepository(Context context) {
        this.sharedPref = context.getSharedPreferences("token_repository", Context.MODE_PRIVATE);
    }

    public static TokenRepository getInstance(Context context) {

        TokenRepository result = instance;
        if (result != null) {
            return result;
        }
        synchronized (TokenRepository.class) {
            if (instance == null) {
                instance = new TokenRepository(context);
            }
            return instance;
        }
    }

    public void setToken(String token) {

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("KEY", "Bearer " + token);
        editor.apply();

    }

    public String getToken(){
        return sharedPref.getString("KEY","");
    }


    public void clear(){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }
}
