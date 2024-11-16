package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnalysisActivity extends AppCompatActivity {
    private TextView read_progress;
    private TextView read_analysis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        read_progress = findViewById(R.id.reading_prog);
        read_analysis = findViewById(R.id.read_analy);

        read_progress.setOnClickListener(v -> {
            Intent intent = new Intent(AnalysisActivity.this, MainActivity.class);
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

        read_analysis.setOnClickListener(v -> {
            Intent intent = new Intent(AnalysisActivity.this, ReadActivity.class);
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });

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