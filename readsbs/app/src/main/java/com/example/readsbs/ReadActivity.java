package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_read) {
            } else if (checkedId == R.id.navigation_import) {
                Toast.makeText(ReadActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ReadActivity.this, MainActivity.class));
            } else if (checkedId == R.id.navigation_analysis) {
                Toast.makeText(ReadActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ReadActivity.this, AnalysisActivity.class));
            }
        });
        ((RadioButton) findViewById(R.id.navigation_read)).setChecked(true);
    }
}