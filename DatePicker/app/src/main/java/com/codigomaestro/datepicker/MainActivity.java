package com.codigomaestro.datepicker;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    private TextView tvBirthDate, tvCurrentDate, tvAge, tvCurrentTime;
    private Button btnSelectDate;
    private Calendar birthDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvCurrentDate = findViewById(R.id.tvCurrentDate);
        tvAge = findViewById(R.id.tvAge);
        tvCurrentTime = findViewById(R.id.tvCurrentTime);
        btnSelectDate = findViewById(R.id.btnSelectDate);

        btnSelectDate.setOnClickListener(v -> showDatePickerDialog());

        // Set initial values
        updateCurrentDateTime();
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, monthOfYear, dayOfMonth) -> {
            birthDate = Calendar.getInstance();
            birthDate.set(year1, monthOfYear, dayOfMonth);
            updateBirthDate();
        }, year, month, day);
        datePickerDialog.show();
    }

    private void updateBirthDate() {
        if (birthDate == null) return;

        Calendar today = Calendar.getInstance();
        int ageYears = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        int ageMonths = today.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH);

        if (ageMonths < 0) {
            ageYears--;
            ageMonths += 12;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String birthDateString = dateFormat.format(birthDate.getTime());
        String currentDateString = dateFormat.format(today.getTime());

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTimeString = timeFormat.format(new Date());

        tvBirthDate.setText("Fecha de Nacimiento: " + birthDateString);
        tvCurrentDate.setText("Fecha Actual: " + currentDateString);
        tvAge.setText("Edad: " + ageYears + " años y " + ageMonths + " meses");
        tvCurrentTime.setText("Hora Actual: " + currentTimeString);

        // Apply animations
        animateTextView(tvBirthDate);
        animateTextView(tvCurrentDate);
        animateTextView(tvAge);
        animateTextView(tvCurrentTime);
    }

    private void updateCurrentDateTime() {
        Calendar today = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDateString = dateFormat.format(today.getTime());

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTimeString = timeFormat.format(new Date());

        tvCurrentDate.setText("Fecha Actual: " + currentDateString);
        tvCurrentTime.setText("Hora Actual: " + currentTimeString);
    }

    private void animateTextView(TextView textView) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(500);
        textView.startAnimation(fadeIn);
    }
}