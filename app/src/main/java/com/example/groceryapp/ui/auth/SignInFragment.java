package com.example.groceryapp.ui.auth;
 import com.example.groceryapp.ui.auth.Validation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.groceryapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


 public class SignInFragment extends Fragment {

    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnSignIn;
    private TextView tvGoToRegister, tvForgotPassword;

    public SignInFragment() { }

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


            if (!Validation.isValidEmail(email)) {
                etEmail.setError("Enter a valid email");
                etEmail.requestFocus();
                return;
            }

            if (!Validation.isValidPassword(password)) {
                etPassword.setError("Password must be at least 6 characters");
                etPassword.requestFocus();
                return;
            }

            // 2) Check if user registered locally
            UserLocalStore store = new UserLocalStore(requireContext());

            if (!store.isRegistered()) {
                Toast.makeText(requireContext(),
                        "You must register first",
                        Toast.LENGTH_SHORT).show();

                NavHostFragment.findNavController(SignInFragment.this)
                        .navigate(R.id.action_signInFragment_to_registerFragment);
                return;
            }

            // Check if user registered at all
            if (!store.isRegistered()) {
                Toast.makeText(requireContext(),
                        "You must register first",
                        Toast.LENGTH_SHORT).show();

                NavHostFragment.findNavController(SignInFragment.this)
                        .navigate(R.id.action_signInFragment_to_registerFragment);
                return;
            }


            if (!store.isEmailRegistered(email)) {
                etEmail.setError("Email not registered");
                etEmail.requestFocus();
                return;
            }


            if (!store.isPasswordCorrect(password)) {
                etPassword.setError("Wrong password");
                etPassword.requestFocus();
                return;
            }



            NavHostFragment.findNavController(SignInFragment.this)
                    .navigate(R.id.action_SignInFragment_to_HomeFragment);
        });


        tvGoToRegister.setOnClickListener(v ->
                NavHostFragment.findNavController(SignInFragment.this)
                        .navigate(R.id.action_signInFragment_to_registerFragment)
        );


        tvForgotPassword.setOnClickListener(v ->
                Toast.makeText(requireContext(),
                        "Forgot password coming soon",
                        Toast.LENGTH_SHORT).show()
        );
    }

    private String getText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }
}
