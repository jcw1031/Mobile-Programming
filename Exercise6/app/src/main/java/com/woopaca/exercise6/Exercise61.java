package com.woopaca.exercise6;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise6.databinding.Exercise61Binding;

public class Exercise61 extends AppCompatActivity {

    private Exercise61Binding binding; //

    private int selectYear;
    private int selectMonth;
    private int selectDay;
    private int selectHour;
    private int selectMinute;

    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = Exercise61Binding.inflate(getLayoutInflater()); //
        setContentView(binding.getRoot()); //

        binding.dateButton.setVisibility(View.INVISIBLE);
        binding.timeButton.setVisibility(View.INVISIBLE);
        binding.datePicker.setVisibility(View.INVISIBLE);
        binding.timePicker.setVisibility(View.INVISIBLE);

        binding.durationView.setOnClickListener(view -> {
            binding.dateButton.setVisibility(View.VISIBLE);
            binding.timeButton.setVisibility(View.VISIBLE);

            binding.durationView.setBase(SystemClock.elapsedRealtime());
            binding.durationView.start();
            binding.durationView.setTextColor(Color.RED);
        });

        binding.yearView.setOnLongClickListener(view -> {
            binding.durationView.stop();
            binding.durationView.setTextColor(Color.BLUE);

            binding.yearView.setText(Integer.toString(selectYear));
            binding.monthView.setText(Integer.toString(selectMonth));
            binding.dayView.setText(Integer.toString(selectDay));
            binding.hourView.setText(Integer.toString(selectHour));
            binding.minuteView.setText(Integer.toString(selectMinute));

            binding.dateButton.setVisibility(View.INVISIBLE);
            binding.timeButton.setVisibility(View.INVISIBLE);
            binding.datePicker.setVisibility(View.INVISIBLE);
            binding.timePicker.setVisibility(View.INVISIBLE);
            return true;
        });

        binding.dateButton.setOnClickListener(view -> {
            binding.datePicker.setVisibility(View.VISIBLE);
            binding.timePicker.setVisibility(View.INVISIBLE);
        });

        binding.timeButton.setOnClickListener(view -> {
            binding.datePicker.setVisibility(View.INVISIBLE);
            binding.timePicker.setVisibility(View.VISIBLE);
        });

        binding.datePicker.setOnDateChangedListener((datePicker, year, month, day) -> {
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