package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
            Intent intent = new Intent(AnalysisActivity.this, AnalysisProgress.class);
            startActivity(intent);
        });

        read_analysis.setOnClickListener(v -> {
            Intent intent = new Intent(AnalysisActivity.this, AnalysisAnalysis.class);
            startActivity(intent);
        });

        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_analysis) {
            } else if (checkedId == R.id.navigation_import) {
                View selectedButton = findViewById(R.id.navigation_import);
                performClickAnimation(selectedButton);
            } else if (checkedId == R.id.navigation_read) {
                View selectedButton = findViewById(R.id.navigation_read);
                performClickAnimation2(selectedButton);
            }
        });
        ((RadioButton) findViewById(R.id.navigation_analysis)).setChecked(true);
    }
    private void performClickAnimation(View view) {
        view.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(100)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .withEndAction(() -> {
                            startActivity(new Intent(AnalysisActivity.this, MainActivity.class));;
                        })
                        .start())
                .start();
    }
    private void performClickAnimation2(View view) {
        view.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(100)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .withEndAction(() -> {
                            startActivity(new Intent(AnalysisActivity.this, BookActivity.class));
                        })
                        .start())
                .start();
    }
}