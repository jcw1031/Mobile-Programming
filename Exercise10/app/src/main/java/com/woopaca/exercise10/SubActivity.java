package com.woopaca.exercise10;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise10.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.returnButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}