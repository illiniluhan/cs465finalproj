package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity {

    private LinearLayout bookSection1;
    private LinearLayout bookSectionDetails11;
    private LinearLayout bookSectionDetails12;
    private LinearLayout bookSectionDetails13;

    private LinearLayout bookSection2;
    private LinearLayout bookSectionDetails21;
    private LinearLayout bookSectionDetails22;
    private LinearLayout bookSectionDetails23;
    private LinearLayout bookSectionDetails24;

    private Button readsec11;
    private Button readsec12;
    private Button readsec13;
    private Button readsec21;
    private Button readsec22;
    private Button readsec23;
    private Button readsec24;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);

        bookSection1 = findViewById(R.id.book1);
        bookSectionDetails11 = findViewById(R.id.book_section_details11);
        bookSectionDetails12 = findViewById(R.id.book_section_details12);
        bookSectionDetails13 = findViewById(R.id.book_section_details13);

        bookSection2 = findViewById(R.id.book2);
        bookSectionDetails21 = findViewById(R.id.book_section_details21);
        bookSectionDetails22 = findViewById(R.id.book_section_details22);
        bookSectionDetails23 = findViewById(R.id.book_section_details23);
        bookSectionDetails24 = findViewById(R.id.book_section_details24);

        readsec11 = findViewById(R.id.read_btn11);
        readsec12 = findViewById(R.id.read_btn12);
        readsec13 = findViewById(R.id.read_btn13);
        readsec21 = findViewById(R.id.read_btn21);
        readsec22 = findViewById(R.id.read_btn22);
        readsec23 = findViewById(R.id.read_btn23);
        readsec24 = findViewById(R.id.read_btn24);

        bookSection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    bookSectionDetails11.setVisibility(View.GONE);
                    bookSectionDetails12.setVisibility(View.GONE);
                    bookSectionDetails13.setVisibility(View.GONE);
                } else {
                    bookSectionDetails11.setVisibility(View.VISIBLE);
                    bookSectionDetails12.setVisibility(View.VISIBLE);
                    bookSectionDetails13.setVisibility(View.VISIBLE);
                }
                isExpanded = !isExpanded;
            }
        });

        bookSection2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    bookSectionDetails21.setVisibility(View.GONE);
                    bookSectionDetails22.setVisibility(View.GONE);
                    bookSectionDetails23.setVisibility(View.GONE);
                    bookSectionDetails24.setVisibility(View.GONE);
                } else {
                    bookSectionDetails21.setVisibility(View.VISIBLE);
                    bookSectionDetails22.setVisibility(View.VISIBLE);
                    bookSectionDetails23.setVisibility(View.VISIBLE);
                    bookSectionDetails24.setVisibility(View.VISIBLE);
                }
                isExpanded = !isExpanded;
            }
        });

        readsec11.setOnClickListener(v -> {
            Intent intent = new Intent(ReadActivity.this, ContentActivity.class);
            intent.putExtra("section_data", "section11");
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        readsec12.setOnClickListener(v -> {
            Intent intent = new Intent(ReadActivity.this, ContentActivity.class);
            intent.putExtra("section_data", "section12");
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        readsec13.setOnClickListener(v -> {
            Intent intent = new Intent(ReadActivity.this, ContentActivity.class);
            intent.putExtra("section_data", "section13");
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        readsec21.setOnClickListener(v -> {
            Intent intent = new Intent(ReadActivity.this, ContentActivity.class);
            intent.putExtra("section_data", "section21");
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        readsec22.setOnClickListener(v -> {
            Intent intent = new Intent(ReadActivity.this, ContentActivity.class);
            intent.putExtra("section_data", "section22");
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        readsec23.setOnClickListener(v -> {
            Intent intent = new Intent(ReadActivity.this, ContentActivity.class);
            intent.putExtra("section_data", "section23");
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        readsec24.setOnClickListener(v -> {
            Intent intent = new Intent(ReadActivity.this, ContentActivity.class);
            intent.putExtra("section_data", "section24");
            Toast.makeText(this, "Redirecting", Toast.LENGTH_SHORT).show();
            startActivity(intent);
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