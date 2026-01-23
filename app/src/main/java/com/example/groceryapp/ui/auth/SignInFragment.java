package com.example.groceryapp.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.fragment.NavHostFragment;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.groceryapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignInFragment extends Fragment {

    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnSignIn;
    private TextView tvGoToRegister, tvForgotPassword;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        tvGoToRegister = view.findViewById(R.id.tvGoToRegister);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);

      
        btnSignIn.setOnClickListener(v -> {
            String email = getText(etEmail);
            String password = getText(etPassword);

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Enter your email");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Enter your password");
                return;
            }

            // TODO: connect backend / Firebase later
            Toast.makeText(requireContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
        });

      
        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Forgot password (later)", Toast.LENGTH_SHORT).show()
        );

      
        tvGoToRegister.setOnClickListener(v -> {
            NavHostFragment.findNavController(SignInFragment.this)
                    .navigate(R.id.action_signInFragment_to_registerFragment);
        });

    }


    private String getText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }
}
