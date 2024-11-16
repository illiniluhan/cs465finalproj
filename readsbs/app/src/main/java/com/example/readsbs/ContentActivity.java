package com.example.readsbs;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.WindowManager;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
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
    private Button setting;
    private boolean isExpanded = false;
    private Button back;
    private TextView font_seek_des;
    private TextView bri_seek_des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String sectionData = getIntent().getStringExtra("section_data");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        text = findViewById(R.id.title);
        text.setText(sectionData);
        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setting = findViewById(R.id.setting_btn);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    fontSpinner.setVisibility(View.GONE);
                    colorSpinner.setVisibility(View.GONE);
                    modeSpinner.setVisibility(View.GONE);
                    brightnessSeekBar.setVisibility(View.GONE);
                    textSizeSeekBar.setVisibility(View.GONE);
                    font_seek_des.setVisibility(View.GONE);
                    bri_seek_des.setVisibility(View.GONE);
                } else {
                    fontSpinner.setVisibility(View.VISIBLE);
                    colorSpinner.setVisibility(View.VISIBLE);
                    modeSpinner.setVisibility(View.VISIBLE);
                    brightnessSeekBar.setVisibility(View.VISIBLE);
                    textSizeSeekBar.setVisibility(View.VISIBLE);
                    font_seek_des.setVisibility(View.VISIBLE);
                    bri_seek_des.setVisibility(View.VISIBLE);
                }
                isExpanded = !isExpanded;
            }
        });

        fontSpinner = findViewById(R.id.font_selection);
        setupFontSpinner(fontSpinner);

        colorSpinner = findViewById(R.id.color_selection);
        setupColorSpinner(colorSpinner);

        modeSpinner = findViewById(R.id.mode_selection);
        setupModeSpinner(modeSpinner);

        font_seek_des = findViewById(R.id.seekbar_font_des);
        bri_seek_des = findViewById(R.id.seekbar_bright_des);
        content = findViewById(R.id.read_content);
        textSizeSeekBar = findViewById(R.id.textsize_seekbar);
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

        brightnessSeekBar = findViewById(R.id.brightness_seekbar);
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
                switch (selectedFont) {
                    case "Choose Font":
                        Toast.makeText(ContentActivity.this, "Please choose font", Toast.LENGTH_SHORT).show();
                        break;
                    case "New York":
                        Typeface typeface1 = ResourcesCompat.getFont(ContentActivity.this, R.font.newyork);
                        content.setTypeface(typeface1);
                        break;
                    case "Bionic":
                        Typeface typeface2 = ResourcesCompat.getFont(ContentActivity.this, R.font.bionic);
                        content.setTypeface(typeface2);
                        break;
                    case "Default":
                        Typeface typeface3 = ResourcesCompat.getFont(ContentActivity.this, R.font.timesnewroman);
                        content.setTypeface(typeface3);
                    default:
                        Typeface typeface4 = ResourcesCompat.getFont(ContentActivity.this, R.font.timesnewroman);
                        content.setTypeface(typeface4);
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
                    case "Choose Background Color":
                        Toast.makeText(ContentActivity.this, "Please choose background color", Toast.LENGTH_SHORT).show();
                        break;
                    case "Yellow":
                        container = findViewById(R.id.content_container);
                        container.setBackgroundColor(Color.YELLOW);
                        fontSpinner.setBackgroundColor(Color.YELLOW);
                        colorSpinner.setBackgroundColor(Color.YELLOW);
                        modeSpinner.setBackgroundColor(Color.YELLOW);
                        textSizeSeekBar.setBackgroundColor(Color.YELLOW);
                        brightnessSeekBar.setBackgroundColor(Color.YELLOW);
                        font_seek_des.setBackgroundColor(Color.YELLOW);
                        bri_seek_des.setBackgroundColor(Color.YELLOW);
                        break;
                    case "Green":
                        container = findViewById(R.id.content_container);
                        container.setBackgroundColor(Color.GREEN);
                        fontSpinner.setBackgroundColor(Color.GREEN);
                        colorSpinner.setBackgroundColor(Color.GREEN);
                        modeSpinner.setBackgroundColor(Color.GREEN);
                        textSizeSeekBar.setBackgroundColor(Color.GREEN);
                        brightnessSeekBar.setBackgroundColor(Color.GREEN);
                        font_seek_des.setBackgroundColor(Color.GREEN);
                        bri_seek_des.setBackgroundColor(Color.GREEN);
                        break;
                    case "Black":
                        container = findViewById(R.id.content_container);
                        container.setBackgroundColor(Color.BLACK);
                        fontSpinner.setBackgroundColor(Color.BLACK);
                        colorSpinner.setBackgroundColor(Color.BLACK);
                        modeSpinner.setBackgroundColor(Color.BLACK);
                        textSizeSeekBar.setBackgroundColor(Color.BLACK);
                        brightnessSeekBar.setBackgroundColor(Color.BLACK);
                        font_seek_des.setBackgroundColor(Color.BLACK);                        font_seek_des.setBackgroundColor(Color.BLACK);
                        bri_seek_des.setBackgroundColor(Color.BLACK);
                        break;
                    default:
                        container = findViewById(R.id.content_container);
                        container.setBackgroundColor(Color.WHITE);
                        fontSpinner.setBackgroundColor(Color.WHITE);
                        colorSpinner.setBackgroundColor(Color.WHITE);
                        modeSpinner.setBackgroundColor(Color.WHITE);
                        textSizeSeekBar.setBackgroundColor(Color.WHITE);
                        brightnessSeekBar.setBackgroundColor(Color.WHITE);
                        font_seek_des.setBackgroundColor(Color.WHITE);
                        bri_seek_des.setBackgroundColor(Color.WHITE);
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
                    case "Choose Mode":
                        Toast.makeText(ContentActivity.this, "Please choose Mode", Toast.LENGTH_SHORT).show();
                        break;
                    case "Speed":
                        Typeface typeface1 = ResourcesCompat.getFont(ContentActivity.this, R.font.newyork);
                        content.setTypeface(typeface1);
                        container = findViewById(R.id.content_container);
                        container.setBackgroundColor(Color.WHITE);
                        fontSpinner.setBackgroundColor(Color.WHITE);
                        colorSpinner.setBackgroundColor(Color.WHITE);
                        modeSpinner.setBackgroundColor(Color.WHITE);
                        textSizeSeekBar.setBackgroundColor(Color.WHITE);
                        brightnessSeekBar.setBackgroundColor(Color.WHITE);
                        font_seek_des.setBackgroundColor(Color.WHITE);
                        bri_seek_des.setBackgroundColor(Color.WHITE);
                        float brightness = 80 / 100.0f;
                        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                        layoutParams.screenBrightness = brightness;
                        getWindow().setAttributes(layoutParams);
                        content.setTextSize(12);
                        break;
                    case "Bionic":
                        Typeface typeface2 = ResourcesCompat.getFont(ContentActivity.this, R.font.bionic);
                        content.setTypeface(typeface2);
                        container = findViewById(R.id.content_container);
                        container.setBackgroundColor(Color.WHITE);
                        fontSpinner.setBackgroundColor(Color.WHITE);
                        colorSpinner.setBackgroundColor(Color.WHITE);
                        modeSpinner.setBackgroundColor(Color.WHITE);
                        textSizeSeekBar.setBackgroundColor(Color.WHITE);
                        brightnessSeekBar.setBackgroundColor(Color.WHITE);
                        font_seek_des.setBackgroundColor(Color.WHITE);
                        bri_seek_des.setBackgroundColor(Color.WHITE);
                        float brightness2 = 80 / 100.0f;
                        WindowManager.LayoutParams layoutParam2 = getWindow().getAttributes();
                        layoutParam2.screenBrightness = brightness2;
                        getWindow().setAttributes(layoutParam2);
                        content.setTextSize(16);
                        break;
                    default:
                        Typeface typeface4 = ResourcesCompat.getFont(ContentActivity.this, R.font.timesnewroman);
                        content.setTypeface(typeface4);
                        container = findViewById(R.id.content_container);
                        container.setBackgroundColor(Color.WHITE);
                        fontSpinner.setBackgroundColor(Color.WHITE);
                        colorSpinner.setBackgroundColor(Color.WHITE);
                        modeSpinner.setBackgroundColor(Color.WHITE);
                        textSizeSeekBar.setBackgroundColor(Color.WHITE);
                        brightnessSeekBar.setBackgroundColor(Color.WHITE);
                        font_seek_des.setBackgroundColor(Color.WHITE);
                        bri_seek_des.setBackgroundColor(Color.WHITE);
                        float brightness3 = 80 / 100.0f;
                        WindowManager.LayoutParams layoutParam3 = getWindow().getAttributes();
                        layoutParam3.screenBrightness = brightness3;
                        getWindow().setAttributes(layoutParam3);
                        content.setTextSize(14);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}
