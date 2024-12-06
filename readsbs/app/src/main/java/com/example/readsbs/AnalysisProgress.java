package com.example.readsbs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnalysisProgress extends AppCompatActivity {

    private ProgressBar progressIntro, progressExperiment, progressConclusion;
    private Button backBtn;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_progress);

        backBtn = findViewById(R.id.back_btn3);
        String selectedReading = getIntent().getStringExtra("selectedReading");

        TextView tvArticleTitle = findViewById(R.id.tv_article_title);
        tvArticleTitle.setText(selectedReading);

        // Find the progress bars
        progressIntro = findViewById(R.id.progress_intro);
        progressExperiment = findViewById(R.id.progress_experiment);
        progressConclusion = findViewById(R.id.progress_conclusion);
        backBtn.setOnClickListener(v -> onBackPressed());

        // Set initial progress for each section
        setSectionProgress(progressIntro, 100); // 100% progress for Introduction
        setSectionProgress(progressExperiment, 28); // 28% progress for Experiment
        setSectionProgress(progressConclusion, 5); // 5% progress for Conclusion
    }

    /**
     * Updates the progress of a given ProgressBar.
     * @param progressBar The ProgressBar to update.
     * @param progressValue The progress value (0-100).
     */
    private void setSectionProgress(ProgressBar progressBar, int progressValue) {
        progressBar.setProgress(progressValue);
    }
}