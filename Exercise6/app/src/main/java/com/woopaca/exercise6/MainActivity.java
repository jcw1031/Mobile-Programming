package com.woopaca.exercise6;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; //

    private int selectYear;
    private int selectMonth;
    private int selectDay;
    private int selectHour;
    private int selectMinute;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater()); //
        setContentView(binding.getRoot()); //

        binding.calendarView.setVisibility(View.INVISIBLE);
        binding.timePicker.setVisibility(View.INVISIBLE);

        binding.startButton.setOnClickListener(view -> {
            binding.durationView.setBase(SystemClock.elapsedRealtime());
            binding.durationView.start();
            binding.durationView.setTextColor(Color.RED);
        });

        binding.finishButton.setOnClickListener(view -> {
            binding.durationView.stop();
            binding.durationView.setTextColor(Color.BLUE);

            binding.yearView.setText(Integer.toString(selectYear));
            binding.monthView.setText(Integer.toString(selectMonth));
            binding.dayView.setText(Integer.toString(selectDay));
            binding.hourView.setText(Integer.toString(selectHour));
            binding.minuteView.setText(Integer.toString(selectMinute));
        });

        binding.dateButton.setOnClickListener(view -> {
            binding.calendarView.setVisibility(View.VISIBLE);
            binding.timePicker.setVisibility(View.INVISIBLE);
        });

        binding.timeButton.setOnClickListener(view -> {
            binding.calendarView.setVisibility(View.INVISIBLE);
            binding.timePicker.setVisibility(View.VISIBLE);
        });

        binding.calendarView.setOnDateChangeListener((calendarView1, year, month, day) -> {
            selectYear = year;
            selectMonth = month;
            selectDay = day;
        });

        binding.timePicker.setOnTimeChangedListener((timePicker1, hour, minute) -> {
            selectHour = hour;
            selectMinute = minute;
        });
    }
}