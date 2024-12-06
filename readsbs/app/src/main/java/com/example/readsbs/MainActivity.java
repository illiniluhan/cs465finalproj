package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private EditText path;
    private Button submit_btn;
    private FirebaseAuth auth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String uid;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser fbuser = FirebaseAuth.getInstance().getCurrentUser();
        uid = fbuser.getUid();

        // Main page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fade animation with logo fix
        ImageView logo = findViewById(R.id.logo);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_out);
        logo.startAnimation(fadeIn);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        if (username != null && !username.isEmpty()) {
            Toast.makeText(MainActivity.this, username + " Logged in", Toast.LENGTH_SHORT).show();
        }

        submit_btn = findViewById(R.id.submit_path);
        path = findViewById(R.id.import_path);
        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_import) {
            } else if (checkedId == R.id.navigation_read) {
                Toast.makeText(MainActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ReadActivity.class));
            } else if (checkedId == R.id.navigation_analysis) {
                Toast.makeText(MainActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, AnalysisActivity.class));
            }
        });
        ((RadioButton) findViewById(R.id.navigation_import)).setChecked(true);

        submit_btn.setOnClickListener(v -> {
            submit_to_parse();
        });
    }

    private void submit_to_parse() {
        String parse_path = path.getText().toString().trim();

        if (parse_path.isEmpty()) {
            // Fail
            Toast.makeText(MainActivity.this, "Please enter a valid path.", Toast.LENGTH_SHORT).show();
        } else {
            // Success
            Toast.makeText(MainActivity.this, "Submit Successful!", Toast.LENGTH_SHORT).show();
        }
    }

}