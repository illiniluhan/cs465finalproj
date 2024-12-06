package com.example.readsbs;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.WindowManager;

import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
public class ContentActivity extends AppCompatActivity {
    private TextView text;
    private Spinner fontSpinner;
    private Spinner colorSpinner;
    private Spinner modeSpinner;
    private SeekBar textSizeSeekBar;
    private SeekBar brightnessSeekBar;
    private TextView content;
    private ConstraintLayout container;
    private CardView cont_container;
    private ImageView back;
    private TextView font_seek_des;
    private TextView bri_seek_des;
    private CardView font_select;
    private CardView color_select;
    private CardView mode_select;
    private CardView shouqi;
    private CardView menu_container;
    private ImageView fang_btn;
    private LinearLayout font_sb;
    private LinearLayout color_sb;
    private SeekBar layoutSeekBar;
    private LinearLayout layout_sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        text = findViewById(R.id.title);
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        menu_container = findViewById(R.id.menu_container);
        font_sb = findViewById(R.id.font_sb);
        color_sb = findViewById(R.id.color_sb);
        layout_sb = findViewById(R.id.layout_sb);

        fontSpinner = findViewById(R.id.font_selection);
        setupFontSpinner(fontSpinner);

        colorSpinner = findViewById(R.id.color_selection);
        setupColorSpinner(colorSpinner);

        modeSpinner = findViewById(R.id.mode_selection);
        setupModeSpinner(modeSpinner);

        font_seek_des = findViewById(R.id.seekbar_font_des);
        bri_seek_des = findViewById(R.id.seekbar_bright_des);

        font_select = findViewById(R.id.font_select);
        color_select = findViewById(R.id.color_select);
        mode_select = findViewById(R.id.mode_select);
        shouqi = findViewById(R.id.shouqi);
        fang_btn = findViewById(R.id.fang_btn);

