package com.woopaca.midterm1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.woopaca.midterm1.databinding.ActivityMainBinding;
import com.woopaca.midterm1.databinding.CompleteDialogBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CompleteDialogBinding dialogBinding;
    private RestaurantViewModel restaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);
        binding.setLifecycleOwner(this);

        setDataBindingVariable();

        setObserver();
    }

    private void setObserver() {
        restaurantViewModel.getFoodType().observe(this, foodType -> {
            restaurantViewModel.calculateTotalPrice();
            if (foodType != null) {
                changeImageSource(foodType);
            }
        });

        restaurantViewModel.getIsComplete().observe(this, isComplete -> {
            if (!isComplete) {
                return;
            }

            dialogBinding = DataBindingUtil.inflate(LayoutInflater
                    .from(this), R.layout.complete_dialog, null, false);
            dialogBinding.setViewModel(restaurantViewModel);

            DialogInterface.OnClickListener onClickListenerPositive = (dialogInterface, which) -> {
                Toast.makeText(MainActivity.this, "예약이 완료되었습니다.", Toast.LENGTH_LONG).show();
            };

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("예약 확인")
                    .setView(dialogBinding.baseDialog)
                    .setPositiveButton("확인", onClickListenerPositive)
                    .show();
        });
    }

    private void changeImageSource(FoodType foodType) {
        int imageSource = 0;
        switch (foodType) {
            case ITALIAN: {
                imageSource = R.drawable.italian;
                break;
            }
            case KOREAN: {
                imageSource = R.drawable.korean;
                break;
            }
            case JAPANESE: {
                imageSource = R.drawable.japanese;
                break;
            }
        }

        binding.foodImage.setImageResource(imageSource);
    }

    private void setDataBindingVariable() {
        binding.setViewModel(restaurantViewModel);

        binding.setItalian(FoodType.ITALIAN);
        binding.setKorean(FoodType.KOREAN);
        binding.setJapanese(FoodType.JAPANESE);

        binding.setAppetizer(Option.APPETIZER);
        binding.setVegetarian(Option.VEGETARIAN);
        binding.setDessert(Option.DESSERT);

        binding.setWindow(SeatType.WINDOW);
        binding.setAnywhere(SeatType.ANYWHERE);
    }
}