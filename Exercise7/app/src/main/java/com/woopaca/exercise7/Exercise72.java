package com.woopaca.exercise7;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise7.databinding.ActivityExercise72Binding;

public class Exercise72 extends AppCompatActivity {

    private ActivityExercise72Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExercise72Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "배경색(빨강)");
        menu.add(0, 2, 0, "배경색(초록)");
        menu.add(0, 3, 0, "배경색(파랑)");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        changeColor(item);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    private void changeColor(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1: {
                System.out.println(1111);
                binding.baseLayout.setBackgroundColor(Color.RED);
                return;
            }
            case 2: {
                System.out.println(2222);
                binding.baseLayout.setBackgroundColor(Color.GREEN);
                return;
            }
            case 3: {
                System.out.println(3333);
                binding.baseLayout.setBackgroundColor(Color.BLUE);
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if (view == binding.contextButton) {
            menu.setHeaderTitle("ㅋㅋ");
            menuInflater.inflate(R.menu.color_menu, menu);
        }
    }
}