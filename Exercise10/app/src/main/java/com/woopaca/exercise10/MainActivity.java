package com.woopaca.exercise10;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.woopaca.exercise10.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private VoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        initViewModel();

        binding.finishButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SubActivity.class);
            startActivity(intent);
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(VoteViewModel.class);
        binding.setViewModel(viewModel);
        viewModel.setContext(this);
    }
}