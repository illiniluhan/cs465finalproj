package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private Button loginButton;
    private TextView errorMessage;
    private TextView redirectRegister;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        errorMessage = findViewById(R.id.errorMessage);
        redirectRegister = findViewById(R.id.redirectRegister);
        loginButton.setOnClickListener(v -> {
            errorMessage.setVisibility(TextView.GONE);
            loginUser();
        });
        redirectRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });


    }

    private void loginUser() {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        username = username + "@465.com"; // Convert username to email

        if (username.isEmpty() || password.isEmpty()) {
            displayErrorMessage("Please enter both username and password.");
            return;
        }

        auth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        displayErrorMessage("Login Failed: " + e.getMessage());
                    }
                });
    }


    private void displayErrorMessage(String message) {
        errorMessage.setText(message);
        errorMessage.setVisibility(TextView.VISIBLE);
    }


}
