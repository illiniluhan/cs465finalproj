package com.example.readsbs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {
    private Button datePickerButton;
    private Button timePickerButton;
    private Button saveButton;
    private Button deleteButton;
    private Calendar selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        selectedDateTime = Calendar.getInstance();
        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        datePickerButton = findViewById(R.id.datePickerButton);
        timePickerButton = findViewById(R.id.timePickerButton);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);
    }

    private void setupListeners() {
        datePickerButton.setOnClickListener(v -> showDatePicker());
        timePickerButton.setOnClickListener(v -> showTimePicker());
        saveButton.setOnClickListener(v -> saveReminder());
        deleteButton.setOnClickListener(v -> deleteReminder());
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedDateTime.set(Calendar.YEAR, year);
                    selectedDateTime.set(Calendar.MONTH, month);
                    selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDateButtonText();
                },
                selectedDateTime.get(Calendar.YEAR),
                selectedDateTime.get(Calendar.MONTH),
                selectedDateTime.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedDateTime.set(Calendar.MINUTE, minute);
                    updateTimeButtonText();
                },
                selectedDateTime.get(Calendar.HOUR_OF_DAY),
                selectedDateTime.get(Calendar.MINUTE),
                false
        );
        timePickerDialog.show();
    }

    private void updateDateButtonText() {
        String date = String.format("%d/%d/%d",
                selectedDateTime.get(Calendar.MONTH) + 1,
                selectedDateTime.get(Calendar.DAY_OF_MONTH),
                selectedDateTime.get(Calendar.YEAR));
        datePickerButton.setText(date);
    }

    private void updateTimeButtonText() {
        String time = String.format("%d:%02d %s",
                selectedDateTime.get(Calendar.HOUR) == 0 ? 12 : selectedDateTime.get(Calendar.HOUR),
                selectedDateTime.get(Calendar.MINUTE),
                selectedDateTime.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");
        timePickerButton.setText(time);
    }

    private void saveReminder() {
        // TODO: Implement saving reminder functionality
        Toast.makeText(this, "Reminder saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void deleteReminder() {
        // TODO: Implement delete reminder functionality
        Toast.makeText(this, "Reminder deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
