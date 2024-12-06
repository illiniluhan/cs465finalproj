package com.example.readsbs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity {

    private TextView read_progress;
    private TextView tvAverageSpeed, tvSpeedValue, tvSessionTime, tvSessionValue, tvAdviceTitle, tvAdviceInProcess, tvAdviceToDo;
    private LineChart lineChart;
    private MaterialCardView reading_prog_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        reading_prog_card = findViewById(R.id.reading_prog_card);
        reading_prog_card.setOnClickListener(v -> {
            Intent intent = new Intent(AnalysisActivity.this, AnalysisSelectReading.class);
            startActivity(intent);
        });

        // Initialize views
        tvAverageSpeed = findViewById(R.id.tv_average_speed);
        tvSpeedValue = findViewById(R.id.tv_speed_value);
        tvSessionTime = findViewById(R.id.tv_session_time);
        tvSessionValue = findViewById(R.id.tv_session_value);
        tvAdviceTitle = findViewById(R.id.tv_advice_title);
        tvAdviceInProcess = findViewById(R.id.tv_advice_in_process);
        tvAdviceToDo = findViewById(R.id.tv_advice_to_do);
        lineChart = findViewById(R.id.line_chart);

        // Set static data directly
        setChartData(getStaticData());
        tvSpeedValue.setText("178 wpm");
        tvSessionValue.setText("15.3 minutes");

        // Set static advice
        tvAdviceInProcess.setText("• Try focusing on challenging texts to increase comprehension.");
        tvAdviceToDo.setText("• Explore new genres or authors you haven't read yet.");

        // Bottom navigation
        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_analysis) {
            } else if (checkedId == R.id.navigation_read) {
            View selectedButton = findViewById(R.id.navigation_read);
            performClickAnimation3(selectedButton);
            } else if (checkedId == R.id.navigation_import) {
                View selectedButton = findViewById(R.id.navigation_import);
                performClickAnimation2(selectedButton);
            }
        });
        ((RadioButton) findViewById(R.id.navigation_analysis)).setChecked(true);
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
                            startActivity(new Intent(AnalysisActivity.this, MainActivity.class));
                        })
                        .start())
                .start();
    }
    private void performClickAnimation3(View view) {
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

    private void setChartData(List<Entry> entries) {
        LineDataSet dataSet = new LineDataSet(entries, "Reading Speed (WPM)");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.CYAN);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(3f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10f);

        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.getDescription().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextColor(Color.BLACK);

        lineChart.getAxisRight().setEnabled(false);
        lineChart.invalidate();
    }

    private List<Entry> getStaticData() {
        // Static dataset (previously for "read" section)
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 150));
        entries.add(new Entry(1, 175));
        entries.add(new Entry(2, 180));
        entries.add(new Entry(3, 190));
        return entries;
    }
}
