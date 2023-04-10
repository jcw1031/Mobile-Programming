package com.woopaca.exercise7;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise7.databinding.ActivityExercise71Binding;

import static com.woopaca.exercise7.R.id.*;

public class Exercise71 extends AppCompatActivity {

    private ActivityExercise71Binding binding; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExercise71Binding.inflate(getLayoutInflater()); //
        setContentView(binding.getRoot()); //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exercise71_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == rotation) {
            rotateImage();
            return true;
        }

        changeImage(item);
        return true;
    }

    private void rotateImage() {
        String degree = binding.degreeEdit.getText().toString();
        System.out.println("degree = " + degree);

        if (degree.isEmpty()) {
            Toast.makeText(this, "각도를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }
        binding.imageView.setRotation(Float.parseFloat(degree));
    }

    @SuppressLint("NonConstantResourceId")
    private void changeImage(MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case image1: {
                binding.imageView.setImageResource(R.drawable.dog1);
                break;
            }
            case image2: {
                binding.imageView.setImageResource(R.drawable.dog2);
                break;
            }
            case image3: {
                binding.imageView.setImageResource(R.drawable.dog3);
                break;
            }
        }
    }
}