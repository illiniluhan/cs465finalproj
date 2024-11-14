package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity {

    private LinearLayout bookSection;
    private LinearLayout bookSectionDetails;
    private LinearLayout bookSectionDetails2;
    private LinearLayout bookSectionDetails3;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);

        bookSection = findViewById(R.id.book_section);
        bookSectionDetails = findViewById(R.id.book_section_details);
        bookSectionDetails2 = findViewById(R.id.book_section_details2);
        bookSectionDetails3 = findViewById(R.id.book_section_details3);

        bookSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    bookSectionDetails.setVisibility(View.GONE);
                    bookSectionDetails2.setVisibility(View.GONE);
                    bookSectionDetails3.setVisibility(View.GONE);
                } else {
                    bookSectionDetails.setVisibility(View.VISIBLE);
                    bookSectionDetails2.setVisibility(View.VISIBLE);
                    bookSectionDetails3.setVisibility(View.VISIBLE);
                }
                isExpanded = !isExpanded;
            }
        });

        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_read) {
            } else if (checkedId == R.id.navigation_import) {
                Toast.makeText(ReadActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ReadActivity.this, MainActivity.class));
            } else if (checkedId == R.id.navigation_analysis) {
                Toast.makeText(ReadActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ReadActivity.this, AnalysisActivity.class));
            }
        });
        ((RadioButton) findViewById(R.id.navigation_read)).setChecked(true);
    }
}