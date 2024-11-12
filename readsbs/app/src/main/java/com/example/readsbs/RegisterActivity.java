package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterActivity extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private Button signupButton;
    private TextView errorMessage;
    private TextView redirectLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        errorMessage = findViewById(R.id.errorMessage);
        signupButton = findViewById(R.id.signupButton);
        redirectLogin = findViewById(R.id.redirectLogin);


        signupButton.setOnClickListener(v -> {
            errorMessage.setVisibility(TextView.GONE);
            signUpUser();
        });

        redirectLogin.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

    }

    private void signUpUser() {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        username = username+"@465.com";
        if (username.isEmpty() || password.isEmpty()) {
            displayErrorMessage("Please enter username and password");
            return;
        }else {
            auth.createUserWithEmailAndPassword(username, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                displayErrorMessage("Signup Failed: " + task.getException().getMessage());
                            }
                        }
                    });
        }

    }

    private void displayErrorMessage(String message) {
        errorMessage.setText(message);
        errorMessage.setVisibility(TextView.VISIBLE);
    }


}
