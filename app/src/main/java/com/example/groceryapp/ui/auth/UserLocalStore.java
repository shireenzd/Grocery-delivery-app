package com.example.groceryapp.ui.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    private static final String PREF_NAME = "user_local_store";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private final SharedPreferences prefs;

    public UserLocalStore(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(String name, String email, String password) {
        prefs.edit()
                .putString(KEY_NAME, name)
                .putString(KEY_EMAIL, email)
                .putString(KEY_PASSWORD, password)
                .apply();
    }

    public boolean isRegistered() {
        String email = prefs.getString(KEY_EMAIL, null);
        String pass = prefs.getString(KEY_PASSWORD, null);
        return email != null && pass != null;
    }

    public boolean checkLogin(String email, String password) {
        String savedEmail = prefs.getString(KEY_EMAIL, null);
        String savedPass = prefs.getString(KEY_PASSWORD, null);

        return savedEmail != null && savedPass != null
                && savedEmail.equals(email)
                && savedPass.equals(password);
    }
    public boolean isEmailRegistered(String email) {
        String savedEmail = prefs.getString(KEY_EMAIL, null);
        return savedEmail != null && savedEmail.equals(email);
    }

    public boolean isPasswordCorrect(String password) {
        String savedPass = prefs.getString(KEY_PASSWORD, null);
        return savedPass != null && savedPass.equals(password);
    }

    public void clearUser() {
        prefs.edit().clear().apply();
    }
}
