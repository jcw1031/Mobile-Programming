package com.woopaca.midterm3;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.woopaca.midterm3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CalculatorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        viewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        viewModel.setContext(this);
        viewModel.setValidator(new CalculatorValidator(this));

        dataBindingVariableConfigure();

        setObserver();
    }

    private void dataBindingVariableConfigure() {
        binding.setViewModel(viewModel);
        binding.setAdditional(Operator.ADDITION);
        binding.setSubtraction(Operator.SUBTRACTION);
        binding.setMultiplication(Operator.MULTIPLICATION);
        binding.setDivision(Operator.DIVISION);
        binding.setModulo(Operator.MODULO);
        binding.setExponentiation(Operator.EXPONENTIATION);
    }

    private void setObserver() {
        viewModel.getIsAdvanced().observe(this, isAdvanced -> {
            if (isAdvanced) {
                setAdvancedButton(View.VISIBLE);
                return;
            }
            setAdvancedButton(View.INVISIBLE);
        });
    }

    private void setAdvancedButton(int visibility) {
        binding.moduloButton.setVisibility(visibility);
        binding.exponentiationButton.setVisibility(visibility);
    }
}