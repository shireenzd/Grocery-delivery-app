
package com.example.groceryapp.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
       MaterialButton tvGoToSignIn = view.findViewById(R.id.tvGoToSignIn);

        btnCreateAccount.setOnClickListener(v -> {
            String name = getText(etFullName);
            String email = getText(etEmail);
            String pass = getText(etPassword);
            String confirm = getText(etConfirmPassword);

            if (TextUtils.isEmpty(name)) {
                etFullName.setError("Enter your name");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Enter your email");
                return;
            }
            if (pass.length() < 8) {
                etPassword.setError("Password must be at least 8 characters");
                return;
            }
            if (!pass.equals(confirm)) {
                etConfirmPassword.setError("Passwords do not match");
                return;
            }
            if (!cbTerms.isChecked()) {
                Toast.makeText(requireContext(), "Please agree to Terms & Privacy", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(requireContext(), "Register success (connect backend later)", Toast.LENGTH_SHORT).show();
        });

        tvGoToSignIn.setOnClickListener(v ->
            NavHostFragment.findNavController(RegisterFragment
                            .this)
                    .popBackStack());


    }

    private String getText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }
}
