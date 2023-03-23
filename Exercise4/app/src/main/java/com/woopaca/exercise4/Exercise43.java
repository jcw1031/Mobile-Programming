package com.woopaca.exercise4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Exercise43 extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private Button btnMod;
    private TextView textResult;

    private String num1;
    private String num2;
    private Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise43);

        init();

        btnAdd.setOnClickListener(view -> {
            getNumbersEditText();

            try {
                validateEditTextValue(btnAdd);
            } catch (IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(), "올바른 값 입력하셈",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            result = Integer.parseInt(num1) + Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });

        btnSub.setOnClickListener(view -> {
            getNumbersEditText();

            try {
                validateEditTextValue(btnSub);
            } catch (IllegalArgumentException e) {

                return;
            }

            result = Integer.parseInt(num1) - Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });

        btnMul.setOnClickListener(view -> {
            getNumbersEditText();

            try {
                validateEditTextValue(btnMul);
            } catch (IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(), "올바른 값 입력하셈",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            result = Integer.parseInt(num1) * Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });

        btnDiv.setOnClickListener(view -> {
            getNumbersEditText();

            try {
                validateEditTextValue(btnDiv);
            } catch (IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(), "올바른 값 입력하셈",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            result = Integer.parseInt(num1) / Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });

        btnMod.setOnClickListener(view -> {
            getNumbersEditText();

            try {
                validateEditTextValue(btnMod);
            } catch (IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(), "올바른 값 입력하셈",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            result = Integer.parseInt(num1) % Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });
    }

    private void init() {
        edit1 = findViewById(R.id.edit_1);
        edit2 = findViewById(R.id.edit_2);

        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);
        btnMul = findViewById(R.id.btn_mul);
        btnDiv = findViewById(R.id.btn_div);
        btnMod = findViewById(R.id.btn_mod);

        textResult = findViewById(R.id.tv_result);
    }

    private void getNumbersEditText() {
        num1 = String.valueOf(edit1.getText());
        num2 = String.valueOf(edit2.getText());
    }

    private void validateEditTextValue(Button btn) {
        if (isEditTextEmpty(num1, num2) || (btn == btnDiv && num2.equals("0"))) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isEditTextEmpty(String num1, String num2) {
        return num1.isEmpty() || num2.isEmpty();
    }
}
