package com.woopaca.exercise7;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise7.databinding.ActivityMainBinding;
import com.woopaca.exercise7.databinding.Dialog1Binding;
import com.woopaca.exercise7.databinding.Toast1Binding;

import static com.woopaca.exercise7.R.id.*;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; //
    private Dialog1Binding dialogBinding;
    private Toast1Binding toastBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //
        dialogBinding = Dialog1Binding.inflate(getLayoutInflater());
        toastBinding = Toast1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); //

        registerForContextMenu(binding.colorButton);

        binding.dialogButton.setOnClickListener(view -> {
            DialogInterface.OnClickListener onClickListenerPositive = (dialogInterface, which) -> {
                binding.nameText.setText(dialogBinding.nameEdit.getText().toString());
                binding.emailText.setText(dialogBinding.emailEdit.getText().toString());
            };

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("사용자 정보 입력")
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setView(dialogBinding.dialogView)
                    .setPositiveButton("확인", onClickListenerPositive)
                    .setNegativeButton("취소", null)
                    .show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.color_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        changeColor(item);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if (view == binding.colorButton) {
            menu.setHeaderTitle("컨텍스트");
            menuInflater.inflate(R.menu.color_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        changeColor(item);
        return super.onContextItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    private void changeColor(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case item_red: {
                binding.baseLayout.setBackgroundColor(Color.RED);
                break;
            }
            case item_blue: {
                binding.baseLayout.setBackgroundColor(Color.BLUE);
                break;
            }
            case item_green: {
                binding.baseLayout.setBackgroundColor(Color.GREEN);
            }
        }
    }
}