        font_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation(view);
            }
        });
        color_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation2(view);
            }
        });
        mode_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation3(view);
            }
        });
        shouqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClickAnimation4(view);
            }
        });
        fang_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu_container.setVisibility(View.VISIBLE);
            }
        });

        content = findViewById(R.id.read_content);
        String originalText = content.getText().toString();

        layoutSeekBar = findViewById(R.id.layoutSeekBar);
        layoutSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String[] lines = originalText.split("\n");
                StringBuilder modifiedText = new StringBuilder();

                for (String line : lines) {
                    modifiedText.append(line);
                    for (int i = 0; i < progress; i++) {
                        modifiedText.append("\n");
                    }
                }
                content.setText(modifiedText.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        textSizeSeekBar = findViewById(R.id.textSizeSeekBar);
        textSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int textSize = progress;
                content.setTextSize(textSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        brightnessSeekBar = findViewById(R.id.brightnessSeekBar);

        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float brightness = progress / 100.0f;
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = brightness;
                getWindow().setAttributes(layoutParams);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setupFontSpinner(Spinner fontSpinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fonts_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpinner.setAdapter(adapter);
        fontSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedFont = parent.getItemAtPosition(position).toString();
//                System.out.println("select："+selectedFont);
//                if (!selectedFont.equals(" ")){fontSpinner.setVisibility(View.GONE);}
                switch (selectedFont) {
                    case "New York":
                        Typeface typeface1 = ResourcesCompat.getFont(ContentActivity.this, R.font.newyork);
                        content.setTypeface(typeface1);
                        fontSpinner.setVisibility(View.GONE);
                        break;
                    case "Bionic":
                        Typeface typeface2 = ResourcesCompat.getFont(ContentActivity.this, R.font.bionic);
                        content.setTypeface(typeface2);
                        fontSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        break;
                    case "Times New Roman":
                        Typeface typeface3 = ResourcesCompat.getFont(ContentActivity.this, R.font.timesnewroman);
                        content.setTypeface(typeface3);
                        fontSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        break;
                    case "Helvetica":
                        Typeface typeface4 = ResourcesCompat.getFont(ContentActivity.this, R.font.helvetica);
                        content.setTypeface(typeface4);
                        fontSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupColorSpinner(Spinner colorSpinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorSpinner.setAdapter(adapter);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedColor = parent.getItemAtPosition(position).toString();
                switch (selectedColor) {
                    case "Yellow":
                        cont_container = findViewById(R.id.cont_container);
                        cont_container.setBackgroundColor(Color.parseColor("#BAEFDE73"));
                        content.setTextColor(Color.BLACK);
                        colorSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        break;
                    case "Green":
                        cont_container = findViewById(R.id.cont_container);
                        cont_container.setBackgroundColor(ContextCompat.getColor(ContentActivity.this, R.color.aaa));
                        content.setTextColor(Color.BLACK);
                        colorSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        break;
                    case "Black":
                        cont_container = findViewById(R.id.cont_container);
                        cont_container.setBackgroundColor(ContextCompat.getColor(ContentActivity.this, R.color.bbb));
                        content.setTextColor(Color.WHITE);
                        colorSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        break;
                    case "White":
                        cont_container = findViewById(R.id.cont_container);
                        cont_container.setBackgroundColor(Color.WHITE);
                        content.setTextColor(Color.BLACK);
                        colorSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setupModeSpinner(Spinner modeSpinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mode_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(adapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMode = parent.getItemAtPosition(position).toString();
                switch (selectedMode) {
                    case "Casual":
                        Typeface typeface1 = ResourcesCompat.getFont(ContentActivity.this, R.font.newyork);
                        content.setTypeface(typeface1);
                        cont_container = findViewById(R.id.cont_container);
                        cont_container.setBackgroundColor(Color.WHITE);
                        content.setTextColor(Color.BLACK);
                        float brightness = 80 / 100.0f;
                        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                        layoutParams.screenBrightness = brightness;
                        getWindow().setAttributes(layoutParams);
                        content.setTextSize(18);
                        modeSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        break;
                    case "Speed":
                        Typeface typeface2 = ResourcesCompat.getFont(ContentActivity.this, R.font.bionic);
                        content.setTypeface(typeface2);
                        cont_container = findViewById(R.id.cont_container);
                        cont_container.setBackgroundColor(Color.WHITE);
                        content.setTextColor(Color.BLACK);
                        float brightness2 = 80 / 100.0f;
                        WindowManager.LayoutParams layoutParam2 = getWindow().getAttributes();
                        layoutParam2.screenBrightness = brightness2;
                        getWindow().setAttributes(layoutParam2);
                        content.setTextSize(18);
                        modeSpinner.setVisibility(View.GONE);
                        layout_sb.setVisibility(View.VISIBLE);
                        font_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        break;
                    case "In-depth":
                        Typeface typeface4 = ResourcesCompat.getFont(ContentActivity.this, R.font.helvetica);
                        content.setTypeface(typeface4);
                        cont_container = findViewById(R.id.cont_container);
                        cont_container.setBackgroundColor(Color.WHITE);
                        content.setTextColor(Color.BLACK);
                        float brightness3 = 80 / 100.0f;
                        WindowManager.LayoutParams layoutParam3 = getWindow().getAttributes();
                        layoutParam3.screenBrightness = brightness3;
                        getWindow().setAttributes(layoutParam3);
                        content.setTextSize(18);
                        modeSpinner.setVisibility(View.GONE);
                        font_sb.setVisibility(View.VISIBLE);
                        layout_sb.setVisibility(View.VISIBLE);
                        color_sb.setVisibility(View.VISIBLE);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
                            fontSpinner.performClick();
                            fontSpinner.setVisibility(View.VISIBLE);
                            font_sb.setVisibility(View.GONE);
                            color_sb.setVisibility(View.GONE);
                            layout_sb.setVisibility(View.GONE);
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
                            colorSpinner.performClick();
                            colorSpinner.setVisibility(View.VISIBLE);
                            font_sb.setVisibility(View.GONE);
                            color_sb.setVisibility(View.GONE);
                            layout_sb.setVisibility(View.GONE);
                        })
                        .start())
                .start();
    }
    private void performClickAnimation3(View view) {
        view.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(200)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200)
                        .withEndAction(() -> {
                            modeSpinner.performClick();
                            modeSpinner.setVisibility(View.VISIBLE);
                            font_sb.setVisibility(View.GONE);
                            color_sb.setVisibility(View.GONE);
                            layout_sb.setVisibility(View.GONE);
                        })
                        .start())
                .start();
    }
    private void performClickAnimation4(View view) {
        view.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(200)
                .withEndAction(() -> view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(200)
                        .withEndAction(() -> {
                            menu_container.setVisibility(View.GONE);
                        })
                        .start())
                .start();
    }

}
