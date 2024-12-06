package com.example.readsbs;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;

public class DetailActivity extends AppCompatActivity {
    private ImageView return_btn;
//    private CardView article_container;
    private CardView sec1_det;
    private CardView sec2_det;
    private CardView sec3_det;
    private CardView sec4_det;
    private CardView sec5_det;
    private CardView sec6_det;
    private CardView sec7_det;
    private CardView alarm_1;
    private CardView alarm_2;
    private CardView alarm_3;
    private CardView alarm_4;
    private CardView alarm_5;
    private CardView alarm_6;
    private CardView alarm_7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

//        article_container = findViewById(R.id.article_container);
        sec1_det = findViewById(R.id.sec1_det);
        sec2_det = findViewById(R.id.sec2_det);
        sec3_det = findViewById(R.id.sec3_det);
        sec4_det = findViewById(R.id.sec4_det);
        sec5_det = findViewById(R.id.sec5_det);
        sec6_det = findViewById(R.id.sec6_det);
        sec7_det = findViewById(R.id.sec7_det);
        alarm_1 = findViewById(R.id.alarm_1);
        alarm_2 = findViewById(R.id.alarm_2);
        alarm_3 = findViewById(R.id.alarm_3);
        alarm_4 = findViewById(R.id.alarm_4);
        alarm_5 = findViewById(R.id.alarm_5);
        alarm_6 = findViewById(R.id.alarm_6);
        alarm_7 = findViewById(R.id.alarm_7);
        return_btn = findViewById(R.id.return_btn);

        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sec1_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        sec2_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        sec3_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        sec4_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        sec5_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        sec6_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        sec7_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        alarm_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
            }
        });
        alarm_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
            }
        });
        alarm_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
            }
        });
        alarm_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
            }
        });
        alarm_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
            }
        });
        alarm_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
            }
        });
        alarm_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
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
                            Intent intent = new Intent(DetailActivity.this, ContentActivity.class);
                            startActivity(intent);
                        })
                        .start())
                .start();
    }
    private void performClickAnimation2(View view) {
        view.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(200)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200)
                        .withEndAction(() -> {
                            Intent intent = new Intent(DetailActivity.this, ReminderActivity.class);
                            startActivity(intent);
                        })
                        .start())
                .start();
    }
}
