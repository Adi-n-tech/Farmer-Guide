package com.adintech.farmersguide.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;

import java.util.HashMap;

public class SharesPreferences {
    SharedPreferences userSession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String PASSWARD = "passward";
    public static final String RE_PASSWARD = "repassward";

    public SharesPreferences(Context context) {
        userSession = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = userSession.edit();
    }

    public void createLoginSession(String name, String phone, String address, String passward, String repassward) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(PHONE, phone);
        editor.putString(ADDRESS, address);
        editor.putString(PASSWARD, passward);
        editor.putString(RE_PASSWARD, repassward);

        editor.commit();
    }

    public HashMap<String, String> getUserDetailFromSession() {
        HashMap<String, String> userData = new HashMap<String, String>();
        userData.put(NAME, userSession.getString(NAME, null));
        userData.put(PHONE, userSession.getString(PHONE, null));
        userData.put(ADDRESS, userSession.getString(ADDRESS, null));
        userData.put(PASSWARD, userSession.getString(PASSWARD, null));
        userData.put(RE_PASSWARD, userSession.getString(RE_PASSWARD, null));
        return userData;
    }

    public boolean checkLogin() {
        if (userSession.getBoolean(IS_LOGIN, true)) {
            return true;
        } else return false;
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
