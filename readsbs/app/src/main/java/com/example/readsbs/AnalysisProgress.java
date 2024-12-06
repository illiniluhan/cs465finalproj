package com.example.readsbs;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class AnalysisProgress extends AppCompatActivity {

    private ImageView backBtn;
    private TextView tvCategoryTitle, tvOverallPercentage, tvEstimatedTimeCategory;
    private ProgressBar largeCircularProgress;
    private LinearLayout llBooksContainer;

    private String selectedCategory;
    private ArrayList<BookItem> booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_progress);

        backBtn = findViewById(R.id.back_btn2);
        tvCategoryTitle = findViewById(R.id.tv_category_title);
        tvOverallPercentage = findViewById(R.id.tv_overall_percentage);
        tvEstimatedTimeCategory = findViewById(R.id.tv_estimated_time_category);
        largeCircularProgress = findViewById(R.id.large_circular_progress);
        llBooksContainer = findViewById(R.id.ll_books_container);

        selectedCategory = getIntent().getStringExtra("selectedCategory");

        setupDataForCategory(selectedCategory);

        // Calculate overall progress and estimated time
        int totalProgress = 0;
        int totalTime = 0;
        for (BookItem item : booksList) {
            totalProgress += item.progress;
            totalTime += parseTime(item.estimatedTime);
        }
        int overallProgress = booksList.size() > 0 ? totalProgress / booksList.size() : 0;
        int overallTime = totalTime;

        tvCategoryTitle.setText(selectedCategory);
        largeCircularProgress.setProgress(overallProgress);
        tvOverallPercentage.setText(overallProgress + "%");
        tvEstimatedTimeCategory.setText("Estimated Time: " + overallTime + " min");

        createBookRows(booksList);

        backBtn.setOnClickListener(v -> onBackPressed());
    }

    private void setupDataForCategory(String category) {
        booksList = new ArrayList<>();

        switch (category) {
            case "Books":
                booksList.add(new BookItem("A Brief History of Time", 100, "0 min"));
                booksList.add(new BookItem("The Selfish Gene", 28, "17 min"));
                booksList.add(new BookItem("Cosmos", 5, "8 min"));
                break;

            case "Research Papers":
                booksList.add(new BookItem("CRISPR Gene Editing: A Review", 45, "22 min"));
                booksList.add(new BookItem("Deep Learning in Autonomous Vehicles", 80, "10 min"));
                booksList.add(new BookItem("Quantum Computing Frontier", 30, "30 min"));
                booksList.add(new BookItem("Climate Change Impacts on Agriculture", 60, "15 min"));
                booksList.add(new BookItem("Nanomaterials in Medicine", 20, "25 min"));
                booksList.add(new BookItem("Black Hole Thermodynamics: A Survey", 90, "5 min"));
                break;

            case "Articles":
                booksList.add(new BookItem("The Future of Renewable Energy", 10, "40 min"));
                booksList.add(new BookItem("Trends in Cryptocurrency", 40, "5 min"));
                booksList.add(new BookItem("Meditation and Mental Health", 25, "0 min"));
                booksList.add(new BookItem("Renaissance Art in Modern Context", 50, "10 min"));
                booksList.add(new BookItem("Global Economic Outlook", 70, "15 min"));
                booksList.add(new BookItem("Soccer Analytics Revolution", 30, "20 min"));
                booksList.add(new BookItem("The Evolution of Pop Music", 55, "8 min"));
                booksList.add(new BookItem("Sustainable Tourism Practices", 35, "12 min"));
                break;

            default:
                booksList.add(new BookItem("Default Title 1", 0, "0 min"));
                booksList.add(new BookItem("Default Title 2", 0, "0 min"));
                booksList.add(new BookItem("Default Title 3", 0, "0 min"));
                break;
        }
    }

    private void createBookRows(ArrayList<BookItem> items) {
        int totalItems = items.size();
        int index = 0;
        while (index < totalItems) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            rowLayout.setWeightSum(3f);
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            rowParams.setMargins(0, 24, 0, 0);
            rowLayout.setLayoutParams(rowParams);

            int itemsInThisRow = Math.min(3, totalItems - index);
            for (int i = 0; i < itemsInThisRow; i++) {
                BookItem book = items.get(index + i);
                MaterialCardView card = createBookCard(book);
                rowLayout.addView(card);
            }

            llBooksContainer.addView(rowLayout);
            index += itemsInThisRow;
        }
    }

    private MaterialCardView createBookCard(BookItem book) {
        MaterialCardView card = new MaterialCardView(this);
        card.setCardElevation(8f);
        card.setRadius(16f);
        card.setUseCompatPadding(true);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        cardParams.setMargins(8, 0, 8, 0);
        card.setLayoutParams(cardParams);

        LinearLayout cardContentLayout = new LinearLayout(this);
        cardContentLayout.setOrientation(LinearLayout.VERTICAL);
        cardContentLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        cardContentLayout.setPadding(16, 16, 16, 16);
        card.addView(cardContentLayout);

        // Frame layout for the circular progress bar and overlay text
        FrameLayout frameLayout = new FrameLayout(this);
        LinearLayout.LayoutParams frameParams = new LinearLayout.LayoutParams(300, 300);
        frameParams.gravity = Gravity.CENTER;
        frameLayout.setLayoutParams(frameParams);

        // The circular progress bar
        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        FrameLayout.LayoutParams pbParams = new FrameLayout.LayoutParams(300, 300);
        progressBar.setLayoutParams(pbParams);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(book.progress);
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.circular_progress));
        progressBar.setBackground(getResources().getDrawable(R.drawable.progress_background));
        frameLayout.addView(progressBar);

        // Overlay layout for percentage and estimated time (inside the circle)
        LinearLayout overlayLayout = new LinearLayout(this);
        overlayLayout.setOrientation(LinearLayout.VERTICAL);
        overlayLayout.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams overlayParams = new FrameLayout.LayoutParams(300, 300);
        overlayLayout.setLayoutParams(overlayParams);

        TextView tvProgress = new TextView(this);
        tvProgress.setText(book.progress + "%");
        tvProgress.setTextSize(18);
        tvProgress.setTypeface(null, Typeface.BOLD);
        tvProgress.setGravity(Gravity.CENTER);

        TextView tvTime = new TextView(this);
        tvTime.setText(book.estimatedTime);
        tvTime.setTextSize(14);
        tvTime.setGravity(Gravity.CENTER);

        overlayLayout.addView(tvProgress);
        overlayLayout.addView(tvTime);

        frameLayout.addView(overlayLayout);

        // Add the frameLayout (circle progress) to the card layout
        cardContentLayout.addView(frameLayout);

        // Title below the drawable, outside the circle
        TextView tvBookTitle = new TextView(this);
        tvBookTitle.setText(book.title);
        tvBookTitle.setTextSize(14);
        tvBookTitle.setTypeface(null, Typeface.ITALIC);

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.topMargin = 16;
        tvBookTitle.setLayoutParams(titleParams);

        cardContentLayout.addView(tvBookTitle);

        return card;
    }

    private int parseTime(String timeStr) {
        if (timeStr.endsWith(" min")) {
            timeStr = timeStr.replace(" min", "").trim();
        }
        try {
            return Integer.parseInt(timeStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    static class BookItem {
        String title;
        int progress;
        String estimatedTime;

        BookItem(String title, int progress, String estimatedTime) {
            this.title = title;
            this.progress = progress;
            this.estimatedTime = estimatedTime;
        }
    }
}
