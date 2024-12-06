package com.example.readsbs;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class AnalysisAnalysis extends AppCompatActivity {

    private Button backBtn;
    private Button btnRead, btnInProgress, btnToDo;
    private TextView tvAverageSpeed, tvSpeedValue, tvSessionTime, tvSessionValue, tvAdviceTitle, tvAdviceInProcess, tvAdviceToDo;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_analysis);

        // Initialize views
        backBtn = findViewById(R.id.back_btn2);
        btnRead = findViewById(R.id.btn_completed);
        btnInProgress = findViewById(R.id.btn_in_progress);
        btnToDo = findViewById(R.id.btn_to_do);

        tvAverageSpeed = findViewById(R.id.tv_average_speed);
        tvSpeedValue = findViewById(R.id.tv_speed_value);
        tvSessionTime = findViewById(R.id.tv_session_time);
        tvSessionValue = findViewById(R.id.tv_session_value);
        tvAdviceTitle = findViewById(R.id.tv_advice_title);
        tvAdviceInProcess = findViewById(R.id.tv_advice_in_process);
        tvAdviceToDo = findViewById(R.id.tv_advice_to_do);

        lineChart = findViewById(R.id.line_chart);

        updateUIForSection("read");

        // Button click listeners
        backBtn.setOnClickListener(v -> onBackPressed());

        btnRead.setOnClickListener(v -> updateUIForSection("read"));
        btnInProgress.setOnClickListener(v -> updateUIForSection("in_progress"));
        btnToDo.setOnClickListener(v -> updateUIForSection("to_do"));
    }

    private void updateUIForSection(String section) {
        // Update the chart
        setChartData(getDataForSection(section));

        // Update stats
        Stats stats = getStatsForSection(section);
        tvSpeedValue.setText(stats.averageSpeed + " wpm");
        tvSessionValue.setText(stats.averageSessionTime + " minutes");

        // Update advice
        Advice advice = getAdviceForSection(section);
        tvAdviceInProcess.setText("• " + advice.inProcessAdvice);
        tvAdviceToDo.setText("• " + advice.toDoAdvice);
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




        // Customize X Axis
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.BLACK);

        // Customize Y Axis
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextColor(Color.BLACK);

        // Disable right Y Axis
        lineChart.getAxisRight().setEnabled(false);

        lineChart.invalidate();
    }
    private List<Entry> getDataForSection(String section) {
        List<Entry> entries = new ArrayList<>();

        switch (section) {
            case "read":
                entries.add(new Entry(0, 150));
                entries.add(new Entry(1, 175));
                entries.add(new Entry(2, 180));
                entries.add(new Entry(3, 190));
                break;
            case "in_progress":
                entries.add(new Entry(0, 120));
                entries.add(new Entry(1, 130));
                entries.add(new Entry(2, 140));
                entries.add(new Entry(3, 160));
                break;
            case "to_do":
                entries.add(new Entry(0, 0));
                entries.add(new Entry(1, 50));
                entries.add(new Entry(2, 90));
                entries.add(new Entry(3, 110));
                break;
        }

        return entries;
    }

    private Stats getStatsForSection(String section) {
        Stats stats = new Stats();
        switch (section) {
            case "read":
                stats.averageSpeed = 214;       // wpm (mock)
                stats.averageSessionTime = 15.3f; // minutes (mock)
                break;
            case "in_progress":
                stats.averageSpeed = 130;       // wpm (mock)
                stats.averageSessionTime = 10.0f; // minutes (mock)
                break;
            case "to_do":
                stats.averageSpeed = 50;        // wpm (mock - user just starting)
                stats.averageSessionTime = 5.0f; // minutes (mock)
                break;
        }
        return stats;
    }

    private Advice getAdviceForSection(String section) {
        Advice advice = new Advice();
        switch (section) {
            case "read":
                advice.inProcessAdvice = "Try focusing on challenging texts to increase comprehension.";
                advice.toDoAdvice = "Explore new genres or authors you haven't read yet.";
                break;
            case "in_progress":
                advice.inProcessAdvice = "Keep a steady pace, schedule short reading bursts.";
                advice.toDoAdvice = "Plan your next read and set daily reading goals.";
                break;
            case "to_do":
                advice.inProcessAdvice = "Begin with short sessions to build momentum.";
                advice.toDoAdvice = "Create a reading list to guide your reading journey.";
                break;
        }
        return advice;
    }


    private static class Stats {
        float averageSpeed;
        float averageSessionTime;
    }

    private static class Advice {
        String inProcessAdvice;
        String toDoAdvice;
    }
}
