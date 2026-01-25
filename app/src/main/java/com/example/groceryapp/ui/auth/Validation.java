
package com.example.groceryapp.ui.auth;

import android.util.Patterns;

public class Validation {

    public static boolean isValidEmail(String email) {
        return email == null
                || email.trim().isEmpty()
                || !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }

    public static boolean isValidPassword(String password) {
        return password == null
                || password.trim().length() < 6;
    }
}

