package com.example.infrastructurecomplaints;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.app.NotificationCompat;

public class SharedPrefrencesConfig {
    private SharedPreferences sharedPreferences;
    private Context context;
    public SharedPrefrencesConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("com.example.infrastructurecomplaints_login",Context.MODE_PRIVATE);
    }
    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("com.example.infrastructurecomplaints_login_status",status);
        editor.apply();
    }
    public boolean readLoginStatus() {
        boolean status = false;
        status = sharedPreferences.getBoolean("com.example.infrastructurecomplaints_login_status",false);
        return status;
    }
    public void writeUserInfo(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email",email);
        editor.apply();
    }
    public String readUserInfo() {
        String email = "";
        email = sharedPreferences.getString("Email","");
        return email;
    }
}
