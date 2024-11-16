package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }
}