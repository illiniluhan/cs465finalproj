package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_analysis) {
            } else if (checkedId == R.id.navigation_import) {
                Toast.makeText(AnalysisActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnalysisActivity.this, MainActivity.class));
            } else if (checkedId == R.id.navigation_read) {
                Toast.makeText(AnalysisActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnalysisActivity.this, ReadActivity.class));
            }
        });
        ((RadioButton) findViewById(R.id.navigation_analysis)).setChecked(true);
    }
}