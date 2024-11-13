package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}