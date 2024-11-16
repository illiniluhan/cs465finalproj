package com.example.readsbs;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnalysisAnalysis extends AppCompatActivity {

    private Button backBtn;
    private Button btnRead, btnInProgress, btnToDo;
    private TextView tvAverageSpeed, tvSpeedValue, tvSessionTime, tvSessionValue, tvAdviceTitle, tvAdviceInProcess, tvAdviceToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_analysis);

        // Initialize views
        backBtn = findViewById(R.id.back_btn2);
        btnRead = findViewById(R.id.btn_read);
        btnInProgress = findViewById(R.id.btn_in_progress);
        btnToDo = findViewById(R.id.btn_to_do);

        tvAverageSpeed = findViewById(R.id.tv_average_speed);
        tvSpeedValue = findViewById(R.id.tv_speed_value);
        tvSessionTime = findViewById(R.id.tv_session_time);
        tvSessionValue = findViewById(R.id.tv_session_value);
        tvAdviceTitle = findViewById(R.id.tv_advice_title);
        tvAdviceInProcess = findViewById(R.id.tv_advice_in_process);
        tvAdviceToDo = findViewById(R.id.tv_advice_to_do);

        // Set click listeners (optional, based on your app requirements)
        backBtn.setOnClickListener(v -> onBackPressed());

        btnRead.setOnClickListener(v -> {
            // Handle "Read" button click
        });

        btnInProgress.setOnClickListener(v -> {
            // Handle "In progress" button click
        });

        btnToDo.setOnClickListener(v -> {
            // Handle "To-Do" button click
        });
    }
}
