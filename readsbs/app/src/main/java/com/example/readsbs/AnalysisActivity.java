package com.example.readsbs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity {

    private TextView read_progress;
    private TextView tvAverageSpeed, tvSpeedValue, tvSessionTime, tvSessionValue, tvAdviceTitle, tvAdviceInProcess, tvAdviceToDo;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        read_progress = findViewById(R.id.reading_prog);
        read_progress.setOnClickListener(v -> {
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
        tvSpeedValue.setText("214 wpm");
        tvSessionValue.setText("15.3 minutes");

        // Set static advice
        tvAdviceInProcess.setText("• Try focusing on challenging texts to increase comprehension.");
        tvAdviceToDo.setText("• Explore new genres or authors you haven't read yet.");

        // Bottom navigation
        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_analysis) {
            } else if (checkedId == R.id.navigation_read) {
                Toast.makeText(AnalysisActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnalysisActivity.this, ReadActivity.class));
            } else if (checkedId == R.id.navigation_import) {
                Toast.makeText(AnalysisActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AnalysisActivity.this, MainActivity.class));
            }
        });
        ((RadioButton) findViewById(R.id.navigation_analysis)).setChecked(true);
    }

    private void setChartData(List<Entry> entries) {
        LineDataSet dataSet = new LineDataSet(entries, "Reading Speed (WPM)");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.CYAN);
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(3f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10f);

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
