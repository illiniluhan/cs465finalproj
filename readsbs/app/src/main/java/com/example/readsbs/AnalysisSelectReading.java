package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class AnalysisSelectReading extends AppCompatActivity {

    private ImageView backBtn;
    private MaterialCardView cardBooks, cardResearchPapers, cardArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_selection);

        backBtn = findViewById(R.id.back_btn2);
        cardBooks = findViewById(R.id.card_books);
        cardResearchPapers = findViewById(R.id.card_research_papers);
        cardArticles = findViewById(R.id.card_articles);

        backBtn.setOnClickListener(v -> onBackPressed());

        cardBooks.setOnClickListener(v -> openAnalysisProgress("Books"));
        cardResearchPapers.setOnClickListener(v -> openAnalysisProgress("Research Papers"));
        cardArticles.setOnClickListener(v -> openAnalysisProgress("Articles"));
    }

    private void openAnalysisProgress(String selectedReading) {
        Intent intent = new Intent(AnalysisSelectReading.this, AnalysisProgress.class);
        intent.putExtra("selectedCategory", selectedReading);
        startActivity(intent);
    }
}
