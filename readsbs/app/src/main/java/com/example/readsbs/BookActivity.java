package com.example.readsbs;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewPropertyAnimator;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;


public class BookActivity extends AppCompatActivity {

    private CardView book1;
    private CardView book2;
    private CardView book3;
    private CardView book4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        book1 = findViewById(R.id.book1);
        book2 = findViewById(R.id.book2);
        book3 = findViewById(R.id.book3);
        book4 = findViewById(R.id.book4);

        RadioGroup bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.navigation_read) {
            } else if (checkedId == R.id.navigation_import) {
                Toast.makeText(BookActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BookActivity.this, MainActivity.class));
            } else if (checkedId == R.id.navigation_analysis) {
                Toast.makeText(BookActivity.this, "Redirecting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BookActivity.this, AnalysisActivity.class));
            }
        });
        ((RadioButton) findViewById(R.id.navigation_read)).setChecked(true);
        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        book2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        book3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        book4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
    }

    private void performClickAnimation(View view) {
        view.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(200)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200)
                        .withEndAction(() -> {
                            Intent intent = new Intent(BookActivity.this, DetailActivity.class);
                            startActivity(intent);
                        })
                        .start())
                .start();
    }




}
