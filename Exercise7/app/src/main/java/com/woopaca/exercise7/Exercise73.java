package com.woopaca.exercise7;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.woopaca.exercise7.databinding.ActivityExercise73Binding;
import com.woopaca.exercise7.databinding.Dialog1Binding;
import com.woopaca.exercise7.databinding.Toast1Binding;

public class Exercise73 extends AppCompatActivity {

    private ActivityExercise73Binding binding;
    private Dialog1Binding dialogBinding;
    private Toast1Binding toastBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExercise73Binding.inflate(getLayoutInflater());
        dialogBinding = Dialog1Binding.inflate(getLayoutInflater());
        toastBinding = Toast1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.clickButton.setOnClickListener(view -> {
            dialogBinding.nameEdit.setText(binding.nameEdit.getText().toString());
            dialogBinding.emailEdit.setText(binding.emailEdit.getText().toString());

            DialogInterface.OnClickListener onClickListenerPositive = (dialogInterface, which) -> {
                binding.nameEdit.setText(dialogBinding.nameEdit.getText().toString());
                binding.emailEdit.setText(dialogBinding.emailEdit.getText().toString());

                removeDialog(dialogBinding.getRoot().getId());
            };

            DialogInterface.OnClickListener onClickListenerNegative = (dialogInterface, which) -> {
                Toast toast = new Toast(Exercise73.this);
                toastBinding.toastText.setText("취소");
                toast.setView(toastBinding.getRoot());
                setRandomToastGravity(toast);
                toast.show();

                removeDialog(dialogBinding.getRoot().getId());
            };

            new AlertDialog.Builder(Exercise73.this)
                    .setTitle("사용자 정보 입력")
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setView(dialogBinding.dialogView)
                    .setPositiveButton("확인", onClickListenerPositive)
                    .setNegativeButton("취소", onClickListenerNegative)
                    .show();
        });
    }

    private void setRandomToastGravity(Toast toast) {
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int x = (int) (Math.random() * display.getWidth());
        int y = (int) (Math.random() * display.getHeight());
        toast.setGravity(Gravity.TOP | Gravity.START, x, y);
    }
}