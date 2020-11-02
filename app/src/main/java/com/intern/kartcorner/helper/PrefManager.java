package com.intern.kartcorner.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by Ravi on 08/07/15.
 */
public class PrefManager {
    // Shared Preferences
    private final SharedPreferences pref;

    // Editor for Shared preferences
    private final Editor editor;

    // Context
    private final Context _context;

    // Shared pref mode
    private final int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Cartcorner";
    private static final String KEY_MOBILE_NUMBER = "mobile_number";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_CITY = "city";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_USER_ID = "ID";
    private static final String KEY_PROFILEPIC = "profilepic";
    private static final String KEY_WALLET_AMOUNT = "wallet";
    private static final String KEY_DEVICE_TOKEN = "devicetoken";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";


    @SuppressLint("CommitPrefEdits")
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setCity(String cityName) {
        editor.putString(KEY_CITY, cityName);
        editor.commit();
        editor.apply();
    }

    public String getCity() {
        return pref.getString(KEY_CITY, null);
    }

    public void setWalletAmount(String amount) {
        editor.putString(KEY_WALLET_AMOUNT, amount);
        editor.commit();
        editor.apply();
    }

    public String getWalletAmount() {
        return pref.getString(KEY_WALLET_AMOUNT, null);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }



    public void setMobileNumber(String mobileNumber) {
        editor.putString(KEY_MOBILE_NUMBER, mobileNumber);
        editor.commit();
        editor.apply();
    }

    public String getMobileNumber() {
        return pref.getString(KEY_MOBILE_NUMBER, null);
    }

    public void setEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
        editor.apply();
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, null);
    }


    public void setprofilepic(String profilepic) {
        editor.putString(KEY_PROFILEPIC, profilepic);
        editor.commit();
        editor.apply();
    }

    public String getprofilepic() {
        return pref.getString(KEY_PROFILEPIC, null);
    }
    public void setDeviceToken(String deviceToken) {
        editor.putString(KEY_DEVICE_TOKEN, deviceToken);
        editor.commit();
        editor.apply();
    }

    public String getDeviceToken() {
        return pref.getString(KEY_DEVICE_TOKEN, null);
    }



    public void setUserId(String otp) {
        editor.putString(KEY_USER_ID, otp);
        editor.commit();
        editor.apply();
    }

    public String getUserId() {
        return pref.getString(KEY_USER_ID, null);
    }

    public void setName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
        editor.apply();
    }

    public String getName() {
        return pref.getString(KEY_NAME, null);
    }

    public void createLogin(String name,String mobile,String email,String secureid,String id) {
        editor.putString(KEY_MOBILE, mobile);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_USER_ID, id);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        setName(name);
        setEmail(email);
        setMobileNumber(mobile);
        setUserId(id);
        editor.commit();
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    public void clearLogin() {
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.commit();
        editor.apply();
    }
    public void setlogin() {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.commit();
        editor.apply();
    }


    public void clearSession() {
        try{
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.apply();

        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> profile = new HashMap<>();
        profile.put("name", pref.getString(KEY_NAME, null));
        profile.put("email", pref.getString(KEY_EMAIL, null));
        profile.put("mobile", pref.getString(KEY_MOBILE, null));
        return profile;
    }
}
