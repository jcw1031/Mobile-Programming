package com.woopaca.exercise6;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise6.databinding.Exercise62Binding;

public class Exercise62 extends AppCompatActivity {

    private Exercise62Binding binding; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = Exercise62Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewFlipper.setVisibility(View.INVISIBLE);

        binding.startButton.setOnClickListener(view -> {
            binding.viewFlipper.setVisibility(View.VISIBLE);
            binding.viewFlipper.startFlipping();
            binding.viewFlipper.setFlipInterval(1_000);
        });

        binding.stopButton.setOnClickListener(view -> {
            binding.viewFlipper.stopFlipping();
        });
    }
}
