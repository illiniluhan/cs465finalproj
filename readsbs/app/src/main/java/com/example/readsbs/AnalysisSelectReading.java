package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AnalysisSelectReading extends AppCompatActivity {

    private ListView readingListView;
    private ArrayList<String> readingList;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_selection);
        backBtn = findViewById(R.id.back_btn2);

        readingListView = findViewById(R.id.reading_list_view);

        readingList = new ArrayList<>();
        readingList.add("Books");
        readingList.add("Research Papers");
        readingList.add("Articles");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, readingList);
        readingListView.setAdapter(adapter);

        backBtn.setOnClickListener(v -> onBackPressed());
        readingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedReading = readingList.get(position);
                Intent intent = new Intent(AnalysisSelectReading.this, AnalysisProgress.class);
                intent.putExtra("selectedReading", selectedReading);
                startActivity(intent);
            }
        });
    }
}