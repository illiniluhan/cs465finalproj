package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        //
        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_analysis) {
                // Cur
            } else if (checkedId == R.id.navigation_import) {
                Toast.makeText(AnalysisActivity.this, "Redirecting to Import", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnalysisActivity.this, MainActivity.class));
            } else if (checkedId == R.id.navigation_read) {
                Toast.makeText(AnalysisActivity.this, "Redirecting to Read", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnalysisActivity.this, ReadActivity.class));
            }
        });
        ((RadioButton) findViewById(R.id.navigation_analysis)).setChecked(true);

        // Article
        TextView title = findViewById(R.id.article_title);
        TextView description = findViewById(R.id.article_description);
        title.setText(getString(R.string.chapter_title));
        description.setText(getString(R.string.chapter_description));

        // Progress
        ProgressBar overallProgress = findViewById(R.id.progress_overall);
        TextView overallProgressPercentage = findViewById(R.id.progress_percentage);
        overallProgress.setMax(100);
        overallProgress.setProgress(60);
        overallProgressPercentage.setText("60%");

        // Section
        ProgressBar section1Progress = findViewById(R.id.section1_progress);
        ProgressBar section2Progress = findViewById(R.id.section2_progress);

        section1Progress.setMax(100);
        section1Progress.setProgress(75);

        section2Progress.setMax(100);
        section2Progress.setProgress(30);

        // Estimate
        TextView estimatedTime = findViewById(R.id.estimated_time);
        estimatedTime.setText(getString(R.string.estimated_time));

        // Mode
        RadioGroup readingModeGroup = findViewById(R.id.reading_mode_group);
        readingModeGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String mode = "";
            if (checkedId == R.id.normal_mode) {
                mode = "Normal Mode";
            } else if (checkedId == R.id.dark_mode) {
                mode = "Dark Mode";
            } else if (checkedId == R.id.focus_mode) {
                mode = "Focus Mode";
            }
            Toast.makeText(AnalysisActivity.this, "Reading Mode: " + mode, Toast.LENGTH_SHORT).show();
        });
    }
}


//package com.example.readsbs;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class AnalysisActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_analysis);
//
//        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);
//
//        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId == R.id.navigation_analysis) {
//            } else if (checkedId == R.id.navigation_import) {
//                Toast.makeText(AnalysisActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(AnalysisActivity.this, MainActivity.class));
//            } else if (checkedId == R.id.navigation_read) {
//                Toast.makeText(AnalysisActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(AnalysisActivity.this, ReadActivity.class));
//            }
//        });
//        ((RadioButton) findViewById(R.id.navigation_analysis)).setChecked(true);
//    }
//}