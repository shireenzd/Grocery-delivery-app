package com.example.groceryapp.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

 public class RegisterFragment extends Fragment {

    private TextInputEditText etFullName, etEmail, etPassword, etConfirmPassword;
    private MaterialCheckBox cbTerms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etFullName = view.findViewById(R.id.etFullName);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);

        cbTerms = view.findViewById(R.id.cbTerms);
        MaterialButton btnCreateAccount = view.findViewById(R.id.btnCreateAccount);
        TextView tvGoToSignIn = view.findViewById(R.id.tvGoToSignIn);

        btnCreateAccount.setOnClickListener(v -> {
            String name = getText(etFullName);
            String email = getText(etEmail);
            String password = getText(etPassword);
            String confirm = getText(etConfirmPassword);

            if (TextUtils.isEmpty(name)) {
                etFullName.setError("Enter your name");
                etFullName.requestFocus();
                return;
            }

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

            if (!password.equals(confirm)) {
                etConfirmPassword.setError("Passwords do not match");
                etConfirmPassword.requestFocus();
                return;
            }

            if (!cbTerms.isChecked()) {
                Toast.makeText(requireContext(),
                        "Please agree to Terms & Privacy",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            UserLocalStore store = new UserLocalStore(requireContext());
            store.saveUser(name, email, password);

            Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show();


            NavHostFragment.findNavController(RegisterFragment.this)
                    .navigate(R.id.action_RegisterFragment_to_HomeFragment);
        });

        tvGoToSignIn.setOnClickListener(v ->
                NavHostFragment.findNavController(RegisterFragment.this).navigateUp()
        );
    }

    private String getText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }
}
